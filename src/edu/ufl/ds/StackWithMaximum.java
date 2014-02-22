package edu.ufl.ds;

public class StackWithMaximum {

	public class stack{
		private int arr[];
		private int top;
		
		public stack( ){
			arr = new int[1000];
			top = -1;
		}
		
		public void push( int val ){
			arr[++top] = val;
		}
		
		public int pop(){
			return arr[top--];
		}
		
		public int peek(){
			return arr[top];
		}
		
		public boolean isEmpty(){
			if( top == -1){
				return true;
			}
			else return false;
		}
	}

	private stack a,b;
	
	public StackWithMaximum() {
		a = new stack();
		b = new stack();
	}
	
	public int giveMax(){
		return b.pop();
	}

	public void push( int val ){
		a.push(val);
		if( !b.isEmpty()){
			if( b.peek() <= val)
				b.push(val);
		}
		else
			b.push(val);
				
	}
	
	public int pop(){
		int value = a.pop();
		if( value == b.peek() ){
			b.pop();
		}
		return value;
	}
	
	public static void main(String args[]){
		StackWithMaximum ob =  new StackWithMaximum();
		ob.push(10);
		ob.push(24);
		System.out.println(ob.giveMax());
		ob.pop();
		System.out.println(ob.giveMax());
	}
	
}
