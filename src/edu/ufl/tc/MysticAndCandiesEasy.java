package edu.ufl.tc;

import java.util.Arrays;

public class MysticAndCandiesEasy {
	
	public int minBoxes(int C, int X, int[] high){
		int min = 0;
		Arrays.sort(high);
		int diff = C - X;
		int sum =0;
		int count =0;
		for( int i=0; i<high.length; i++){
			sum = sum + high[i];
			if( sum <= diff)
				count++;
			if( sum > diff){
				break;
			}
		}
		return high.length-count;
		
	}
	
	

}
