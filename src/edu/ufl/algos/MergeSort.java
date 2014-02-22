package edu.ufl.algos;

public class MergeSort {

	int arr[];
	int temp[];
	
	public MergeSort( int arr[] ){
		this.arr = arr;
		this.temp = new int[arr.length];
	}
	
	public void mergeSort( int arr[], int start, int end ){
		if( start >= end ) return;
		int mid = ( start + end )/2;
		mergeSort( arr, start, mid );
		mergeSort( arr, mid+1, end );
		merge( arr, temp, start, mid, end );
	}
	
	public void print(){
		for( int i=0;i< arr.length;i++){
			System.out.print( arr[i] + "  ");
		}
		System.out.println();
	}
	
	public void swap( int a, int b ){
		int temp = arr[a];
		arr[a]   = arr[b];
		arr[b]   = temp;
	}
	
	public void merge( int arr[], int temp[], int start, int mid, int end ){

		 // copy to aux[]
        for (int k = start; k <= end; k++) {
            temp[k] = arr[k]; 
        }

        // merge back to a[]
        int i = start, j = mid+1;
        for (int k = start; k <= end; k++) {
            if      (i > mid)                    arr[k] = temp[j++];
            else if (j > end)                    arr[k] = temp[i++];
            else if ( temp[j] < temp[i] )        arr[k] = temp[j++];
            else                                 arr[k] = temp[i++];
        }
		
	}	
		
		
	public static void main( String args[] ){
		int arr[] = {2,1,7,4,8,9,2,3,6,5};
		MergeSort ob = new MergeSort(arr);
		ob.mergeSort(arr, 0, arr.length-1);
		ob.print();
		
		
	}
	
}
