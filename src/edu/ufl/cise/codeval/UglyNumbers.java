package edu.ufl.cise.codeval;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class UglyNumbers {
	
	static int count =0;  // To keep count of the number of ugly numbers
	
	/**
	 * Check if the number is ugly
	 * @param num
	 * @return
	 */
	public boolean isUgly( double num){
		if( num == 0 || num%2 == 0 || num%3 == 0 || num%5 ==0 || num%7 == 0 ) return true;
		return false;
	}
	
	/**
	 * Evaluate the expression of the form a+b.c where '.' is a concatenation operator.
	 * @param exp
	 * @return
	 */
	
	public double evaluateExpression( String exp){
		double temp =0;
		Stack<Character> operatorStack = new Stack<Character>();
		Stack<Double> numberStack      = new Stack<Double>();
		char arr[]  = exp.toCharArray();
		int count = 0;
		while( count < arr.length ){
			if(Character.isDigit(arr[count])){
				numberStack.push((double) (arr[count] - '0'));
			}
			else if( arr[count] == '+' || arr[count] == '-'){
				if( operatorStack.isEmpty()){
					operatorStack.push(arr[count]);
				}
				else{
					int multi = 0;
					while( !operatorStack.isEmpty()){
						char ch = operatorStack.pop();
						if( ch == '.') multi++;
						else multi = 0;
						double n1 = numberStack.pop();
						double n2 = numberStack.pop();
						temp = getValue(n1, n2, ch, multi);
						numberStack.push(temp);
					}
					operatorStack.push(arr[count]);
				}
			}
			else if( arr[count] == '.'){
				operatorStack.push(arr[count]);
			}
			count++;
		}
		
		// Evaluate the remaining expression now
		int multi = 0;
		while( !operatorStack.isEmpty()){
			char ch = operatorStack.pop();
			if( ch == '.') multi++;
			else multi = 0;
			double n1 = numberStack.pop();
			double n2 = numberStack.pop();
			temp = getValue(n1, n2, ch, multi);
			numberStack.push(temp);
		}
		return temp;
	}
	
	/**
	 * Get the result of the numbers and operator
	 * @param n1
	 * @param n2
	 * @param ch
	 * @param multi
	 * @return
	 */
	public double getValue( double n1, double n2, char ch, int multi){
		double temp = 0;
		if( ch == '.'){
			temp = (n2*Math.pow(10, multi) + n1);
		}
		else if( ch == '-'){
			temp = n2 - n1;
		}
		else if( ch =='+'){
			temp = n1 + n2;
		}
		return temp;
	}
	
	/**
	 * Generate all the possible combination of expressions of the given string
	 * @param str
	 * @param len
	 * @param exp
	 * @param sign
	 */
	public void generateUgly( char str[], int len, String exp, char sign){
		StringBuilder sb = new StringBuilder(exp);
		if( sign == '+'){
			sb.append( "+" + str[len]);
		}
		else if( sign == '-'){
			sb.append( "-" + str[len]);
		}
		else if( sign == '.'){    //used for concatenation
			sb.append( "." + str[len]);
		}
		if( len == str.length-1){
			double currNum = evaluateExpression( sb.toString()); 
			if(isUgly(currNum)){
				count++;
			}
			return;
		}
		else{
			generateUgly(str, len+1, sb.toString(), '+');
			generateUgly(str, len+1, sb.toString(), '-');
			generateUgly(str, len+1, sb.toString(), '.');
		}
	}
	
	public static void main( String args[]){
		String inputFileName  = args[0];
		UglyNumbers ob = new UglyNumbers();
		BufferedReader br = null;
		try {
			 br = new BufferedReader(new FileReader(inputFileName));
			 String sentence = null;
			 while((sentence = br.readLine()) != null){
				 char arr[] = sentence.toCharArray();
				 if( arr.length == 1) {
					 if( ob.isUgly(Double.parseDouble(sentence))){
						 System.out.println("1");
						 count = 0;
					 }
					 else{
						 System.out.println("0");
						 count = 0;
					 }
					 continue;
				 }
				 String exp = Character.toString(arr[0]);
				 // Initiate call with each operator. '.' is a concatenation operator.
				 ob.generateUgly(arr, 1, exp, '+'); 
				 ob.generateUgly(arr, 1, exp, '-');
				 ob.generateUgly(arr, 1, exp, '.');
				 System.out.println(count);
				 count = 0;
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
