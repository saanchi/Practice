package edu.ufl.misc;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;


public class IsFibo {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        long arr[] = new long[75];
		arr[0] = 1;
		arr[1] = 1;
		int j=0;
		for( j=2; j<75; j++ ){
			arr[j] = arr[j-1] + arr[j-2];
		}
		long first = arr[j-1];
		long second = arr[j-2];
		long value = 0;
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		boolean flag = false;
        int k=0;
		while( k < n){
            k++;
			long number = in.nextLong();
			int low = 0;
			int high = arr.length-1;
			int mid = ( low + high )/2;
			while( low <= high ){
				//System.out.println(arr[mid]);
				if( arr[mid] == number ) {
					System.out.println("IsFibo");
					flag = true;
					break;
				}
				else if( number > arr[mid] ){
					low = mid+1;
					mid = ( low + high )/2;
				}
				else{ 
					high = mid-1;
					mid = ( low + high )/2;
				}
			}
			if(flag){
				flag =false;
				continue;
			}
			while( value < 10000000000l ){
				if(value == number ) {
					flag = true;
					System.out.println("IsFibo");
					break;
				}
				value = first + second;
				second = first;
				first = value;
			}
			if( flag ){
				flag =false;
				continue;
			}
			else{
				System.out.println("IsNotFibo");
			}
		}

        
        
    }
}