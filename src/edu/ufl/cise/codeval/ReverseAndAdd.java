package edu.ufl.cise.codeval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReverseAndAdd {

	static int iterations = 0;
	
	public boolean isPalindrome( int n){
		boolean isPalindrome = true;
		int i=0;
		char arr[] = new char[12];
		while( n != 0){
			arr[i++] = (char)( 48 + Math.abs(n%10));
			n = n/10;
		}
		i--;
		for( int j=0; j<=i; j++, i--){
			if( arr[i] != arr[j]){
				isPalindrome = false;
				break;
			}
		}
		return isPalindrome;
	}
	
	public int reverseNumber( int n){
		int number = 0;
		while( n != 0){
			int tmp = Math.abs(n%10);
			number  = number*10 + tmp;
			n = n/10;
		}
		return number;
	}
	
	public int reverseAndAdd(int n){
		while( !isPalindrome(n)){
			int temp = n + reverseNumber(n);
			n = temp;
			iterations++;
		}
		return n;
	}
	
	public static void main( String args[]){
		ReverseAndAdd ob = new ReverseAndAdd();
		//System.out.println(ob.isPalindrome(12));
		//System.out.println(ob.reverseNumber(1234555));
		String inputFileName  = args[0];
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(inputFileName));
			 String sentence = null;
			 while((sentence = br.readLine()) != null){
				 int n = Integer.parseInt(sentence);
				 int number = ob.reverseAndAdd(n);
				 System.out.println( iterations + " " + number );
				 iterations = 0;
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
		//System.out.println(ob.reverseAndAdd(195) + "iterations " + iterations);
	}
	
	
}
