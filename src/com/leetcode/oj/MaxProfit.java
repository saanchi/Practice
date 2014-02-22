package com.leetcode.oj;

public class MaxProfit {

	public int maxProfit(int[] prices) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if( prices.length == 0 ) return 0;
        if( prices.length == 1 ) return 0;
        int low = prices[0];
        int profit = 0;
        for( int i=1;i <prices.length; i++){
        	int temp = prices[i] - low;
        	if( temp > profit){
        		profit = temp;
        	}
        	else{
        		if( prices[i] < low ){
        			low = prices[i];
        		}
        	}
        }
        return profit;
    }
	
}
