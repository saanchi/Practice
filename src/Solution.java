import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int arr[] = new int[50000000];
		arr[0] = 1;
		arr[1] = 1;
		int j=0;
		for( j=2; j<50000000; j++ ){
			arr[j] = arr[j-1] + arr[j-2];
		}
		double first = arr[j-1];
		double second = arr[j-2];
		double value = 0.0;
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		boolean flag = false;
		while( n++<n){
			double number = in.nextDouble();
			int low = 0;
			int high = arr.length-1;
			int mid = ( low + high )/2;
			while( low <= high ){
				if( number == arr[mid] ) {
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
			while( value < 10000000000.0 ){
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