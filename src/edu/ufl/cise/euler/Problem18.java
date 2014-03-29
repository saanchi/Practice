package edu.ufl.cise.euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below,
 *  the maximum total from top to bottom is 23.
	3
   7 4
  2 4 6
 8 5 9 3
 * 
 * @author sanchit
 *
 */

public class Problem18 {

	String fileName = "euler/18.txt";
	int rows = 15;
	int arr[][];;
	
	public void readFile() throws IOException{
		BufferedReader bf = new BufferedReader(new FileReader(new File(fileName)));
		String line = "";
		int count = 0;
		arr = new int[rows][];
		while( (line = bf.readLine()) != null ){
			String split[] = line.split(" ");
			arr[count] = new int[split.length];
			for( int i=0; i<split.length; i++){
				arr[count][i] = Integer.parseInt(split[i]);
			}
			count++;
		}
		bf.close();
	}
	
	public int getSum(){
		for( int i=1; i<rows; i++){
			arr[i][0] += arr[i-1][0];
			int j=1;
			for(  j=1; j<arr[i].length-1; j++ ){
				int max = arr[i-1][j-1] > arr[i-1][j] ? arr[i-1][j-1] : arr[i-1][j];
				arr[i][j] = arr[i][j] + max;
			}
			arr[i][j]  += arr[i-1][j-1];
		}
		int max = 0;
		for( int i=0; i<arr[rows-1].length; i++){
			if( arr[rows-1][i] > max)
				max =  arr[rows-1][i];
		}
		return max;
	}
	
	
	
	public static void main( String args[] ) throws IOException{
		Problem18 ob = new Problem18();
		ob.readFile();
		System.out.println( ob.getSum() );
	}
	
	
}
