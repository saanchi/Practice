package edu.ufl.cise.codeval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SpiralPrinting {

	public String spiralMatrix( String matrix[][], int rows, int columns ){
		StringBuilder output = new StringBuilder();
		int level = 0;
		for( level =0; rows> level && columns>level ; level++, rows--, columns--){
			int rightIndex  = columns-1;
			int bottomIndex = rows-1;
			for( int i=level; i<=rightIndex; i++){
				output.append(matrix[level][i] + " ");
			}
			if( bottomIndex == level) return output.toString();
			for( int i=level+1; i<=bottomIndex; i++){
				output.append(matrix[i][rightIndex] + " ");
			}
			if( rightIndex == level ) return output.toString();
			for( int i=rightIndex-1; i>=level; i--){
				output.append(matrix[bottomIndex][i] + " ");
			}
			for( int i=bottomIndex-1; i>level; i--){
				output.append(matrix[i][level]  + " ");
			}
		}
		return output.toString();
	}
	
	public static void main(String args[]){
		
		SpiralPrinting ob = new SpiralPrinting();
		String inputFileName  = args[0];
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(inputFileName));
			 String sentence = null;
			 while((sentence = br.readLine()) != null){
				 String arr[] = sentence.split(";");
				 int rows     = Integer.parseInt(arr[0]);
				 int columns  = Integer.parseInt((arr[1]));
				 String arr1[] = arr[2].split(" ");
				 int count = 0;
				 String matrix[][]  = new String[rows][columns];
				 for( int i=0; i<rows; i++){
					 for( int j=0; j<columns; j++){
						 matrix[i][j] = arr1[count];
						 count++;
					 }
				 }
				 String output = ob.spiralMatrix(matrix, rows, columns);
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
