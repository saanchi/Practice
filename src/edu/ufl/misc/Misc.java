package edu.ufl.misc;

import java.util.ArrayList;
import java.util.Iterator;

public class Misc {

	public static void main( String args[]){
		char arr[] = new char[20];
		arr[0] = '1';
		String str = arr.toString();
		Misc ob = new Misc();
		int matrix[][] = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20}};
		ArrayList<Integer> list = ob.spiralOrder(matrix);
		Iterator<Integer> itr = list.iterator();
		while( itr.hasNext()){
			System.out.println(itr.next());
		}
	}
	
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int m = matrix.length;
        if( m==0 ) return list;
        int n = matrix[0].length;
        if( n == 0) return list;
        if( m ==1 && n == 1 ){
            list.add(matrix[0][0]);
            return list;
        }
   
        for( int layer =0; layer<m/2+1; layer++){
            for( int i= layer; i<n-layer-1; i++){
                list.add( matrix[layer][i]);
            }
            for( int j=layer; j<m-1-layer; j++){
                list.add( matrix[j][n-1-layer]);
            }
            for( int k= n-1-layer; k>layer; k--){
                list.add( matrix[m-1-layer][k]);
            }
            for( int l=m-1-layer; l> layer; l--){
                list.add( matrix[l][layer]);
            }
        }
        if( m%2 == 1){
        	list.add(matrix[m/2][n/2]);
        }
        return list;
    }
	
}
