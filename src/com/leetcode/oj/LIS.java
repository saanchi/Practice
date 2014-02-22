package com.leetcode.oj;

import edu.ufl.java.MutableInteger;

public class LIS {

	
	public int recursiveLIS( int arr[], MutableInteger max, int len){
		if( len == 1 ) return 1;
		int previousResult = 0;
		int maxLen = 1;
		for( int i=1; i<len; i++){
			previousResult = recursiveLIS(arr, max, i);
			if( arr[i-1] < arr[len-1]  && previousResult +1 > maxLen ){
				maxLen = previousResult + 1;
			}
		}
		if( maxLen > max.getValue()){
			max.setValue(maxLen);
		}
		return maxLen;
	}
	
	public static void main(String args[]){
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		LIS ob = new LIS();
		System.out.println(ob.recursiveLIS(arr, new MutableInteger(1), 8));
	}
	
}
