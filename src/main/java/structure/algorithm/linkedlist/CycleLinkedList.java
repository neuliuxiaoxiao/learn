package structure.algorithm.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @Title CycleLinkedList
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/6 10:51
 **/
//判断是否有环
public class CycleLinkedList {

    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        ListNode cur = head;
        while (cur!=null){
            if(set.contains(cur)){//包含
               return true;
            }else{
                set.add(cur);
            }
            cur=cur.next;
        }
        return  false;
    }

    public boolean hasCycleV2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast =head.next;
        ListNode slow =head;
        while (slow!=fast){
            if (fast==null||fast.next==null){
                return false;
            }
            fast=fast.next.next;
            slow=slow.next;
        }
        return true;
    }
}
