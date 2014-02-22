package edu.ufl.ds;

import java.util.BitSet;

public class BitTst {

	public static void main( String args[]){
		//BitSet bit[] = new BitSet[5000];
		//boolean[][] arr = new boolean[5000][5000];
		//java.util.LinkedList<ListNode> list = new java.util.LinkedList<ListNode>();
		//for( int i=0; i<5000; i++){
			//bit[i] = new BitSet(5000);
			//for( int j=0; j<5000; j++){
				//bit[i].set(j);
				//arr[i][j] = true;
		//	}
		//}
		Runtime runtime = Runtime.getRuntime();
	    // Run the garbage collector
	    //runtime.gc();
	    // Calculate the used memory
	    long memory = runtime.totalMemory() - runtime.freeMemory();
	    System.out.println("Used memory is bytes: " + memory);
	}
	
	
}
