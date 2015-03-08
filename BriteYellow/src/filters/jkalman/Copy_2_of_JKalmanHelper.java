package filters.jkalman;

import jama.Matrix;

import java.sql.Timestamp;
import java.util.ArrayList;

import maths.PhoneData;

public class Copy_2_of_JKalmanHelper{
	
	private final ArrayList<PhoneData> data;
	private final ArrayList<PhoneData> result;
	private final JKalman jk;
	
	private int index = 0;

	public Copy_2_of_JKalmanHelper(ArrayList<PhoneData> data) throws Exception{
		this(data, 0, 0);
	}
	public Copy_2_of_JKalmanHelper(ArrayList<PhoneData> data, double tkn_x, double tkn_y) throws Exception{
		this.data = data;
		result = new ArrayList<PhoneData>();
		
		jk = new JKalman(4, 2);
		jk.setState_post(new Matrix(new double[][]{
				 {data.get(0).x},
				 {data.get(0).y},
				 {data.get(1).rsx},
				 {data.get(1).rsy}
				 }));
		
	}


	public PhoneData processData(){
		
		double dt = 1;
		if(index>0)
			dt = (data.get(index).ts.getTime() - data.get(index-1).ts.getTime())/1000;

		// A
		jk.setTransition_matrix(new Matrix(new double[][]{
				{1, 0, dt, 0},
				{0, 1, 0, dt},
				{0, 0, 1, 0},
				{0, 0, 0, 1}
		}));
		
		// B
		jk.setControl_matrix(new Matrix(new double[][]{
				{dt*dt/2},
				{dt*dt/2},
				{dt},
				{dt},
		}));
		
		// C
		jk.setMeasurement_matrix(new Matrix(new double[][]{
				{1, 0, 0, 0},
				{0, 1, 0, 0}
				
		}));
		
		// Ex = [dt^4/4 0 dt^3/2 0; ...
	    // 0 dt^4/4 0 dt^3/2; ...
	    // dt^3/2 0 dt^2 0; ...
	    // 0 dt^3/2 0 dt^2].*HexAccel_noise_mag^2;
		jk.setError_cov_pre(new Matrix(new double[][]{
				{dt*dt*dt*dt/4, 0, dt*dt*dt/2, 0},
				{0, dt*dt*dt*dt/4, 0, dt*dt*dt/2},
				{dt*dt*dt/2, 0, dt*dt, 0},
				{0, dt*dt*dt/2, 0, dt*dt}
		}) );
		
		double tkn_x = 50;
		double tkn_y = 25;
		// Ez = [tkn_x 0; 0 tkn_y];
		jk.setMeasurement_noise_cov(new Matrix(new double[][]{
				{tkn_x, 0},
				{0, tkn_y}
				
		}));
		
		
		jk.Predict();
		Matrix resultant = jk.Correct(new Matrix(new double[][]{
			{data.get(index).x},
			{data.get(index).y}
		}));
		
		double[][] array = resultant.getArray();
		
		PhoneData pd = new PhoneData();
		pd.x = array[0][0];
		pd.y = array[1][0];
		pd.rsx = array[2][0];
		pd.rsy = array[3][0];
		pd.ts = data.get(index).ts;
		pd.tb = dt;
		result.add(pd);
		index++;
		
		return pd;
		
	}
	
	public boolean isEndReached(){
		return index == data.size();
	}
	public int getCurrentIndex(){
		return index;
	}
	public ArrayList<PhoneData> getFullResult(){
		return result;
	}
}