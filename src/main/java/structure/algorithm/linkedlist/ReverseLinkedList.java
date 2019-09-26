package structure.algorithm.linkedlist;

import java.util.List;

//反转列表
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head){
        ListNode prev =null;
        ListNode curr = head;
        while (curr!=null){
            ListNode temp =curr.next;//保存下一个节点
            curr.next=prev;//将后一个节点指向前一个节点
            prev=curr;//将前一个节点的指针后移
            curr=temp;//将当前结点的指针后移
        }
        return prev;
    }
    public ListNode reverseListV2(ListNode head){
        if (head==null||head.next==null) {
            return head;
        }
        ListNode p = reverseListV2(head.next);
        head.next.next=head;//迭代公式
        head.next=null;//反转之后 头结点指向null
        return p;
    }

}
