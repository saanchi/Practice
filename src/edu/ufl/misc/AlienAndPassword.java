package edu.ufl.misc;

import java.util.HashMap;

public class AlienAndPassword {

	public int getNumber( String S){
		HashMap<String,Boolean> map = new HashMap<String,Boolean>();
		char arr[] = S.toCharArray();
		for( int i=0; i<arr.length; i++){
			StringBuilder sb = new StringBuilder();
			for( int j=0; j<arr.length; j++){
				if( i == j) continue;
				sb.append(arr[j]);
			}
			if( !map.containsKey(sb)){
				map.put(sb.toString(), new Boolean(true));
			}
		}
		return map.entrySet().size();
	}
	
	
	public static void main( String args[]){
		AlienAndPassword ob = new AlienAndPassword();
		System.out.println(ob.getNumber("A"));
	}
	
}

