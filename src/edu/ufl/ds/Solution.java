package edu.ufl.ds;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
	
	class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) {
		    val = x;
		    next = null;
		 }
	}
	
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if( l2 == null && l1 == null ) return null;
        if( l2 == null ) return l1;
        if( l1 == null ) return l2;
        int carry=0;
        int sum = 0;
        ListNode sumNode = null;
        ListNode startNode = null;
        while( l1 != null && l2 != null ){
            ListNode temp = null;
            if( l1 == null ){
                sum = carry + l2.val;
                carry = 0;
                if( sum >= 10 ){
                  sum = sum % 10;
                  carry = 1;
                } 
                temp = new ListNode( sum );
                sumNode.next = temp;
                sumNode = sumNode.next;
                l2 = l2.next;
            }
            if( l2 == null ){
                sum = carry + l1.val;
                carry = 0;
                if( sum >= 10 ){
                    sum  = sum % 10;
                    carry = 1;
                }
                temp = new ListNode( sum );
                sumNode.next = temp;
                sumNode = sumNode.next;
                l1 = l1.next;
            }
            if( l1 != null && l2 != null ){
                sum = carry + l1.val + l2.val;
                carry = 0;
                if( sum >= 10 ){
                    sum = sum % 10;
                    carry = 1;
                }
                temp = new ListNode( sum );
                if( startNode == null ){
                    sumNode = temp;
                    startNode = temp;
                }
                else{
                    sumNode.next = temp;
                    sumNode  = sumNode.next;
                }
                l1 = l1.next;
                l2 = l2.next;
           }
        }
        if( carry == 1){
            ListNode temp = new ListNode(1);
            sumNode.next = temp;
            sumNode = temp;
        }
        return startNode;
    }
    
    public static void main( String args[]){
    	Solution ob = new  Solution();
    }
    
}
	