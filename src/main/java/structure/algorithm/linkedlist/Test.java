package structure.algorithm.linkedlist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Title Test
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/6 9:32
 **/
public class Test {

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return pre;
    }

    public static ListNode swap(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        ListNode pre = swap(next.next);
        next.next = head;
        head.next = pre;
        return next;
    }

    public static ListNode reverse2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static ListNode swap2(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }

    public static boolean cycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }

    public static boolean cycle2(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode temp = head;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != slow) {
            fast = temp.next;
            if (fast == null || fast.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    public static ListNode cycle3(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            set.add(node);
            node = node.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode h1 = new ListNode(2);
        ListNode h2 = new ListNode(3);
        ListNode h3 = new ListNode(4);
        head.next = h1;
        h1.next = h2;
        h2.next = h3;
        h3.next = h1;
        System.out.println(cycle3(head));
        //System.out.println(reverse2(head));
        // System.out.println(swap(head));
        //   System.out.println(swap2(head));
        //  System.out.println(swap2(head).toString());

    }
}
