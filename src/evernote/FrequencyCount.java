package evernote;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class FrequencyCount {

	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int i=0;
		String arr[] = new String[n];
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		
		// Read input string
		while( i<n){
			arr[i]  = in.next();
			if( map.containsKey(arr[i])){
				int count = map.get(arr[i]);
				map.put(arr[i], ++count);
				i++;
				continue;
			}
			map.put(arr[i], 1);
			i++;
		}
		// Get the input k
		int k = in.nextInt();
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String,Integer>>(){

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if( o2.getValue() > o1.getValue()) return 1;
				if( o2.getValue() < o1.getValue()) return -1;
				if( o2.getValue() == o1.getValue()){
					if(o2.getKey().compareTo(o1.getKey()) < 0 ) return 1;
					else return 0;
				}
				return 0;
			}
		}
		);
		i = 0;
		for (Map.Entry<String, Integer> entry : list){
			if( i<k){
				System.out.println(entry.getKey());
				i++;
			}
		}
		in.close();
	}
	
	
}
