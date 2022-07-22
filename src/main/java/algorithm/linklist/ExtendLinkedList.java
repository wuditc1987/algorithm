package algorithm.linklist;

import algorithm.utils.ListNode;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/7/22
 * @description 由leetcode扩展出的算法题
 */
public class ExtendLinkedList {

    /**
     * 移除链表中重复的节点，包含节点本身全部移除  TODO
     * @param head
     * @return
     */
    public static ListNode removeDupNodesIncludeItself(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        //定义外层和内层循环的链表
        ListNode dummy = new ListNode(-1, head);
        ListNode outer = dummy.next, outerPre = dummy;
        while (outer != null){
            boolean delete = false;
            int val = outer.val;
            ListNode inner = dummy.next, innerPre = dummy;
            while (inner != null){
                //如果两值相同，则删除当前节点
                if (val == inner.val){
                    delete = true;
                    innerPre.next = inner.next;
                }else {
                    innerPre = inner;
                }
                inner = inner.next;
            }
            //需要删除当前节点
            if (delete){
                outerPre.next = outer.next;
            }else {
                outerPre = outer;
            }
            outer = outer.next;
        }
        return dummy.next;
    }

}
