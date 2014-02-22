package edu.ufl.tc;

public class OneDimensionalRobotEasy {

	public int finalPosition(String commands, int A, int B){
		int x = 0;
		int pos = 0;
		if( commands.length() == 0) return 0;
		for( int i=0; i<commands.length(); i++){
			if( commands.charAt(i) == 'L' && (pos-1) >= -A  ){
				pos--;
			}
			else if( commands.charAt(i) == 'R' && (pos+1) <=B ){
				pos++;
			}
			System.out.println(pos);
		}
		
		return Math.abs(pos);
	}
	
	public static void main( String args[]){
		OneDimensionalRobotEasy ob = new OneDimensionalRobotEasy();
		ob.finalPosition("LLLLLLLLLLR", 2, 6);
	}
	
}
