	package edu.ufl.cise.codeval;
	
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	
	public class GeneratePrimes {
	
		public String generatePrime( int n ){
			boolean composite[] = new boolean[n+1];
			int sqrt = (int)Math.sqrt(n);
			for ( int i=2; i<=sqrt; i++ ){
				for( int j=2*i; j<=n; j = j+i){
					composite[j] = true;
				}
			}
			StringBuilder output = new StringBuilder();
			for( int i=2; i<n; i++){
				if( composite[i] != true){
					output.append(i + ",");
				}
			}
			
			return output.toString();
		}
	
		public static void main(String args[]){
			GeneratePrimes ob = new GeneratePrimes();
			String inputFileName  = args[0];
			BufferedReader br = null;
			try {
				 br = new BufferedReader(new FileReader(inputFileName));
				 String sentence = null;
				 while((sentence = br.readLine()) != null){
					 int n = Integer.parseInt(sentence);
					 String output = ob.generatePrime(n);
					 System.out.println(output.substring(0, output.length()-1));
				 }
			}catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			
		}
		
	}
