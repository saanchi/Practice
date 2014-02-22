package evernote;

import java.util.Scanner;

public class ProductArray {
	
public static void main( String args[]){
		
		Scanner in = new Scanner(System.in);
		int len = in.nextInt();
		int i=0;
		long product[] = new long[len];
		long arr[] = new long[len];
		long left[] = new long[len];
		long right[] = new long[len];
		
		while( i< len){
			arr[i] = in.nextInt();
			i++;
		}
		right[len-1] = 1;
		left[0] = 1;
		// populating left array
		for( int j=1; j<len; j++  ){
			left[j] = arr[j-1]*left[j-1];  
		}
		// populating righ array
		for( int j=len-2; j>=0; j--){
			right[j] = right[j+1]*arr[j+1];
		}
		// calculating the products
		for(int j=0;j<len; j++){
			product[j] = left[j]*right[j];
			System.out.println(product[j]);
		}
	}
	
}
