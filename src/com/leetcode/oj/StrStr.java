package com.leetcode.oj;

public class StrStr {

	public int strstr( String string, String pattern ){
		int len1  = string.length();
		int len2  = pattern.length();
		int i=0,j=0;
		if( len1 ==0 || len2 == 0) return -1;
		for( i=0; i<= (len1- len2); i++){
			for( j=0; j<len2; j++){
				if( string.charAt(i+j) != pattern.charAt(j) ){
					break;
				}
			}
			if( j == len2){
				return i;
			}
		}
		
		return -1;
	}
	
	
	public static void main( String args[]){
		StrStr ob = new StrStr();
		System.out.println( ob.strstr("babcd", "abcd"));
	}
	
}
