package com.leetcode.oj;

public class InterleaveString {

	
	public void interleave( char s1[], char s2[], char s3[], int i, int j, int k ){
		if( i < s1.length  && j < s2.length ){     
			s3[k] = s2[j];
			interleave( s1, s2, s3, i, j+1, k+1);
		    s3[k] = s1[i];
		    interleave( s1, s2, s3, i+1, j, k+1);
		}
		else if( i > s1.length-1 && j < s2.length ){
			s3[k] = s2[j];
	        interleave( s1, s2, s3, i, j+1, k+1);
	    }
	    else if( j > s2.length-1 && i < s1.length){
	        s3[k] = s1[i];
	        interleave( s1, s2, s3, i+1, j, k+1);
	    }
		else {
		    System.out.println( new String(s3));
		}
	}

	public boolean isInterleave( String s1, String s2, String s3){
		int len1 = s1.length();
		int len2 = s2.length();
		int len3 = s3.length();
		if( len1 == 0 && len2 == 0 ) return false;
		if( len3 == 0 ) return false;
		if( len1 + len2 != len3 ) return false;
		int i=0, j =0, k=0;
		int pos1 = 0, pos2 =0, pos3=0;
		while( i <= len1 -1 || j <= len2 -1 ){
			if( i <= len1-1 &&  j <= len2-1 && s2.charAt(j) ==  s1.charAt(i) && s3.charAt(k) == s1.charAt(i) ){
				k++; i++;
			}
			else if( j <= len2-1 && s3.charAt(k) == s2.charAt(j)){
				k++; j++;
			}
			else{
				return false;
			}
		}
		return true;
	}
	
	
	public static void main( String args[]){
		InterleaveString ob = new InterleaveString();
		String s1 = "AB";
		String s2 = "CD";
		char arr[] = new char[s1.length() + s2.length()];
		//ob.interleave( s1.toCharArray() , s2.toCharArray(), arr, 0, 0, 0);
		//ob.interleave( s2.toCharArray() , s1.toCharArray(), arr, 0, 0, 0);
		System.out.println(ob.isInterleave("a", "", "a"));
	}
}
