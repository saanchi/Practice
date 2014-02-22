package edu.ufl.cise.codeval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DoubleSquares {

	static int squareArray[] = new int[46342]; // Square root of 2147483647
	
	public static void computeSquares(){
		for ( int i=0; i<= 46341; i++){
			squareArray[i] = i*i;
		}
	}
	
	public int countSquaredSumCombinations( int n){
		int count = 0;
		int sqrt  = (int)Math.ceil(Math.sqrt(n));
		int leftIndex = 0, rightIndex = sqrt;
		while( leftIndex<=rightIndex){
			int temp = squareArray[leftIndex] + squareArray[rightIndex];
			if( temp == n){
				count++;
				leftIndex++;
				rightIndex--;
			}
			else if( temp < n ){
				leftIndex++;
			}
			else rightIndex--;
		}
		return count;
	}
	
	public static void main( String args[]){
		computeSquares();
		DoubleSquares ob = new DoubleSquares();
		//System.out.println( ob.countSquaredSumCombinations(0));
		String inputFileName  = args[0];
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(inputFileName));
			 String sentence = null;
			 int count = 0;
			 while((sentence = br.readLine()) != null){
				 count++;
				 if( count == 1) continue;
				 int n = Integer.parseInt(sentence);
				 int output = ob.countSquaredSumCombinations(n);
				 System.out.println(output);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
			}
		}

	}
	
}
