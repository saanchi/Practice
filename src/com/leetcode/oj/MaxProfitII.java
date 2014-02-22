package com.leetcode.oj;

public class MaxProfitII {

	public int maxProfit(int[] prices) {
	   
		int max=0, sum=0, low=0;
	   if( prices.length == 0 ) return 0;
	   if( prices.length == 1 ) return 0;
	   low = prices[0];
	   for( int i = 1; i<prices.length; i++){
	       int temp = prices[i] - low;
	       if( temp > max ){
	    	   max = temp;
	       }
	       else{
	    	   if( prices[i] < prices[i-1] ){
	    		   low = prices[i];
	    		   sum += max;
	    		   max = 0;
	    	   }
	       }
	   }
	   return sum+max;
	}
	
}
