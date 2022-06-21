package algorithm.linklist;

import algorithm.utils.ListNode;
import algorithm.utils.Print;

import java.util.*;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/14
 * @description 链表
 */
public class LinkedListDemo {

    /**
     * 21. 合并两个有序链表
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

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
     * 面试题 02.01. 移除重复节点
     * https://leetcode-cn.com/problems/remove-duplicate-node-lcci/
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode temp = head;
        while (temp.next != null){
            if(set.add(temp.next.val)){
                temp = temp.next;
            }else {
                temp.next = temp.next.next;
            }
        }
        temp.next = null;
        return head;
    }

    /**
     * 移除链表中重复的节点，包含节点本身全部移除
     * @param head
     * @return
     */
    public ListNode removeDuplicateListNode(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy.next, fast = head.next ,prev = dummy;
        while (fast.next != null){
            if(fast.val != slow.val){
                fast = fast.next;
                slow = slow.next;
            }else {
                fast = fast.next;
                while (fast.val == slow.val){
                    fast = fast.next;
                }
                while (prev.next.val != slow.val){
                    prev = prev.next;
                }

                prev.next = fast;
                slow = prev;
            }
        }
        return dummy.next;
    }

    /**
     * 234. 回文链表
     * https://leetcode-cn.com/problems/palindrome-linked-list/
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head){
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }

        while (head != null){
            if(stack.pop().val != head.val){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 反转链表  解法1
     * https://leetcode-cn.com/problems/reverse-linked-list/
     *
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
     * 反转链表 解法2
     * https://leetcode-cn.com/problems/reverse-linked-list/
     *
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
     * 92. 反转链表 II
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


    /**
     * 143. 重排链表
     * https://leetcode-cn.com/problems/reorder-list/
     * @param head
     */
    public void reorderList(ListNode head){
        if(head.next == null){
            return;
        }

        ListNode temp = head;
        List<ListNode> list = new ArrayList<>();
        while (temp != null){
            list.add(temp);
            temp = temp.next;
        }

        int i = 0,j = list.size() - 1;
        while (i < j){
            list.get(i).next = list.get(j);
            i ++;
            //需要判断是否已经循环到头了
            if(i == j){
                break;
            }
            list.get(j).next = list.get(i);
            j --;
        }
        //使用list.get(i).next = null;也可以
        list.get(j).next = null;
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