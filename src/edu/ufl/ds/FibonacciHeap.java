package edu.ufl.ds;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class FibonacciHeap {

	private FHNode minNode;
	private int size;
	
	public FibonacciHeap(){
		this.minNode = null;
		this.size    = 0;
	}
	
	public FHNode getMin() {
		return minNode;
	}

	public void setMin(FHNode minNode) {
		this.minNode = minNode;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean isEmpty(){
		return minNode == null;
	}

	public void clear() {
        minNode = null;
        size = 0;
    }
	
	public int size() {
		return size;
	}

	/**
	 * 
	 * @return An empty Fibonacci Heap object
	 */
	public static FibonacciHeap makeFibonacciHeap(){
		FibonacciHeap heap = new FibonacciHeap();
		return heap;
	}
	
	/**
	 * Removes node1 from the root list of heap and make node1 a child of node2
	 * @param node1
	 * @param node2
	 */
	public void linkHeap( FHNode node1, FHNode node2 ){
		//if( node1 == null || node2 == null ) return;
		// Detach node1 from the list and join the adjacent pointers with each other
		node1.getLeftSibling().setRightSibling( node1.getRightSibling());
		node1.getRightSibling().setLeftSibling(node1.getLeftSibling());
		// Make node1 a child of node2.
		//Setting parent pointer of node1
		node1.setParent( node2 );
		// Adjusting node2 child's pointer
		if( node2.getChild() == null ){
			node2.setChild( node1 );
			// Since only child therefore pointing left and right pointers to itself
			node1.setLeftSibling( node1 );
			node1.setRightSibling( node1 );
		}
		else{ // if child pointer exists
			FHNode child = node2.getChild();
			// Adjusting node1's left and right pointers
			node1.setLeftSibling( child );
			node1.setRightSibling( child.getRightSibling() );
			// Adjusting left and right pointers of node1's siblings
			child.getRightSibling().setLeftSibling(node1);
			child.setRightSibling( node1 );
		}
		// Increase degree of node2
		node2.setDegree( node2.getDegree() + 1 );
		// Set child cut flag to false for node1 means no child has been cut
		node1.setChildCut(false); 
	}
	
	/**
	 * Insert a new node into the Fibonacci Heap structure.
	 * It creates a new node and merge the new node into the existing circular doubly linked list.
	 * @return List having the minimum priority 
	 * @param data
	 * @param priority
	 * @return FHNode type object of the newly created node
	 */
	public FHNode enque( int data, int priority ){
		FHNode newNode = new FHNode( data, priority );
		// if initially root list is empty
		if( minNode == null ){
			minNode = newNode;
		}else{ // if a min node exists
			newNode.setLeftSibling( minNode );
			newNode.setRightSibling( minNode.getRightSibling() );
			minNode.getRightSibling().setLeftSibling( newNode );
			minNode.setRightSibling( newNode );
			// Check which one is minimum : existing or the newly created.
			if( minNode.getPriority() > newNode.getPriority()){
				minNode = newNode;
			}
		}
		size++;
		return newNode;
	}
	
	/**
	 * @return Minimum element of the node without removing the element from queue
	 */
	public int extractMin(){
		return minNode.getData();
	}
	
	/**
	 * Returns and deletes minimum element of the heap
	 * @return Returns minimum element of the heap
	 */
	public int deque(){
		if( minNode == null ) return -1;
		FHNode node = minNode; // Cache the minNode copy
		// If child of the node exists set the parent pointers of child nodes to null
		// Iterate over the child pointers, connected via circular doubly linked list,
		// Setting parent pointer of each to null.
		FHNode child = node.getChild();
		if( child != null ){
			FHNode temp = node.getChild();
			do{
				temp.setParent( null );
				temp = temp.getRightSibling();
			}while( temp != child );
			
			// Merge the child node list to the root list 
			// Store the right siblings in temp locations
			// merge the children into root list
            FHNode minleft = minNode.getLeftSibling();
            FHNode zchildleft = node.getChild().getLeftSibling();
            minNode.setLeftSibling( zchildleft );
            zchildleft.setRightSibling(minNode);
            child.setLeftSibling(minleft);
            //z.child.left = minleft;
            minleft.setRightSibling(node.getChild());
            //minleft.right = z.child;

		}
		// Now remove minNode from the root list
		minNode.getLeftSibling().setRightSibling( minNode.getRightSibling());
		minNode.getRightSibling().setLeftSibling(minNode.getLeftSibling());
		// If there is only one node in the heap. Just set the minNode to null and return the minimum value
		if( minNode == minNode.getRightSibling()){
			minNode = null;
		}
		else{
			minNode = minNode.getRightSibling(); // assign min node to an arbitrary node
			// Call the consolidate function to merge the trees of equal degrees.
			consolidate();
		}
		size--;       // Decrease the size of heap
		return node.getData(); // return the cached minNode's copy
	}
	
	/**
	 * Decrease the priority of a node. Decreasing the priority can result in cascading cut.
	 * @param x Node whose priority needs to be decreased
	 * @param priority. New priority value
	 */
	public void decreaseKey( FHNode node, int priority ){
		if( priority > node.getPriority()){ // Priority is greater than the existing value
			return; 
		}
		// Else just set the priority
		node.setPriority(priority);
		FHNode parent = node.getParent();
		// if the parent node is not null and the new priority of node is less than parent's priority
		// cut the node and move the child nodes to the root list
		if( parent != null &&  node.getPriority() < parent.getPriority()){
			// cut the node from the parent and move the child nodes to root list
			cut( node, parent );
			// Invoke cascading cut on the parent
			cascadingCut( parent );
		}
		// if the node with changed priority is minimum then set it as minNode
		if( node.getPriority() < minNode.getPriority()){
			minNode = node;
		}
	}
	
	/**
	 * Cuts the node from parent's child list and attach it to root.
	 * @param node
	 */
	public void cut( FHNode node, FHNode parent ){
		if( node == null ) return;
		// Detach node from the list of child nodes
		node.getRightSibling().setLeftSibling(node.getLeftSibling());
		node.getLeftSibling().setRightSibling(node.getRightSibling());
		
		// child pointer of parent might be pointing to node
		// if so point to its right sibling
		if( parent.getChild() == node ){
			parent.setChild( node.getRightSibling());
		}
		// Check if node is the only child of parent. 
		// In this case after removal of child parent has no child. pointing it to null.
		if( node.getRightSibling() == node ){
			parent.setChild( null );
		}
		// Decrease the degree of parent
		parent.setDegree(parent.getDegree()-1);
		// Make parent pointer of child node null
		node.setParent( null );
				
		// Finally attach the node to the root list
		node.setRightSibling( minNode.getRightSibling() );
		node.setLeftSibling( minNode );
		minNode.getRightSibling().setLeftSibling( node );
		minNode.setRightSibling( node );
		// Mark the child cut value of node to be false
		node.setChildCut( false );
	}
	
	/**
	 * Cuts the node from its parent till it finds a node with a child flag = true;
	 */
	public void cascadingCut( FHNode node) {
        FHNode parent = node.getParent();
        // if parent exists
        if( parent != null ){
            if( node.getChildCut() ){ // if a child has already been cut from the parent.
                // cut the node from its parent.
            	cut( node, parent );
                // Invoke cascading cut on its parent.
                cascadingCut( parent );
            } else {  // if no child has been cut. Then mark it
                node.setChildCut(true);
            }
        }
    }

	/**
	 * Combine the trees of equal degree
	 */
	private void consolidate() {
        
		FHNode[] A = new FHNode[45];
		HashMap<Integer, FHNode> degreeTable = new HashMap<Integer, FHNode>();
		
        // For each root list node look for others of the same degree.
        FHNode start = minNode;
        FHNode curr  = minNode;
        do{
            FHNode next = curr;
            // Because x might be moved, save its sibling now.
            FHNode nextR = next.getRightSibling();
            int degree = next.getDegree();
            while(A[degree] != null){
            //while( degreeTable.containsKey(degree) && degreeTable.get(degree) != null){
                // Make one of the nodes a child of the other.
                FHNode existingNode = A[degree];
                //FHNode existingNode = degreeTable.get(degree);
            	if( next.getPriority() > existingNode.getPriority()) {
                    FHNode temp = existingNode;
                    existingNode = next;
                    next = temp;
                }
                if( existingNode == start){
                    // Because removeMin() arbitrarily assigned the min
                    // reference, we have to ensure we do not miss the
                    // end of the root node list.
                    start = start.getRightSibling();
                }
                if( existingNode == nextR ){
                    // If we wrapped around we need to check for this case.
                    nextR = nextR.getRightSibling();
                }
                // Node y disappears from root list.
                linkHeap( existingNode, next );
                // We've handled this degree, go to next one.
                A[degree] = null;
                //degreeTable.put(degree, null);
                degree++;
            }
            // Save this node for later when we might encounter another
            // of the same degree.
            A[degree] = next;
            //degreeTable.put(degree, next);
            // Move forward through list.
            curr = nextR;
        } while( curr != start);

        // The node considered to be min may have been changed above.
        minNode = start;
        // Find the minimum key again.
        for (FHNode a : A) {
            if (a != null && a.getPriority() < minNode.getPriority()) {
                minNode = a;
            }
        }
        /*Iterator<Integer> itr = degreeTable.keySet().iterator();
        while( itr.hasNext() ){
        	Integer degree = itr.next();
        	if( degreeTable.containsKey(degree) && degreeTable.get(degree) != null ){
        		if( degreeTable.get(degree).getPriority() < minNode.getPriority()){
        			minNode = degreeTable.get(degree);
        		}
        	}
        }*/
    }
	
}
