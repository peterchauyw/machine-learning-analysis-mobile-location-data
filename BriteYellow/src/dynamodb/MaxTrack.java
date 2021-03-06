package dynamodb;

import java.util.ArrayList;

import objects.PhoneDataDB;

public class MaxTrack {
	
	public int findMaxTrackNo(ArrayList<PhoneDataDB> test){
		int max = 0;
		if(test.get(1).getTimestampLong()>test.get(0).getTimestampLong()){
		
			for(int i = test.size()-1; i > 0; i--){
				max = test.get(i).getTRACK_NO();
				if(max != -1){
					return max;
				}
			}
		}
		else{
			System.err.println("Put in timestamp order ascending!");
		}
		return 0;
	}
}
