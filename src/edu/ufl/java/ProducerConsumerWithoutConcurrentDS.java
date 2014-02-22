package edu.ufl.java;

import java.util.LinkedList;
import java.util.Queue;


public class ProducerConsumerWithoutConcurrentDS {
	
	public ProducerConsumerWithoutConcurrentDS(){
		Work work = new Work(10);
		Thread producer = new Thread(new Producer(work));
		Thread consumer1 = new Thread(new Consumer(work));
		Thread consumer2 = new Thread(new Consumer(work));
		consumer1.start();
		consumer2.start();
		producer.start();
	}
	
	class Work{
		Queue<Integer> queue;
		int size;
		public Work( int size ){
			this.size = size;
			queue = new LinkedList<Integer>();
		}
		
		public int consume() throws InterruptedException{
			int i=0;
			synchronized(queue){
				while( queue.size() == 0) {
					try {
						System.out.println(" Consumer Waiting ");
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Thread.sleep(1000);
				i = queue.poll();
				queue.notify();
			}
			return i;
		}
		
		public void add( int i ){
			synchronized(queue){
				while( queue.size() == size){
					try {
						System.out.println( "Producer Waiting ");
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				queue.add(i);
				queue.notify();
			}
		}
		
		public boolean isEmpty(){
			synchronized(queue){
				if( queue.size() == 0) return true;
				else return false;
			}
		}
		
		public boolean isFull(){
			synchronized(queue){
				if( queue.size() == size) return true;
				else return false;
			}
		}
		
	}
	
	class Producer implements Runnable{

		Work queue;
		public Producer( Work queue){
			this.queue = queue;
		}
		
		public void run() {
			int i=0;
			while(true){
				queue.add(i);
				System.out.println( " Produced " + i++);
			}
		}
		
	}
	
	class Consumer implements Runnable{

		Work queue;
		public Consumer( Work queue){
			this.queue = queue;
		}
		
		public void run() {
			int i=-1;
			while(true){	
				try {
					i = queue.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println( " Consumed " + i );
			}
		}
		
	}
	
	public static void main(String args[]){
		ProducerConsumerWithoutConcurrentDS ob = new ProducerConsumerWithoutConcurrentDS();
	}
	
}
