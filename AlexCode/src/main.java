import Filters.*;
import Graphing.*;

//import javax.swing.JPanel;

public class main {
	public static void main(String[] args){

		double[] output = obj1.butterworth(input, 2000, 2500, 10000);

		wienerFilter obj2 = new wienerFilter();
		double input[] = {1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		double reference[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		

		double[] output = obj2.wFilter(input, reference);
		
		double in1[] = new double[output.length];
		for(int i=0; i<output.length; i++){
			in1[i] = i+1;
		}
				
		XYPlot plotObj = new XYPlot();
		plotObj.plot(in1, output, "Title", "Heading", "Y-Axis", "X-Axis", "Saved53245");
		
		
	}
}