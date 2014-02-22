package evernote;

import java.util.Scanner;

public class CircularBuffer {

	// implementation of an array based circular queue
	class Queue{
		
		int MAXSIZE ;
		String arr[];
		int head;
		int tail;
		int size; // to keep track of number of elements added by now.
		
		public Queue( int n ){
			
			MAXSIZE = n  ;
			arr = new String[MAXSIZE];
			head = -1;
			tail = -1;
			size = 0;
		}
		
		public void add( String string){
			tail = (tail+1)%MAXSIZE;
			arr[tail] = string;
			if( size == MAXSIZE ) head++; 
			if( size < MAXSIZE ) size++;
		}
		
		public String remove(){
			int index = head + 1;
			head = (index)%MAXSIZE;
			String value = arr[head];
			arr[head] = null;
			size--;
			return value;
		}
		
		public void list(){
			int index = (head+1)%MAXSIZE;
			int i = 0;
			while( i<size){
				System.out.println(arr[index]);
				index = (index+1)%MAXSIZE;
				i++;
			}
		}
	}
	
	Queue queue;
	
	public CircularBuffer( int n){
		queue = new Queue(n);
	}
	
	public void addBuffer(String string){
		queue.add(string);
	}
	
	public void list(){
		queue.list();
	}
	
	public void remove(){
		queue.remove();
	}
	
	public static void main( String args[]){
		Scanner in  = new Scanner(System.in);
		int n = in.nextInt();
		CircularBuffer ob = new CircularBuffer(n);
		//Read next line
		String line ;
		String arr[] ;
		String command ;
		int  number =-1;
		int i=0;
		while((line = in.nextLine()) != null ){
			arr = line.split(" ");
			command = arr[0];
			number = arr.length == 2 ?  Integer.parseInt(arr[1]) : -1;
			i = 0;
			if( command.equalsIgnoreCase("Q")){
				break;
			}
			else if( command.equalsIgnoreCase("L")){
				ob.list();
			}
			else if( command.equalsIgnoreCase("A")){
				while(i<number){
					ob.addBuffer(in.next());
					i++;
				}
			}
			else { // remove elements
				while( i<number){
					ob.remove();
					i++;
				}
			}
			
		}
		
		in.close();
	
	
	
	}
	
}
