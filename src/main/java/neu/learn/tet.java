package neu.learn;

import java.util.HashMap;

public class tet {

	public static void main(String[] args) {
		char[][] arr1 = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		int[] arr2 = { 2, 2, 7, 11, 15 };
		// System.out.println(firstUniqChar("leetcode"));
		// System.out.println(isPalindrome("0P"));
		ListNode n1 = new ListNode(1); 
		ListNode n2 = new ListNode(1);
		ListNode n3 = new ListNode(2);
		ListNode n4 = new ListNode(1);
		ListNode n5 = new ListNode(1);
		n1.next=n2;
		n2.next=n3;
		n3.next=n4;
		n4.next=n5;

		//System.out.println(isPalindrome1(n1));
		//System.out.println(3%8);
		System.out.println((int)'0');
	}

	 public static boolean isPalindrome1(ListNode head) {
	        if(head==null||head.next==null){
	            return true;
	        }
	        if(head.next.next==null){
	            return head.val == head.next.val;
	        }
	        ListNode slow = head;
	        ListNode fast = head.next;
	        
	        while(fast.next!=null){
	            if(slow.val == fast.next.val){
	                if(fast.next.next!=null){
	                    return false;
	                }
	                fast.next = null;
	                slow = slow.next;
	                fast = slow.next;
	                if(fast==null || slow.val == fast.val){
	                    return true;
	                }
	            }else{
	                fast = fast.next;
	            }
	        }
	        return false;
	}
	
	public static boolean isPalindrome(ListNode head) {
		if(head==null||head.next==null) {
            return true;
        }
		ListNode middle = findMiddle(head);
		//middle.next = 
		ListNode p = head, q = reverseList(middle.next);
		while(p!=null && q!=null && p.val==q.val){
			p = p.next;
			q = q.next;
		}
		return q==null;
	}

	public static ListNode findMiddle(ListNode head) {
		if(head.next.next==null) {
            return head;
        }
		ListNode p = head, q = head;
		while (q!= null && q.next != null) {
			q = q.next.next;
			if(q==null) {
                return p;
            }
			p = p.next;
		}
		return p;
	}

	public static ListNode reverseList(ListNode head) {
		if (head == null) {
            return null;
        }

		ListNode pre = null, next = null, cur = head;

		while (cur != null) {
			// 1.保存下一个节点，防止链表断了
			next = cur.next;
			// 2.将当前节点指向前一个节点从而实现局部反转，想象一下，如果没有1步骤，是不是链表断了
			cur.next = pre;
			// 3.将前一个节点向右移动
			pre = cur;
			// 4.将当前节点向右移动
			cur = next;
		}
		return pre;

	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}