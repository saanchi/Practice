package edu.ufl.ds;

import java.util.Iterator;
import java.util.LinkedList;

public class mst {
	
	private int vertices;
	private int density;
	private Graph graph;
	
	public mst( int vertices, int density ){
		this.vertices = vertices;
		this.density  = density;
		this.graph    = new Graph( vertices, density );
	}
	
	private int returnMinKeyArray( boolean mstSet[], int keyArr[] ){
		int minWeight      = Integer.MAX_VALUE;
		int minWeightIndex = 0;
		for( int i=0; i<vertices; i++){
			if( mstSet[i] == false && keyArr[i] < minWeight ){
				minWeight = keyArr[i];
				minWeightIndex = i;
			}
		}
		return minWeightIndex;
	}

	// Run Prims's Algorithm using Fibonacci heap for extracting minimum value.
	public void runMSTFScheme(){
		boolean mstSet[] = new boolean[vertices]; // To keep the track which vertex has been added to the MST set.
		FHNode vertexPointers[] = new FHNode[vertices];   // To keep the track of the nodes inserted into heap
		int countMST = 0; // To keep count how many vertices are present at any moment in the MST set.
		// Create fibonacci heap 
		FibonacciHeap heap = new FibonacciHeap();
		// initialize vertex values with Integer.MAX_VALUE. 
		// Except the first vertex '0' which is initialized with value 0.
		vertexPointers[0] = heap.enque(0, 0);
		for(int i=1; i<vertices; i++){
			vertexPointers[i] = heap.enque(i, Integer.MAX_VALUE );
		}
		//System.out.println( heap.getMin().getData());
		// Initially no vertex is present in MST set. Hence initializing with false
		for( int i=0; i<vertices; i++){
			mstSet[i] = false;
		}
		int minWeight=0;
		// Iterate till all the vertices are present in the MST set.
		while( countMST < vertices ){
			// Extract the node pointer with minimum weight.
			FHNode minNode = heap.getMin();
			int minWeightVertex = (int) heap.deque(); // Remove it from the queue
			//System.out.println(minWeightVertex);
			//System.out.println(minWeightVertex);
			minWeight += (int)minNode.getPriority();
			mstSet[minWeightVertex] = true;
			countMST++;
			// Explore the connected vertices of the minimum weight vertex.
			LinkedList<ListNode> connectedVertices = graph.adjList[minWeightVertex].getList();
			Iterator<ListNode> itr = connectedVertices.iterator();
			// Iterate and extract the connected vertices of the minimum weight vertex.
			while( itr.hasNext()){
				ListNode node = itr.next();
				FHNode heapNode = vertexPointers[node.getVertex()];
				// if vertex is not already a part of MST and the weight of key in graph is less than the weight present in vertexPointers
				if( !mstSet[node.getVertex()] &&  node.getWeight() < heapNode.getPriority()){ 
					heap.decreaseKey( heapNode, node.getWeight()); // update the vertex values array with the weight of the vertex.
				}
			}
		}
		System.out.println( " Min weight is " + minWeight);	
	}
	
	/**
	 * Based on the implementation type call either array based scheme or Fibonacci scheme 
	 * @param implementationType
	 */
	
	public void runMSTSimpleScheme(){
		boolean mstSet[] = new boolean[vertices]; // To keep the track which vertex has been added to the MST set.
		int vertexValues[] = new int[vertices];   // To keep the track of the minimum cost with which the vertex is reachable. Initially set infinite
		int countMST = 0; // To keep count how many vertices are present at any moment in the MST set.
		// initialize vertex values with Integer.MAX_VALUE. 
		// Except the first vertex '0' which is initialized with value 0.
		vertexValues[0] = 0;
		for(int i=1; i<vertices; i++){
			vertexValues[i] = Integer.MAX_VALUE;
		}
		// Initially no vertex is present in MST set. Hence initializing with false
		for( int i=0; i<vertices; i++){
			mstSet[i] = false;
		}
		// Iterate till the all the vertices are present in the MST set. And keep adding the minimum weight of MST
		int minWeight = 0;
		while( countMST < vertices ){
			// Extract the vertex with minimum weight.
			int minWeightVertex =  returnMinKeyArray( mstSet, vertexValues );
			minWeight += vertexValues[minWeightVertex];
			mstSet[minWeightVertex] = true;
			countMST++;
			// Explore the connected vertices of the minimum weight vertex.
			LinkedList<ListNode> connectedVertices = graph.adjList[minWeightVertex].getList();
			Iterator<ListNode> itr = connectedVertices.iterator();
			// Iterate and extract the connected vertices of the minimum weight vertex.
			// Update the vertexValues with the actual weight
			while( itr.hasNext()){
				ListNode node = itr.next();
				// if vertex is not already a part of MST and the weight of key in graph is less than the weight present in vertexvalues
				if( !mstSet[node.getVertex()] &&  node.getWeight() < vertexValues[node.getVertex()] ){ 
					vertexValues[node.getVertex()] = node.getWeight(); // update the vertex values array with the weight of the vertex.
				}
			}
		}
		System.out.println( " Min weight is " + minWeight);	
	}
	
	public static void main( String args[] ){
	
		if( args.length != 3 || args.length != 2 ){
			System.out.println( " Run as follows : mst -r n d or mst -s file_name or mst -f file_name ");
	//		System.exit(1);
		}
		
		int density = 0;
		int vertices = 0;
		// For random mode 
		/*if( args[1] == "-r" || args[1] == "-R" ){
			vertices = Integer.parseInt( args[2] );
			density  = Integer.parseInt( args[3] );
			mst ob = new mst( vertices, density );
		*/	
			// Run the simple scheme
			mst ob = new mst(10,100);
			//ob.graph.printGraph();
			ob.runMSTSimpleScheme();
			// Run FScheme
			ob.runMSTFScheme();
		//}
		// For simple mode
		/*String fileName = "";
		if( args[1] == "-s" || args[1] == "-S" ){
			fileName  = args[1];
			
		}
		
		// For F Scheme
		if( args[1] == "-f" || args[1] == "-F" ){
			fileName = args[1];
		}*/
	}


}
