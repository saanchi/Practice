package edu.ufl.ds;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import edu.ufl.java.MutableBoolean;
import edu.ufl.java.MutableInteger;

public class BST<K extends Comparable<K>, V> {

	private class Node<K,V>{
		private K key;
		private V value;
		private Node<K,V> left;
		private Node<K,V> right;
		
		public Node( K key, V value ){
			this.key   = key;
			this.value = value;
			this.left  = null;
			this.right = null;
		}
	}
	
	private Node<K,V> root;
	
	public BST(){
	}
	
	public BST( K key, V value ){
		this.root = new Node<K, V>(key, value);
	}
	
	public Node<K,V> insert( K key, V value ){
		return addNode( key, value, root);
	}
	
	public Node<K,V> addNode( K key, V value, Node<K,V> node ){
		if( node == null ) return new Node<K, V>(key, value);
		if( node.key.compareTo( key ) > 0 ) node.left  = addNode(key, value, node.left );
		if( node.key.compareTo( key ) < 0 ) node.right = addNode(key, value, node.right);
		if( node.key.compareTo( key ) == 0) node.value = value;
		return node;
	}
	
	public void inorderTraversal(){
		recInOrderTraversal( root );
		iterInOrderTraversal( root );
	}
	
	public void recPreorderTraversal( Node<K,V> node ){
		if( node == null ) return;
		System.out.println( node.key );
		recPreorderTraversal(node.left);
		recPreorderTraversal(node.right);
	}
	
	public void recInOrderTraversal( Node<K,V> node ){
		if( node == null ) return;
		recInOrderTraversal( node.left );
		System.out.println( node.key );
		recInOrderTraversal( node.right );
	}
	
	public void recPostOrderTraversal( Node<K,V> node ){
		if( node == null ) return;
		recPostOrderTraversal( node.left );
		recPostOrderTraversal( node.right );
		System.out.println( node.key );
	}
	
	public void iterPreorderTraversal( Node<K,V> node ){
		Stack<Node<K,V>> stack = new Stack<Node<K,V>>();
		stack.push( node );
		while( !stack.isEmpty()){
			Node<K,V> temp = stack.pop();
			System.out.println( temp.key );
			if( temp.right != null ) stack.push( temp.right );
			if( temp.left != null  ) stack.push( temp.left );
		}
	}
	
	public void iterInOrderTraversal( Node<K,V> node ){
		if( node == null ) return;
		Stack<Node<K,V>> stack = new Stack<Node<K,V>>();
		Node<K,V> current = node;
		boolean isFinished = false;
		while( !isFinished ){
			if(current != null ){
				stack.push(current.left);
				current  = current.left;
			}else{
				if( !stack.isEmpty()){
					current = stack.pop();
					System.out.println( current.key );
					current  = current.right;
				}
				else{
					isFinished = true;
				}
			}
		}
	}
	
	// Using two stacks
	public void iterPostOrderTraversal( Node<K,V> node ){
		Stack<Node<K,V>> parent = new Stack<Node<K,V>>();
		Stack<Node<K,V>> child  = new Stack<Node<K,V>>();
		child.push(node);
		while( !child.isEmpty()){
			Node<K,V> temp = child.pop();
			parent.push(temp);
			if( temp.left != null )  child.push(temp.left);
			if( temp.right != null ) child.push(temp.right);
		}
		while( !parent.isEmpty()){
			System.out.println(parent.pop().key);
		}
	}
	
	public int size( Node<K,V> node ){
		if( node == null ) return 0;
		int sizeL = size( node.left );
		int sizeR = size( node.right );
		return sizeL + sizeR + 1;
	}
	
	public void levelOrder(){
		levelOrder(root);
	}
	
	public void levelOrder( Node<K,V> node ){
		if( node == null ) return;
		Queue<Node<K,V>> queue = new ArrayDeque<Node<K,V>>();
		queue.add(node);
		while(!queue.isEmpty()){
			Node<K,V> current = queue.poll();
			System.out.println(current.key);
			if( node.left != null ) queue.add(node.left);
			if( node.right != null) queue.add(node.right);
		}
	}
	
	public K min(){
		return min( root );
	}
	
	public K min( Node<K,V> node){
		if(node.left == null) return node.key;
		return min( node.left );
	}
	
	public K max(){
		return max(root);
	}
	
	public K max( Node<K,V> node){
		if( node.right == null ) return node.key;
		return max( node.right );
	}
	
	public boolean isBST(){
		K min,max;
		min = min();
		max = max();
		return isBST( root, min, max);
	}
	
	public boolean isBST( Node<K,V> node, K min, K max  ){
		if( node == null ) return true;
		if( node.key.compareTo( min ) > 0 && node.key.compareTo( max ) < 0 )
			return isBST( node.left, min, node.key ) && isBST( node.right, node.key, max );
		else return false;
	}
	 
	public Node<K,V> lcaBST( Node<K,V> node, K key1, K key2 ){
		if( node == null ) return null;
		if( node.key.compareTo(key1) == 0 || node.key.compareTo(key2) == 0 ) return node;
		if( node.left != null ){
			if( node.left.key.compareTo(key1) == 0 || node.left.key.compareTo(key2) == 0 ) return node;
		}
		if( node.right != null ){
			if( node.right.key.compareTo(key2) == 0 || node.right.key.compareTo(key1) == 0 ) return node;
		}
		if(( node.key.compareTo(key1) < 0 && node.key.compareTo(key2) > 0 ) || ( node.key.compareTo(key1) > 0 && node.key.compareTo(key2) < 0 ))  return node;
		if( node.key.compareTo(key1) < 0 && node.key.compareTo(key2) < 0 ) return lcaBST( node.right, key1, key2);
		if( node.key.compareTo(key1) > 0 && node.key.compareTo(key2) > 0 ) return lcaBST( node.left, key1, key2 );
		return null;
	}
	
	public boolean isNodePresent(Node<K,V> node, K key ){
		if( node == null ) return false;
		if( node.key.compareTo(key) == 0 ) return true;
		else return( isNodePresent(node.left, key) || isNodePresent(node.right, key));
	}
	
	public Node<K,V> lcaBT(  Node<K,V> node, K key1, K key2 ){
		if( node == null ) return null;
		if( node.key.compareTo(key1) == 0 || node.key.compareTo(key2) == 0)	return node;
		if( isNodePresent(node.left, key1 ) && isNodePresent(node.right, key2)) return node ;
		else if( isNodePresent(node.left, key1) && isNodePresent(node.left, key2)) return lcaBT(node.left, key1, key2);
		else if( isNodePresent(node.right, key2) && isNodePresent(node.right, key2)) return lcaBT(node.right, key1, key2);
		else return null;
	}
	
	public Node<K,V> lcaBTOpt( Node<K,V> node, K key1, K key2){
		if( node == null ) return null;
		if( node.key.compareTo(key1) ==0 || node.key.compareTo(key2) == 0 ) return node;
		Node<K,V> left   = lcaBTOpt( node.left, key1, key2 );
		Node<K,V> right  = lcaBTOpt( node.right, key1, key2 );
		if( left != null && right != null ) return node;
		else return left != null ? left : right;
	}
	
	public void treeToLinkList(){
		
	}
	
	// returns the size of the maximum BST in a BT  
	public int maxBST(  Node<K,V> node, K maxValue, K minValue, MutableBoolean isBST, MutableInteger maxSize ){
		if( node == null ) {
			isBST.setValue( true );
			return 0;
		}
		boolean isLeftBST  = false;
		boolean isRightBST = false;
		int leftSize, rightSize;
		
		int lSize = maxBST( node.left, maxValue, minValue, isBST, maxSize );
		if( isLeftBST && node.key.compareTo(maxValue) > 0 ){
			maxValue = node.key;
			leftSize = maxSize.getValue();
		}
		int rSize = maxBST( node.right, maxValue, minValue, isBST, maxSize );
		if( isRightBST && node.key.compareTo(minValue) < 0 ){
			minValue = node.key;
			rightSize = maxSize.getValue();
		}
		if( lSize == 0 && rSize == 0 ){
			maxValue = node.key;
			minValue = node.key;
			isBST.setValue(true);
			return 1;
		}
		if( node.key.compareTo( maxValue) > 0 && node.key.compareTo( minValue ) < 0 )
			return lSize + rSize + 1;
		
		
	}
	
	
	public static void main( String args[] ){
		
	}
	
	
}
