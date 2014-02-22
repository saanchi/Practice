package edu.ufl.spoj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class ANARC09C {

	int prime[];
	int small;
	int commonCount = 0;
	ArrayList<Integer> primes = new ArrayList<Integer>();
	HashMap<Integer, Integer> n1map = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> n2map = new HashMap<Integer, Integer>();
	
	public ANARC09C( int n1, int n2  ){
		small = 0;
		if( n1 > n2) small = (int) Math.pow(n1, 0.5);
		else small  = (int) Math.pow(n1, 0.5);
		prime = new int[small+1];
		generatePrime();
		findCommonPrimes(n1, n2);
		System.out.println( commonCount + ":" +  returnDistance());
	}

	public void generatePrime(){
		
		for ( int i=2; i<=small; i++ ){
			for( int j=2*i; j<=small; j = j+i){
				prime[j] = 1;
			}
		}
		for( int i=2; i<=small; i++){
			if( prime[i] != 1){
				primes.add(i);
			}
		}
	}
	
	public void findCommonPrimes( int n1, int n2 ){
		Iterator<Integer> itr = primes.iterator();
		while( itr.hasNext()){
			int n = itr.next();
			int div1 = 0;
			int div2 = 0;
			while( n1%n == 0){
				div1++;
				n1 = n1/n;
			}
			while( n2%n == 0){
				div2++;
				n2 = n2/n;
			}
			if(div1 != 0 && div2 != 0 ){
				n1map.put(n, div1);
				n2map.put(n, div2);
				commonCount++;
				//System.out.println( "common  " + n);
			}
			else if( div1 == 0 && div2 != 0 ){
				n2map.put(n, div2);
				n1map.put(n, 0);
				commonCount++;
				//System.out.println("n1  " + n);
			}
			else if( div2 == 0 && div1 != 0 ){
				n1map.put(n, div1);
				n2map.put(n, 0);
				commonCount++;
				//System.out.println( "n2  " + n);
			}
		}
	}
	
	public int returnDistance(){
		int distance = 0;
		Iterator<Integer> itr = n1map.keySet().iterator();
		while( itr.hasNext()){
			int key = itr.next();
			int x1 = n1map.get(key);
			int x2 = n2map.get(key);
			distance += Math.abs(x1-x2);
		}
		return distance;
	}
	
	public static void main(String args[]){
		
		//ANARC09C ob = new ANARC09C(770, 792);
		Scanner in = new Scanner(System.in);
		int n1 = in.nextInt();
		int n2 = in.nextInt();
		ANARC09C ob = new ANARC09C(n1, n2);
	}
	
}
