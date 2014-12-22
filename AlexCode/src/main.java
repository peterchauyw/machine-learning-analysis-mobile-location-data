import Filters.*;
import Graphing.*;

//import javax.swing.JPanel;

public class main {
	public static void main(String[] args){
/*
		Butterworth obj1 = new Butterworth();

		double[] input = new double[50000];
		for(int i=0; i<50000; i++){
			input[i]=10000;
		}
	
		double[] input = {0, 0, 250, 500, 750, 1000, 1250, 1500, 1750, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10000, 10500, 11000, 11500, 12000};
		
		double[] output = obj1.butterworth(input, 2000, 2500, 10000);
		
		XYPlot plotObj = new XYPlot();
		plotObj.plot(input, output, "Title", "Heading", "Y-Axis", "X-Axis", "Saved53245");
		
		for(int i=0; i<output.length; i++){
			System.out.println(output[i]);	
		}
		*/
		wienerFilter obj2 = new wienerFilter();
		double input[] = {1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		double reference[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		

		//obj2.autocorrelation(input);

		//obj2.crosscorrelation(input, reference);
		double a[][] = {{2,1,-1},{-3,-1,2},{-2,1,2}};
		double b[] = {8,-11,-3};
		//obj2.solve(a, b);
		double[] output = obj2.wFilter(input, reference);
		
		double in1[] = new double[output.length];
		for(int i=0; i<output.length; i++){
			in1[i] = i+1;
		}
				
		XYPlot plotObj = new XYPlot();
		plotObj.plot(in1, output, "Title", "Heading", "Y-Axis", "X-Axis", "Saved53245");
		
		
	}
}