package structure.algorithm.linkedlist;

/**
 * @Title ReverseKGroup
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/6 13:40
 **/
//https://leetcode-cn.com/problems/reverse-nodes-in-k-group/ k个一组 反转链表
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dump = new ListNode(0);
        dump.next=head;
        ListNode pre = dump;
        ListNode end =dump;
        while (end.next!=null){
            for (int i = 0; i <k&&end!=null ; i++) {
                end =end.next;
            }
            if (end==null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next=null;//先砍断
            pre.next = reverse(start);
            start.next=next;
            pre=start;
            end=pre;
        }
        return dump.next;

    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nexttemp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nexttemp;
        }
        return pre;
    }

}
