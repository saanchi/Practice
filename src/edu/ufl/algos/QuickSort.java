package edu.ufl.algos;

public class QuickSort {

	private int arr[];
	
	public QuickSort( int arr[] ){
		this.arr = arr;
	}
	
	public void quickSort( int arr[], int start, int end ){
		if( start < end ){
			int pivot = partition( arr, start, end);
			quickSort( arr, start, pivot-1 );
			print();
			quickSort( arr, pivot+1, end);
			print();
		}
	}
	
	public int getPivot( int start, int end ){
		return start;
	}
	
	public void print(){
		for( int i=0; i<arr.length; i++){
			System.out.print( arr[i] + " ");
		}
		System.out.println();
	}
	
	public int partition( int arr[], int start, int end ){
		int pivot = getPivot( start, end);
		int i = pivot + 1;
		for( int j = pivot+1; j<= end; j++ ){
			if( arr[j] < arr[pivot] ){
				swap( i, j );
				i++;
			}
		}
		swap( pivot, i-1);
		return i-1;
	}
	
	public void swap( int a, int b){
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp; 
	}
	
	public static void main( String args[] ){
		int arr[] = { 1,5,2,6,8,3,9,1,5 };
		QuickSort ob =  new QuickSort(arr);
		ob.quickSort(arr, 0, arr.length-1 );
		
	}
	
}
