package edu.ufl.misc;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Multiple9And0 {
	
	public class Number{
		String strNumber;
		int remainder;
		public Number( String strNumber, int remainder){
			this.strNumber = strNumber;
			this.remainder = remainder;
		}
	}
	
	public static void main( String args[]){
		Scanner in = new Scanner(System.in);
		Multiple9And0 ob = new Multiple9And0();
		int nCases = in.nextInt();
		int i=0;
		int number;
		while( i< nCases){
			i++;
			Queue<Number> queue = new ArrayDeque<Number>();
			number = in.nextInt();
			Number num = ob.new Number("9", 0);
			queue.add(num);
			int remainder;
			String str1,str2;
			str1 = str2 = null;
			int temp;
			String tmpStr = null;
			while( !queue.isEmpty()){
				Number tmpNum = queue.poll();
				tmpStr = tmpNum.strNumber;
				temp = tmpNum.remainder*10 + '0' - tmpStr.charAt(tmpStr.length() - 1);
				if( temp%number == 0 ){
					System.out.println(tmpNum.strNumber);
					break;
				}
				else{
					remainder = temp%number;
					str1 = new String( tmpStr.concat("0"));
					str2 = new String( tmpStr.concat("9"));
					queue.add(ob.new Number( str1, remainder  ));
					queue.add( ob.new Number( str2, remainder ));
					tmpNum = null;
				}
			}
			
		}
	}

}
