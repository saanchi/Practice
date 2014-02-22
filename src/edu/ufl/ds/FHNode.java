package edu.ufl.ds;

public class FHNode {
	
	public FHNode( int data, int priority ){
		this.data = data;
		this.priority = priority;
		this.child = null;
		this.parent = null;
		this.rightSibling = this;
		this.leftSibling = this;
		this.degree = 0;
		this.childCut = false; // false means no child has been cut since it has been attached to its parent.
							   // true means one child has been cut.	
	}
	
	private int degree;
	private int data;
	private int priority;
	private FHNode child;
	private FHNode parent;
	private FHNode rightSibling;
	private FHNode leftSibling;
	private boolean childCut;
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public boolean getChildCut() {
		return childCut;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public FHNode getChild() {
		return child;
	}
	public void setChild(FHNode child) {
		this.child = child;
	}
	public FHNode getParent() {
		return parent;
	}
	public void setParent(FHNode parent) {
		this.parent = parent;
	}
	public FHNode getRightSibling() {
		return rightSibling;
	}
	public void setRightSibling(FHNode rightSibling) {
		this.rightSibling = rightSibling;
	}
	public FHNode getLeftSibling() {
		return leftSibling;
	}
	public void setLeftSibling(FHNode leftSibling) {
		this.leftSibling = leftSibling;
	}
	public void setChildCut(boolean childCut) {
		this.childCut = childCut;
	}

}
