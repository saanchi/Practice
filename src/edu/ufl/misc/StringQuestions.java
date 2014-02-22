package edu.ufl.misc;

import java.util.StringTokenizer;

public class StringQuestions{

	private String string;
	
	public StringQuestions( String string ){
		this.string = string;
	}
	
	public StringQuestions(){	}
	
	public String reverseString( String string ){
		int length = string.length();
		char str[] = string.toCharArray();
		int i=0, j = length-1;
		for( i=0; i<=j ; i++,j--){
			char temp = str[i];
			str[i]    = str[j];
			str[j]    = temp;
		}
		String newString = new String(str);
		return newString;
	}
	
	public boolean hasUniqueCharacters( String string ){
		boolean map[];
		char str[] = string.toCharArray();
		map = new boolean[256];
		for( int i=0; i<256; i++ )  map[i] = false;
		for( int i=0; i<string.length(); i++ ){
			if(map[str[i]] == true ) return false;
			else map[str[i]] = true;
		}
		return true;
	}
	
	public char firstNonRepeatedCharacter( String string ){
		char arr[] = string.toCharArray();
		char map[] = new char[256];
		char ch = '\0';
		for( int i=0; i<arr.length; i++){
			map[arr[i]]++;
		}
		for(int i=0;i <256; i++ ){
			if( map[arr[i]] == 1 ){
				System.out.println( "First non repeated character " + arr[i]);
				ch = arr[i];
				break;
			}
		}
		return ch;
	}
	
	public char maxOccurChar( String str ){
		if( str == null ) return '\0';
		char arr[] = str.toCharArray();
		char charSet[] = new char[256];
		int max = 0; char maxChar = '\0';
		int len = str.length();
		for( int i=0; i<len; i++){
			charSet[arr[i]]++;
		}
		for( int i=0; i<256; i++){
			if(charSet[i] > max ){
				max = charSet[i];
				maxChar = (char)i;
			}
		}
		return maxChar;
	}
	
	public boolean isAnagram( String str1, String str2 ){
		int len1 = str1.length();
		int len2 = str2.length();
		if( len1 != len2 ) return false;
		char charSet[] = new char[256];
		for( int i =0; i<len1; i++){
			charSet[str1.charAt(i)]++;
		}
		for( int j=0; j<len2; j++){
			if( charSet[str2.charAt(j)] == 0 ){
				return false;
			}
			else{
				charSet[str2.charAt(j)]--;
			}
		}
		return true;
	}
	
	public void swap( char arr[], int i, int j){
		char temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public void permuteString( char[] str, int i, int len ){
		if( str == null ) return;
		if( i == len-1 ) System.out.println( new String(str) );
		for( int j=i; j<len; j++){
			swap( str, i, j);
			permuteString(str, i+1, len);
			swap( str, i, j);
		}
	}
	
	public String itoa( int n ){
		if( n==0) return "0";
		if( n == Integer.MIN_VALUE ) return "";
		int sgn=0,i=0;
		char buf[] = { '0','1','2','3','4','5','6','7','8','9'};
		char arr[] = new char[12];
		if( n< 0){
			sgn = -1;
			n=-n;
		}
		while( n > 0){
			//arr[i++] = buf[n%10];
			arr[i++] = (char)( 48 + n%10);
			n = n/10;
		}
		if( sgn < 0 ) arr[i++] = '-';
		i--;
		for( int j=0; j<i; j++, i--){
			char tmp = arr[j];
			arr[j]   = arr[i];
			arr[i]   = tmp;
		}
		return new String(arr);
	}
	
	public String itoa2( int n ){
		if( n==0) return "0";
		int sgn=0,i=0;
		char buf[] = { '0','1','2','3','4','5','6','7','8','9'};
		char arr[] = new char[12];
		if( n < 0) sgn = -1;
		while( n != 0){
			//arr[i++] = buf[n%10];
			arr[i++] = (char)( 48 + Math.abs(n%10));
			n = n/10;
		}
		if( sgn < 0 ) arr[i++] = '-';
		i--;
		for( int j=0; j<i; j++, i--){
			char tmp = arr[j];
			arr[j]   = arr[i];
			arr[i]   = tmp;
		}
		return new String(arr);
	}
	
	public boolean isdigit( char ch){
		if( (ch - '0') >= 0 && (ch - '0') <= 9){
			return true;
		}
		return false;
	}
	
	public int atoi( String s) throws Exception {
		if( s == null) throw new NumberFormatException(" Empty String");
		boolean isNegative = false;
		int index=0, number=0;
		char arr[] = s.toCharArray();
		int len = arr.length;
		switch( arr[0]){
			case '-' :
				isNegative = true; index++;break;
			case '+' : index++; break;	
		}
		if( index == len) throw new NumberFormatException(" Invalid arguments ");
		for( int i=index; i<len; i++){
			if( isdigit(arr[i])){
				number = number*10 + arr[i] - '0';
				if( number < 0) throw new NumberFormatException(" Overflow ");
			}
			else throw new NumberFormatException( " Invalid digits ");
		}
		return isNegative ? -1*number : number;
	}
	
	public boolean isPalindrome(String s) {
        int len = s.length();
        if( len == 0) return true;
        String str = s.toLowerCase();
        int start = 0, end = len-1;
        char sch = str.charAt(start);
        char endch = str.charAt(end);
        boolean flag = false;
        while( start < end ){
            while( (start < len-1 ) && ((sch<48 ||  sch > 122 ) || ( sch>57 && sch< 97 ))){
              start++;
              sch = str.charAt(start);  
            } 
            while( (end >0 ) && ((endch<48 ||  endch > 122) || ( endch>57 && endch< 97 ))){
              end--;
              endch = str.charAt(end);  
            }
            if( start<= end){
                if(sch != endch)  return false;
                if( sch == endch ) flag = true;
            }
            
            start++; end--;
        }
        if(flag ) return true;
        return false;
    }
    
	public String reverseWords( String str ){
		int len = str.length();
		if( len == 0) return "";
		int index=0,i=0;
		char ch[] = str.toCharArray();
		reverseWord(ch, 0, len-1);
		System.out.println(new String(ch));
		while( i < len ){
			while( ch[i] != ' ' || ch[i] != '\t' || ch[i] != '\n' ) i++;
			reverseWord(ch, index, i-1);
			index = i;
			i++;
		}
		return new String(ch);
	}
	
	public String reverseWord( char str[], int start, int end ){
		if( start == end) return "";
		while( start < end){
			char tmp = str[start];
			str[start] = str[end];
			str[end]   = tmp;
			start++; end--;
		}
		return new String(str);
	}
	
	public static void main( String args[] ) throws Exception{
		StringQuestions ob = new StringQuestions();
		//ob.firstNonRepeatedCharacter("abacdedfavncbebcdedb");
		//System.out.println(ob.reverseString("abcd xyz 123 mno pqrstu"));
		//System.out.println(ob.hasUniqueCharacters(""));
		//System.out.println(" " + ob.maxOccurChar(""));
		//System.out.println( "" + ob.isAnagram("abaccccaa", "acbaaaccc"));
		//ob.permuteString("abc".toCharArray(), 0, 3);
		//System.out.println(ob.itoa2(Integer.MIN_VALUE));
		System.out.println(ob.itoa2(1223));
		//System.out.println(ob.isdigit('1'));
		//System.out.println(ob.atoi("-1938383838338"));
		//System.out.println(ob.isPalindrome(".......a.........a,......"));
		//System.out.println(ob.reverseWord("abcd".toCharArray(), 1,3));
		//System.out.println(ob.reverseWords("ab cd"));
	}
	
}
