package edu.ufl.java;

public class Derived extends Base {

	static {
        String category = "static blocks";
        System.out.println("Static blocks gets executed first");
    }

	public static String str = "static string";
	
	public static void functionB(){
		System.out.println(" In class Derived Function B");
	}
	
	public static void main(String args[]){
		Derived dob = null;
		System.out.println(str);
	}
}
