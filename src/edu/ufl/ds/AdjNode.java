package edu.ufl.ds;

import java.util.LinkedList;

public class AdjNode {

	private int vertex;
	private LinkedList<ListNode> list = null;
	
	public AdjNode( int vertex ){
		this.vertex = vertex;
		list = new  LinkedList<ListNode>();
	}

	public void addToList( ListNode node ){
		this.list.add(node);
	}
	
	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public LinkedList<ListNode> getList() {
		return list;
	}

	public void setList( LinkedList<ListNode> list) {
		this.list = list;
	}
	
}
