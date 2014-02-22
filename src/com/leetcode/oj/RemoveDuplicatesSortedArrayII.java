package com.leetcode.oj;

public class RemoveDuplicatesSortedArrayII {
	
	/**
	 * Such that the element which is repeated does not exist in the array
	 * e.g. 1,2,2,3,4,5,5,6,7 should return : 1,3,4,6,7
	 * @param A
	 * @return
	 */
	// Case 1:) No number repeated : 1,2,3,4,5  should be as it is
	// Case 2:) Numbers repeated   : 1,2,2,3,4,4,5,6 : 1,3,5,6
	// Case 3:) Numbers repeated consecutively : 1,2,2,3,3,4,5,5,6 : 1,4,6
	// Case 4:) Boundary cases : Repeated number in start and repeated number in the end:
	//          1,1,2,3,4,4 : 2,3
	public int removeDuplicates(int[] A) {
        int len = A.length;
        if( len == 0) return 0;
        if( len == 1) return 1;
        int count =0 ;
        for( int i=1; i<len; i++){
            if( A[i] == A[i-1]){
                count = count + 2;
            }
            else if( count > 0){
                A[i-count] = A[i]; 
            }
        }
        return len-count;
    }
	
	public static void main( String args[]){
		RemoveDuplicatesSortedArrayII ob = new RemoveDuplicatesSortedArrayII();
		int A[] = {1,2,2,3,4,4,5,6};
		System.out.println( ob.removeDuplicates(A));
		int B[] = {1,2,2,3,3,4,5,5,6};
		System.out.println(ob.removeDuplicates(B));
		int C[] = {1,2,3,4,5};
		System.out.println(ob.removeDuplicates(C));
		int D[] = {1,1,2,3,4,4};
		System.out.println(ob.removeDuplicates(D));
	}

}
