package structure.algorithm.linkedlist;

import jdk.nashorn.internal.ir.LiteralNode;

public class SwapLinkedList {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;//保留head节点的下一个节点 反转head 和next
        head.next = swapPairs(next.next);//反转后 head节点的next指向 递归的结果  next.next 指向前一个节点即head
        next.next = head;
        return next;//返回头节点
    }

    public static  ListNode swapPairsV2(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next=head;
       ListNode temp = pre;//给新节点  pre用来表示头结点
        while (temp.next!=null&&temp.next.next!=null){
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next=end;
            start.next=end.next;
            end.next=start;
            temp = start;
        }
        return pre.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode h1 = new ListNode(2);
        ListNode h2 = new ListNode(3);
        ListNode h3 = new ListNode(4);
        head.next=h1;
        h1.next=h2;
        h2.next=h3;
        System.out.println(swapPairsV2(head).toString());

    }


}
