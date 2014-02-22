package com.leetcode.oj;

public class RemoveDuplicatesSortedArray {

	public int removeDuplicates(int[] A) {
        int len = A.length;
        if( len == 0) return 0;
        if( len == 1) return 1;
        int count =0 ;
        for( int i=1; i<len; i++){
            if( A[i] == A[i-1]){
                count++;
            }
            else if( count > 0){
                A[i-count] = A[i]; 
            }
        }
        return len-count;
    }
	
}
