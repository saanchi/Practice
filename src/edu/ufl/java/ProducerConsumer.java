package edu.ufl.java;

import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {
	
	public ProducerConsumer(LinkedBlockingQueue<Integer> queue){
		Thread producer = new Thread(new Producer(queue));
		Thread consumer = new Thread(new Consumer(queue));
		consumer.start();
		producer.start();
	}
	
	class Producer implements Runnable{

		LinkedBlockingQueue<Integer> queue;
		public Producer( LinkedBlockingQueue<Integer> queue){
			this.queue = queue;
		}
		
		public void run() {
			int i=0;
			while(true){
				try {
					queue.put(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println( " Produced " + i++ );
			}
		}
		
	}
	
	class Consumer implements Runnable{

		LinkedBlockingQueue<Integer> queue;
		public Consumer( LinkedBlockingQueue<Integer> queue){
			this.queue = queue;
		}
		
		public void run() {
			int i;
			while(true){	
				try {
					i = queue.take();
					System.out.println( " Consumed " + i );
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String args[]){
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		ProducerConsumer ob = new ProducerConsumer(queue);
	}
	
}
