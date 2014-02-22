package com.leetcode.oj;

public class RemoveDuplicatesFromSortedList {
	
	class ListNode{
		ListNode next;
		int val;
	}
	
	public ListNode deleteDuplicates(ListNode head) {
        if( head == null ) return head;
        if( head.next == null ) return head;
        int previous = -1;
        ListNode start = head;
        previous = head.val;
        ListNode temp = head;
        head = head.next;
        while( head != null ){
            if( previous == head.val ){
                temp.next = head.next;
                head  = null;
                head = temp.next;
                continue;
            }
            previous = head.val;
            temp = head;
            head = head.next;
        }
        return start;
        
    }

}
