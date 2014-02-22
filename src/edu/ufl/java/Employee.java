package edu.ufl.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

public class Employee implements Comparable<Employee> {

	int id;
	String name;
	int salary;
	
	public Employee( int id, String name, int salary){
		this.id     = id;
		this.name   = name;
		this.salary = salary;
	}
	
	public static final Comparator<Employee> SalaryComparator = new Comparator<Employee>() {
		
		public int compare(Employee o1, Employee o2) {
			return  o1.salary < o2.salary ? -1 : o1.salary > o2.salary ? 1 : 0;
		}
	};
	
	public int compareTo(Employee o) {
		return this.id < o.id ? -1 : this.id > o.id ? 1 :0;
	}
	
	public static void main( String args[]){
		Employee ob1 = new Employee(1, "name1", 923);
		Employee ob2 = new Employee(2, "name2", 223);
		ArrayList<Employee> list = new ArrayList<Employee>();
		list.add(ob1);
		list.add(ob2);
		Collections.sort(list);
		Iterator<Employee> itr = list.iterator();
		while( itr.hasNext()){
			System.out.println(itr.next().id);
		}
		Collections.sort(list, SalaryComparator);
		itr = list.iterator();
		while( itr.hasNext()){
			System.out.println(itr.next().id);
			itr.remove();
		}
		itr = list.iterator();
		while( itr.hasNext()){
			System.out.println(itr.next().id);
		}
	}
	
}