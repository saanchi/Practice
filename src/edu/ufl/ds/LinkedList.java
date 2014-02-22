package edu.ufl.ds;

public class LinkedList {
	
	private Node root;
	private Node last;
	
	public LinkedList(){
	}
	
	public void addNode( Node node ){
		if( root == null ){
			root = last = node;
			return;
		}
		addNodeLast(node);
	}
	
	public void addNodeLast( Node node ){
		this.last.next = node;
		this.last      = node;
	}
	
	public void printList( Node root ){
		Node temp = root;
		while( temp != null ){
			System.out.println( temp.data );
			temp = temp.next;
		}
	}
	
	public Node reverseListIter( Node root ){
		if( root == null ) return null;
		Node prev = null;
		Node curr = root;
		while( curr != null ){
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;	
	}
	
	public Node reverseListRecur( Node root ){
		if( root == null ) return null;
		Node node, next;
		node = root;
		next = node.next;
		if( next == null ) return node;
		node.next = null;
		Node reverse = reverseListRecur(next);
		next.next = node;
		return reverse;
		
	}
	
	public String check( String str){
		String string = null;
		int n = str.hashCode();
		switch( n%2 ){
		case 0:
			System.out.println("hello");
			string  ="hell";
			break;
		case 1:
			System.out.println(" bye ");
			string = "bye";
			break;
		}
		return string;
	}
	
	
	public static void main( String args[]){
		LinkedList list = new LinkedList();
		list.addNode( list.new Node(12));
		list.addNode( list.new Node(10));
		list.addNode( list.new Node(15));
		list.addNode( list.new Node(20));
		//list.printList( list.root );
		//list.printList( list.reverseListIter(list.root));
		list.printList( list.reverseListRecur(list.root));
		System.out.println( list.check(null));
	}
	
	public class Node{

		int data;
		Node next;
		
		public Node( int data ){
			this.data = data;
			next = null;
		}
	}
}
