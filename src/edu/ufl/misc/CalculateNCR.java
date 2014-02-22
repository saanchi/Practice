package edu.ufl.misc;


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CalculateNCR {

    public static void main(String[] args) {
    	CalculateNCR ob = new CalculateNCR();
    	Scanner in = new Scanner(System.in);
    	int no = in.nextInt();
    	int k = 0;
    	while( k < no){
    		k++;
    		int m = in.nextInt();
    		int n = in.nextInt();
    		n = m + n-2;
    		m = m-1;
    		long num = 1;
    		for (int i=0; i<m; i++) {
    	        num =(num*(n-i)) % (1000000007);
    	    }
    		long denom1 =1;
    		for(int i=1; i<=m; i++){
    			denom1 =(denom1*(i)) % (1000000007);
    		}
    		
    		long answer =(num*ob.modI(denom1, 1000000007))%1000000007;
    		System.out.println(answer);
       }
    }
    
    public long modI( long num, long p ){
    	return modpower(num, p-2, p);
    }
    
    public long modpower(long num, long x, long p){
    	long answer = 1;
        while(x > 0){
            if(x % 2 != 0) {
                answer=(answer*num)%p;
            }
            num= (num*num)%p;
            x =x/2;
        }
        return answer;
    }
}