package Filters;

public class wienerFilter {
	
	public static double[] wFilter(double[] input, double[] reference){
		
		double[][] R_w = autocorrelation(input);
		double[] R_sw= crosscorrelation(input,reference);
		double[] a = weights(R_w,R_sw);
		double[] x = output(input, a);
        for(int m = 0; m<x.length; m++){
        	System.out.println(x[m]+" ");
        }
		System.out.println("Output----");

		return x;
	}
	
	
	public static double[][] autocorrelation(double[] input){
		
		double T[][] = new double[input.length][input.length];
		
		for(int i=0; i<input.length; i++){
			for(int j=0; j<input.length; j++){	
				int pos = j-i;
				if(j-i<0){
					pos = -pos;
				}
				for(int k=0; k<(input.length-pos); k++){
					T[i][j]+=input[k+pos]*input[k];
				}
				System.out.printf(T[i][j]+" ");				
			}
			System.out.println("");
		}
		System.out.println("Autocorrelation----");
		return T;
	}
	
	public static double[] crosscorrelation(double[] input, double[] reference){
		
		double v[] = new double[input.length];
		
		for(int i=0; i<input.length; i++){
			for(int k=0; k<input.length-i; k++){
				v[i]+=input[k]*reference[k+i];
			}
			System.out.println(v[i]+" ");
		}
		System.out.println("Cross-correlation----");
		return v;
	}
	
	public static double[] weights(double[][] T, double[] v){
		
		double a[] = new double[v.length];
		
		a=solve(T,v);
		for(int i = 0; i<v.length; i++){
			System.out.printf(a[i]+", ");
		}

		return a;
	}
	
	public static double[] solve(double[][] A, double[] b){
		double EPSILON = 1e-10;
        int N  = b.length;

        for (int p = 0; p < N; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            // singular or nearly singular
            if (Math.abs(A[p][p]) <= EPSILON) {
                throw new RuntimeException("Matrix is singular or nearly singular");
            }

            // pivot within A and b
            for (int i = p + 1; i < N; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < N; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // back substitution
        double[] x = new double[N];
        double[] x_rounded = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        
        for(int i=0; i<x.length;i++){
        	//x_rounded[i] = Math.round(x[i]);
        	System.out.println(x[i]);
        }
		System.out.println("Weights----");

        return x;
	}
	
	public static double[] output(double[] input, double[] weights){
		double[] out = new double[input.length];
		
		for(int n=0; n<input.length; n++){
			for(int i=0; i<n; i++){
				out[n] += weights[i]*input[n-i];
			}
		}
		return out;
	}
}
















