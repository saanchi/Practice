package com.leetcode.oj;

import java.util.ArrayList;
import java.util.Arrays;

public class Subsets {
	
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		int len = S.length;
        ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
        if( len == 0 ) return subsets;
        ArrayList<Integer> set = new ArrayList<Integer>();
        subsets.add(set);
        Arrays.sort(S);
        if( len == 1 ){
            set = new ArrayList<Integer>();
            set.add(new Integer(S[0]));
            subsets.add(set);
            return subsets;
        }
        
        int number = (1<<(len )) -1;
        System.out.println(number);
        for(int i=1; i<= number; i++){
            set = new ArrayList<Integer>();
            for( int j=1; j<=len; j++){
                if(((i>>(j-1)) & 1) == 1){
                    set.add(new Integer(S[j-1]));
                    System.out.print( S[j-1] );
                }
            }
            System.out.println();;
            subsets.add( set );
        }
        return subsets;
    }

	public static void main(String args[]){
		Subsets sb = new Subsets();
		int S[] = {1,23,2};
		sb.subsets(S);
		
		
	}
	
}
