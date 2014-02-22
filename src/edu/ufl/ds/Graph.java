package edu.ufl.ds;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {

	int numberEdges;
	int numberVertices;
	AdjNode adjList[] = null;
	
	/**
	 * This constructor is used for the random generation of graph.
	 * @param vertices
	 * @param density
	 */
	public Graph( int vertices, int density ){
		this.adjList = new AdjNode[vertices];
		this.numberVertices = vertices;
		// Initialize all vertices 
		for( int i =0; i<numberVertices; i++){
			adjList[i] = new AdjNode(i);
		}
		// Compute the maximum number of nodes possible.
		// For a graph with n nodes. They are n*( n-1 )/2
		int maxNodes    = vertices * ( vertices - 1 )/2;
		this.numberEdges = ( density*maxNodes )/100;   // number of edges required by input
		generateGraphRandom();
	}
	/**
	 * Constructor used for the generation of graph using file.
	 * @param fileName
	 */
	public Graph( String fileName ){
		// Read file and extract number of vertices and number of edges
		
		generateGraphFromFile();
	}
	
	/**
	 *  Randomly generate Graph
	 */
	private void generateGraphRandom(){
		// Since edges are generated random. This map is used to check if the edge has already been generated
		// Used just for the creation of a random graph and is not a part of any graph operation or an adjacency
		// list operations. Edge is placed in the map as "firstVertexId_secondVertexID
		HashMap< String, Boolean> edgeMap = new HashMap<String, Boolean>();
		
		// First generate random vertex. Generate a random weight.
		// check if the edge exist. if not add the edge to the graph.
		for( int i = 0;  i<numberEdges; i++ ){
			int firstVertexId  =  (int) Math.round( Math.random() * (numberVertices -1));
			int secondVertexId = ( int ) Math.round( Math.random() * (numberVertices - 1));
			int weight         = ( int )Math.round( Math.random() * 1000 ) +1;
			//Edge edge = new Edge( firstVertexId, secondVertexId, weight );
			String edge1 = firstVertexId + "_" + secondVertexId;
			String edge2 = secondVertexId + "_"  + firstVertexId;
			//if( edgeMap.containsKey(edge1) || edgeMap.containsKey(edge2)){
			if( edgeExists(firstVertexId, secondVertexId)){
				i--; // if edge already exists, no new edge is generated
			}
			else if( firstVertexId == secondVertexId ) i--; // self loops not allowed
			else{ // add vertex in the list of the firstVertex
				// Create Linked list node1 and node2
				// Since its an undirected graph there should be an entry in both vertex with same weight.
				ListNode node1 = new ListNode(secondVertexId, weight);
				ListNode node2 = new ListNode(firstVertexId, weight);
				adjList[firstVertexId].addToList(node1); 
				adjList[secondVertexId].addToList(node2);
				//edgeMap.put(edge1, true);
				//edgeMap.put(edge2, true);
			}
		}
	}
	
	private void generateGraphFromFile(){
	}
	
	public Graph(){}
	
	public boolean edgeExists( int firstVertexID, int secondVertexID ){
		if( adjList[firstVertexID] == null ) return false;
		Iterator<ListNode> itr = adjList[firstVertexID].getList().iterator();
		while( itr.hasNext()){
			ListNode node = itr.next();
			if( node.getVertex() == secondVertexID ) return true;
		}
		return false;
	}
	
	/**
	 * To check if the graph is connected. It does a DFS on the graph.
	 * @return true if graph is connected, false otherwise
	 */
	public boolean isConnected(){
		if( adjList == null ) return true; // empty graph is connected
		boolean vertexMap[] = new boolean[numberVertices]; // Maintain a vertex map to keep track if the vertex has already been traversed
		int countVertexTraversed = 0;
		int countEdgeTraversed = 0;
		// Initializing map with 0 vertex 
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		while( !stack.isEmpty() && countEdgeTraversed < numberEdges && countVertexTraversed < numberVertices){
			int vertex = stack.pop();
			// Check if the vertex has already been traversed. If not increase the count of number of vertices traversed
			if( !vertexMap[vertex] ){
				vertexMap[vertex] = true;
				countVertexTraversed++;
			}
			if( countVertexTraversed == numberVertices ) return true;
			// Traverse the list connected to vertex.
			// And keep adding into the stack.
			LinkedList<ListNode> list = adjList[vertex].getList();
			Iterator<ListNode> itr = list.iterator();
			while( itr.hasNext()){
				ListNode node = itr.next();
				int toVertex  = node.getVertex();
				if( !vertexMap[toVertex] ){ // if the vertex does not already exists.
					stack.push( toVertex);
					vertexMap[vertex] = true;
					countEdgeTraversed++;
					countVertexTraversed++;
				}
			}
		}
		if( countVertexTraversed < numberVertices )  return false;
		return true;
	}
	 
	/**
	 * Print the graph. Since its undirected it will print each edge twice.
	 */
	public void printGraph(){
		for( int i=0; i<numberVertices; i++){
			if( adjList[i].getList() != null ){
				Iterator<ListNode> itr = adjList[i].getList().iterator();
				while( itr.hasNext()){
					ListNode node = itr.next();
					System.out.println( "From : " + i + " To : " + node.getVertex() + " Weight: " + node.getWeight());
				}
			}
		}
	}
	
	/**
	 * To run test on the above graph
	 * @param args
	 */
	public static void main( String args[] ){
		//Graph graph = new Graph(10000, 10);
		//graph.printGraph();
		//System.out.println(graph.isConnected());
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("key" , null);
		if( map.containsKey("key") && map.get("key") != null )
		System.out.println(map.get("key"));
	}
	
	
}
