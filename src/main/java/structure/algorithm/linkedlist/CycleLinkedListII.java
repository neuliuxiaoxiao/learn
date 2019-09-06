package structure.algorithm.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @Title CycleLinkedListII
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/6 11:10
 **/
public class CycleLinkedListII {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visit = new HashSet<>();
        while (head != null) {
            if (visit.contains(head)) {
                return head;
            } else {
                visit.add(head);
            }
            head = head.next;
        }
        return null;
    }
    public ListNode detectCycleV2(ListNode head) {
        if (head==null||head.next==null){
            return null;
        }
        ListNode ptr2 = getInstance(head);
        if (ptr2==null){
            return null;
        }
        ListNode ptr1 =head;
        while (ptr2!=ptr1){
            ptr2=ptr2.next;
            ptr1=ptr1.next;
        }
        return ptr1;

    }
    public ListNode getInstance(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (fast==slow) return slow;
        }
        return null;
    }

}
