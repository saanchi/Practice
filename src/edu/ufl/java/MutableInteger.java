package edu.ufl.java;

public class MutableInteger {

	int value;
	
	public MutableInteger( int value ){
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void testMutableInteger( MutableInteger ob  ){
		System.out.println( " Passed value of Mutable Integer is "  + ob.value );
		ob.setValue( 50 );
		System.out.println( " Changed value inside the function is  " + ob.getValue() );
	}
	
	public void testInteger( Integer ob ){
		System.out.println( " Passed value of Mutable Integer is "  + ob.intValue() );
		ob = new Integer(ob.intValue());
		System.out.println( " Changed value inside the function is  " + ob.intValue() );
	}
	
	public static void main( String args[] ){
		int value = 100;
		MutableInteger ob = new MutableInteger(value);
		System.out.println( " Value of ob is " + value );
		ob.testMutableInteger(ob);
		System.out.println( " Changed value after function cal is  "  + ob.value);
	}
	
	
}
