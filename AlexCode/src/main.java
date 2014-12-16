import Filters.*;
import Graphing.*;

//import javax.swing.JPanel;

public class main {
	public static void main(String[] args){
		Butterworth obj1 = new Butterworth();
/*
		double[] input = new double[50000];
		for(int i=0; i<50000; i++){
			input[i]=10000;
		}
*/	
		double[] input = {0, 0, 250, 500, 750, 1000, 1250, 1500, 1750, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10000, 10500, 11000, 11500, 12000};

		double[] output = obj1.butterworth(input, 2000, 2500, 10000);
		
		XYPlot plotObj = new XYPlot();
		plotObj.plot(input, output);
		
		for(int i=0; i<output.length; i++){
			System.out.println(output[i]);	
		}
	}
}