package edu.ufl.misc;

import java.util.HashMap;

public class ArrayQuestions {
	
	public boolean search( int arr[], int element ){
		boolean isFound = false;
		if( arr.length == 0 ) return isFound;
		int low = 0;
		int high = arr.length-1;
		int mid = ( low + high )/2;
		while( low <= high ){
			if( element == arr[mid] ) {
				return true;
			}
			else if( element > arr[mid] ){
				low = mid+1;
				mid = ( low + high )/2;
			}
			else{ 
				high = mid-1;
				mid = ( low + high )/2;
			}
		}
		return isFound;
	}
	
	public int[] twoSum( int[] numbers, int target ){
		int arr[]  = new int[3];
		int index1 = 0;
		int index2 = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for( int i=0; i< numbers.length; i++){
			map.put( new Integer(numbers[i]), new Integer(i+1));
		}
		for( int i=0; i< numbers.length; i++){
			if( map.containsKey( target - numbers[i] )){
				index2 = map.get(target - numbers[i]);
				if( i+1< index2 && index2 != 0){
					index1 = i+1;
				}
				else{
					index1 = index2;
					index2 = i+1;
				}
				break;
			}
		}
		arr[0] = index1;
		arr[1] = index2;
		return arr;
	}
	
	public int singleNumber( int A[]){
		int singleNumber = A[0];
        for( int i=1; i<A.length; i++){
            singleNumber  = singleNumber ^ A[i];
        }
        return singleNumber;
	}
	
	public int reverse( int x ){
		int reverse = 0;
		int length = 1;
		int temp ;
		int isNegative = 0;
		if( x < 0 ){
			isNegative = 1;
			x = x*( -1 );
		}
		temp = x;
		// Get the length of the number
		while(( temp = temp/10) > 0)	length++;
		temp = 1; 
		int i = length;
		// compute the 10th power 
		while( i >1 ){
			temp = temp*10;
			i--;
		}
		for( i= length-1; i>=0; i--){
			int val = x%10;
			x = x/10;
			reverse = reverse + temp*val;
			temp = temp/10;
			System.out.println(reverse);
		}
		if( isNegative == 1 ) reverse = reverse * -1;
		return reverse;
	}

	public int numTrees( int n ){
		int arr[] = new int[n+3];
		arr[0] = 1;
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 5;
		if( n>=4 ){
			for( int i=4; i<=n; i++){
				for( int j = i-1; j>=0; j-- ){
					arr[i] = arr[i] + ( arr[j] * arr[i-j-1] );
				}
			}
		}
		return arr[n];
	}
	
	public double pow( double x, int n){
		if( x == 0) return 0;
		if( n == 0) return 1;
		if( n == 1) return x;
		double temp = pow( x, n/2 );
		if( n%2 == 0 ) return temp*temp;
		else{
			if( n > 0 )
				return temp * temp * x;
			else 
				return temp*temp/x;
		}
		
	}
	
	public int removeElement(int []A, int elem ){
		if( A.length == 0 )  return 0;
		int len = A.length;
		int lastIndex = len - 1;
		while( lastIndex <0 && A[lastIndex] == elem ) lastIndex--;
		if( lastIndex < 0 ) return 0;
		for( int i=0; i<= lastIndex; i++ ){
			if( A[i] == elem ) {
				int temp = A[i];
				A[i]     = A[lastIndex];
				A[lastIndex--] = temp;
			}
		}
		return lastIndex+1;
	}
	
	public int maxProfit(int[] prices) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int maxNo, maxDiff;
        if( prices.length == 0) return 0;
        if( prices.length == 1 ) return 0;
        maxNo = prices[prices.length-1];
        maxDiff = 0;
        for( int i = prices.length - 2; i>=0; i--){
            if( prices[i] > maxNo ) maxNo = prices[i];
            else {
                int temp = maxNo - prices[i];
                if( temp > maxDiff ){
                  maxDiff = temp;
                 } 
            }
        }
        return maxDiff;
    }
	
	//public int maxProfit2( int prices[]){
	//}
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if( matrix.length == 0 ) return false;
		int nRows, nColumns;
		
		nRows = matrix.length;
		nColumns = matrix[0].length;
		
		int lowR, highR, midR;
		lowR = 0; highR = nRows-1;
		midR = ( lowR + highR )/2;
		
		int lowC, highC, midC;
		lowC = 0; highC = nColumns-1;
		midC = ( lowC + highC )/2;
		
		// Extract the value of Row where the value should be searched
		while( lowR <= highR ){
			if( matrix[midR][0] == target ){
				return true;
			}
			else if( target < matrix[midR][0] ){
				highR = midR - 1;
				midR  = ( lowR + highR )/2;
			}
			else if( target > matrix[midR][0] ){
				lowR = midR + 1;
				midR = ( lowR + highR )/2;
			}
		}
		if( lowR > 0) lowR--;
		if( lowR >= nRows  ||  lowR < 0) return false;
		// Now search the value in the corresponding Row which is given by lowR
		while( lowC <= highC ){
			if( matrix[lowR][midC] == target ){
				return true;
			}
			else if( target < matrix[lowR][midC] ){
				highC = midC-1;
				midC = ( lowC + highC )/2;
			}
			else if( target > matrix[lowR][midC] ){
				lowC  = midC + 1;
				midC  = ( lowC + highC )/2;
			}
		}
		
		return false;
    }
	
	public int searchInsert(int[] A, int target) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if( A.length == 0) return 0;
        int mid, low, high;
        low = 0;
        high = A.length - 1 ;
        mid  = ( low + high )/2;
        while( low <= high ){
            if( A[mid] == target ) return mid;
            else if( target < A[mid]  ){
                high = mid -1;
                mid  = ( low + high )/2;
            }
            else if( target > A[mid]){
                low = mid +1;
                mid  = ( low + high )/2;
            }
        }
        return low;
    }
	
	public char searchInsertChar(char[] A, char target) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if( A.length == 0) return 0;
        int mid, low, high;
        low = 0;
        high = A.length - 1 ;
        mid  = ( low + high )/2;
        while( low <= high ){
            if( A[mid] == target ) return A[mid];
            else if( target < A[mid]  ){
                high = mid -1;
                mid  = ( low + high )/2;
            }
            else if( target > A[mid]){
                low = mid +1;
                mid  = ( low + high )/2;
            }
        }
        return A[low];
    }

	
	
	public int[] plusOne(int[] digits) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int result[] = new int[digits.length];
        int carry = 0;
        if( digits[digits.length-1] == 9 ){
        	result[digits.length-1] = 0;
        	carry = 1;
        }
        else result[digits.length - 1 ] = digits[digits.length -1];
        for( int i= digits.length -2; i>=0; i-- ){
            if( digits[i] == 9 && carry == 1 ){
              result[i] = 0;
              carry = 1;
            }
            else if( carry == 1 ){
                result[i] = digits[i] + 1;
                if( digits[i] == 9 ) carry = 1;
                else carry = 0;
            }
            else result[i] = digits[i];
            
        }
        int result1[];
        if( carry == 1 && result[0] == 9){
            result1 = new int[digits.length+1];
            result1[0] = 1;
            for( int i=1; i<= digits.length; i++){
            	result1[i]= result[i]; 
            }
            return result1;
        }
        return result;
    }
	
	public int binarySearch( int arr[], int n){
		int start, mid, end;
		start = 0; end = arr.length - 1;
		mid = start + ( end - start )/2;
		while( start <= end ){
			if( arr[mid] == n ) return mid;
			if( arr[mid] < n ) start = mid+1;
			if( arr[mid] > n ) end = mid-1;
			mid = start + (end - start)/2;
		}
		return -1;
	}
	
	public int findLowestInRotated( int arr[] ){
		int start, end, mid;
		start = 0; end = arr.length - 1;
		if( end == 0 ) return arr[0]; // if one element just return that element
		mid = ( start + end )/2;
		while( start <= end ){
			if( arr[start] < arr[end]) return arr[0];  // if array is not rotated at all
			if( mid<end && arr[mid] > arr[mid+1]) return arr[mid+1]; 
			if( start<mid && arr[mid-1] > arr[mid]) return arr[mid];
			if( arr[start] > arr[mid]) end = mid-1;
			if( arr[end] < arr[mid]) start = mid+1;
		}
		return arr[0];
		
	}
	
	public int uniquePaths(int m, int n) {
        if( m ==0 || n == 0) return 0;
        if( m== 1 || n ==1 ) return 1;
        int arr[][] = new int[m][n];
        for( int i =0; i<m; i++){
            arr[i][0] = 1;
        }
        for( int j=0; j<n; j++){
            arr[0][j] = 1;
        }
        for( int i=1; i<m; i++){
            for( int j=1; j<n; j++){
                arr[i][j] = arr[i][j-1] + arr[i-1][j];
            }
        }
        return arr[m-1][n-1];
    }
	
	public int uniquePaths( int obstacleGrid[][]){
		if( obstacleGrid == null ) return 0;
		int m,n;
		boolean flag = false;
		m = obstacleGrid.length;
		n = obstacleGrid[0].length;
		int paths[][] = new int[m][n];
		if( m == 0 || n == 0) return 0;
		for( int i=0; i<m; i++ ){
			if( obstacleGrid[i][0] == 1 ) flag = true;
			if(flag) paths[i][0] = 0;
			else paths[i][0] = 1;
		}
		flag = false;
		for( int j=0; j<n; j++ ){
			if( obstacleGrid[0][j] == 1 ) flag = true;
			if( flag ) paths[0][j] = 0;
			else paths[0][j] = 1;
		}
		for( int i=1; i<m; i++){
			for( int j=1; j<n; j++){
				if( obstacleGrid[i][j] == 1) paths[i][j] = 0;
				else paths[i][j] = paths[i-1][j] + paths[i][j-1];
			}
		}
		return paths[m-1][n-1];
	}
	
	public static void main( String args[] ){
		ArrayQuestions aq = new ArrayQuestions();
		int arr[] = {5,75,25};
		int target 	 = 100;
		int output [] = aq.twoSum( arr, target);
		//System.out.println( output[0] + " " + output[1] );
		int A[] = { 1,2,3,4,3,2,2 };
		//System.out.println( aq.singleNumber(A));
		//System.out.println( aq.reverse(-10) );
		//System.out.println( aq.numTrees(5));
		//System.out.println( aq.pow(2, -1));
		//System.out.println( aq.removeElement(A,2));
		int B[] = {};
		//System.out.println( aq.maxProfit(B));
		int C[][] = { {1,3}};
		//System.out.println( aq.searchMatrix(C, 2));
		//System.out.println( C.length);
		int D[] = { 1};
		//System.out.println(aq.search(D, 12));
		int arr1[] = {9, 9, 9, 9, 9 };
		//System.out.println( aq.searchInsert(arr1, 10));
		int result[] = aq.plusOne(arr1);
		//for( int i =0; i<result.length; i++){
		//	System.out.print( result[i]  );
		//}
		//int arr2[] = { 1,2,3,4,5,6,7,8,9};
		//System.out.println( aq.binarySearch(arr2, 11));
		int arr3[] = { 1};
		//System.out.println(aq.findLowestInRotated(arr3) );
		//System.out.println(aq.uniquePaths(10, 10));
		char chArr[] = { 'a', 'd', 'e' ,'f' , 'k', 'm', 'x' ,'z'  };
		System.out.println(aq.searchInsertChar(chArr, 'b'));
	}
	
	
	
}