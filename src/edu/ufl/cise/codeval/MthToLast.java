package edu.ufl.cise.codeval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MthToLast {


	public static void main( String args[]){
		String inputFileName  = args[0];
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(inputFileName));
			 String sentence = null;
			 while((sentence = br.readLine()) != null){
				 String arr[] = sentence.split(" ");
				 int n = Integer.parseInt(arr[arr.length-1]);
				 if( n > arr.length - 1){
					 System.out.println(); 
					 continue;
				 }
				 int x = arr.length - 1 - n;
				 System.out.println(arr[x]);
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
