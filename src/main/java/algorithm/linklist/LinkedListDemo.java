package algorithm.linklist;

import algorithm.utils.ListNode;
import algorithm.utils.Print;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/14
 * @description 链表
 */
public class LinkedListDemo {

    /**
     * LeetCode  https://leetcode-cn.com/problems/add-two-numbers/
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null){
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        while(l1 != null || l2 != null){
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2 + carry;
            curr.next = new ListNode(sum % 10);
            carry = sum / 10;
            curr = curr.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        if(carry != 0){
            curr.next = new ListNode(carry);
        }

        return dummy.next;
    }

    /**
     * 删除重复节点
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0,head);
        ListNode curr = dummy.next,n = curr.next;

        return dummy.next;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-linked-list/
     * 反转链表  解法1
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode p = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-linked-list/
     * 反转链表 解法2
     * @param head
     * @return
     */
    public ListNode reverse2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode pre = null;
        ListNode curr = head,n;
        while (curr != null){
            //记录当前节点的后继节点
            n = curr.next;
            //当前节点后继指针指向前驱节点（此时已经断开指向n的链接）
            curr.next = pre;
            //向后循环1，
            pre = curr;
            //向后循环2
            curr = n;
        }
        return pre;
    }

    /**
     *
     * https://leetcode-cn.com/problems/reverse-linked-list-ii/
     * 翻转从left位置到right位置链表
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseFrom(ListNode head,int left,int right){
        ListNode dummy = new ListNode(-1,head);

        ListNode pre = dummy;
        for(int i = 0; i < left - 1; i ++){
            pre = pre.next;
        }

        ListNode leftNode = pre.next;
        ListNode rightNode = pre;
        for (int i = 0; i < right- left + 1; i ++){
            rightNode = rightNode.next;
        }
        ListNode curr = rightNode.next;

        //切断指针
        pre.next = null;
        rightNode.next = null;
        //翻转链表 leftNode为需要翻转的子链表
        reverse(leftNode);

        //翻转以后rightNode就变成了
        pre.next = rightNode;
        leftNode.next = curr;

        return dummy.next;
    }

    public static void main(String[] args) {
        LinkedListDemo demo = new LinkedListDemo();
        ListNode l1 = new ListNode(9);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next = new ListNode(9);

        ListNode node = demo.addTwoNumbers(l1,l2);
        Print.printNode(node);
    }

}