package edu.ufl.ds;

public class Heap {
	
	private int arr[] = null;
	private int heapLength;
	
	public Heap( int arr[] ){
		this.heapLength = arr.length;
		this.arr        = new int[arr.length+1];
		for( int i=0; i< this.heapLength; i++){
			this.arr[i+1] = arr[i];
		}
	}
	
	public void maxHeapify( int arr[], int index ){
		int left, right, largest, temp;
		while( index < heapLength ){
			left  = 2*index;
			right = 2*index + 1;
			if( left <= heapLength && arr[left] > arr[index] ){
				largest = left;
			}
			else largest = index;
			if( right <= heapLength && arr[right] > arr[largest]){
				largest  = right;
			}
			
			if( largest != index ){
				temp         = arr[largest];
				arr[largest] = arr[index];
				arr[index]   = temp;
			}
			else{ return; }
			index = largest;
		}
	}

	
	public void contructHeap(){
		int middle = heapLength/2;
		for( int i = middle ; i>0; i-- ){
			maxHeapify( arr, i);
		}
	}
	
	public void heapSort(){
		int temp;
		System.out.println(arr.length-1);
		for( int i=arr.length-1; i>= 2; i-- ){
			temp                = arr[1];
			arr[1]              = arr[heapLength];
			arr[heapLength]     = temp;
			heapLength          = heapLength - 1;
			maxHeapify(arr, 1);	
			printHeap();
		}	
	}
	
	public void printHeap(){
		for(int i=1; i<arr.length; i++){
			System.out.print( arr[i] + "  ");
		}
		System.out.println();
	}
	
	public static void main(String args[]){
		int arr[] = {3,5,2,67,23,12,18,4,26,32 };
		Heap heap = new Heap(arr);
		heap.contructHeap();
		heap.printHeap();
		heap.heapSort();
		heap.printHeap();
	}

}
