package com.leetcode.oj;

public class SwapBits {

	public int swapBitsNumber( int number ){
		if( number == 0  ) return number; 
		int swapN = 0;
		for( int i=0, j=31; i<j; i++, j--){
			int left = number >> i;
			int right = number >> 31-i;
			if( (left ^ right) == 1){
				// Swap the bits
				System.out.println(left + " " + right );
				swapN = swapN | (( 1 & right) << i) ;  
				swapN = swapN | ((1 & left) << 31-i) ;
			}
		}
		return swapN;
	}
	
	public boolean isNumberMultipleTwo( int number ){
		if( number == 1) return false;
		return  (number & (number - 1)) == 0 ? true : false;
	}
	
	public static void main( String args[]){
		SwapBits sb = new SwapBits();
		//System.out.println(sb.isNumberMultipleTwo(0));
		System.out.println(sb.swapBitsNumber(2));
	}
}
