package edu.ufl.java;

import java.util.Iterator;

public class Test1 {
	
	public static void main(String args[]){
		Test1 ob = new Test1();
		TestMap ob1 = new TestMap();
		for( int i=0; i<3; i++){
			ob1.map.put(i, new String("i"));
		}
		Iterator itr = ob1.map.keySet().iterator();
		while( itr.hasNext()){
			System.out.println(ob1.map.get(itr.next()));
		}
	}

}
