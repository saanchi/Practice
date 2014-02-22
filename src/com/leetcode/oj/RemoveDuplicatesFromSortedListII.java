package com.leetcode.oj;

public class RemoveDuplicatesFromSortedListII {

	public class ListNode {
	   int val;
	   ListNode next;
	   ListNode(int x) {
		   val = x;
		   next = null;
	   }
	}
	
	/**
	 * @param head
	 * @return
	 */
	// Case 1: 1>2>3>4       : same as input
	// Case 2: 1>2>2>3>4     : 1>3>4
	// Case 3: 1>2>2>3>3>4>5 : 1>4>5
	// Case 4: 1>1>2>3>3>    : 2
	// Case 5: 1>1>2>2       : null
	// Case 6: 1>2>2>2>3>3>4>5 : 1>4>5
	public ListNode deleteDuplicates(ListNode head) {
		if( head == null) return null;
		if( head.next == null ) return head;
		ListNode start = null , curr = null, previousNode = null;
		start = new ListNode(0);
		previousNode = start;
		while( curr != null && curr.next != null ){
			// if the current value is not equal to the next value
			if( curr.val != curr.next.val){
				previousNode = curr;
			}
			else{
				while( curr.next != null && curr.next.val != curr.val ){
					curr = curr.next;
				}
				previousNode.next = curr;
			}
			curr = curr.next;
		}
		return start.next;
	}
	
}
