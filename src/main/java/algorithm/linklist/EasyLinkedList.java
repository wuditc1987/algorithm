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
public class EasyLinkedList {


    //

    /**
     * 141. 环形链表
     * https://leetcode.cn/problems/linked-list-cycle/
     *
     * @param head
     *
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 160. 相交链表
     * https://leetcode.cn/problems/intersection-of-two-linked-lists/
     *
     * @param headA
     * @param headB
     *
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA, pb = headB;
        while (pa != pb) {
            pa = pa != null ? pa.next : headB;
            pb = pb != null ? pb.next : headA;
        }
        return pa;
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
     *
     * @param head
     * @param k
     *
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode former = head, latter = head;
        for (int i = 0; i < k; i++) {
            former = former.next;
        }

        while (former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }

    /**
     * 1290. 二进制链表转整数 解法1
     * https://leetcode.cn/problems/convert-binary-number-in-a-linked-list-to-integer/
     *
     * @param head
     *
     * @return
     */
    public int getDecimalValue(ListNode head) {
        if (head == null) return 0;
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int power = 0;
        int total = 0;
        while (!stack.isEmpty()) {
            int val = stack.pop().val;
            total += (int) Math.pow(2, power) * val;
            power++;
        }
        return total;
    }

    /**
     * 1290. 二进制链表转整数 解法2
     * 解法3   先翻转链表再计算
     *
     * @param head
     *
     * @return
     */
    public static int getDecimalValue2(ListNode head) {
        int sum = 0;
        while (head != null) {
            sum = (sum << 1) + head.val;
            head = head.next;
        }
        return sum;
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/submissions/
     *
     * @param head
     *
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        List<ListNode> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        int[] arr = new int[list.size()];
        int j = list.size() - 1;
        for (int i = 0; i <= list.size() - 1; i++, j--) {
            arr[i] = list.get(j).val;
        }
        return arr;
    }

    /**
     * 21. 合并两个有序链表
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     *
     * @param l1
     * @param l2
     *
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 83. 删除排序链表中的重复元素
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
     *
     * @param head
     *
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = head;
        while (dummy.next != null) {
            if (dummy.val == dummy.next.val) {
                dummy.next = dummy.next.next;
            } else {
                dummy = dummy.next;
            }
        }
        return head;
    }

    /**
     * 203. 移除链表元素
     * https://leetcode.cn/problems/remove-linked-list-elements/
     *
     * @param head
     * @param val
     *
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return dummy.next;
    }

    /**
     * 面试题 02.01. 移除重复节点
     * https://leetcode-cn.com/problems/remove-duplicate-node-lcci/
     *
     * @param head
     *
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Set<Integer> set = new HashSet<>();
        set.add(head.val);
        ListNode temp = head;
        while (temp.next != null) {
            if (set.add(temp.next.val)) {
                temp = temp.next;
            } else {
                temp.next = temp.next.next;
            }
        }
        return head;
    }

    /**
     * 移除链表中重复的节点，包含节点本身全部移除  TODO 有问题 用例[1,1,2,3,3,4,5,5]
     *
     * @param head
     *
     * @return
     */
    public static ListNode removeDuplicateListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy.next, fast = head.next, prev = dummy;
        while (fast.next != null) {
            if (fast.val != slow.val) {
                fast = fast.next;
                slow = slow.next;
            } else {
                fast = fast.next;
                while (fast.val == slow.val) {
                    fast = fast.next;
                }
                while (prev.next.val != slow.val) {
                    prev = prev.next;
                }

                prev.next = fast;
                slow = prev;
            }
        }
        return dummy.next;
    }

    /**
     * 移除链表中重复的节点，包含节点本身全部移除
     * 解法2 TODO 有问题 用例[1,1,2,3,3,4,5,5]
     *
     * @param head
     *
     * @return
     */
    public static ListNode removeDuplicateListNode2(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        Set<Integer> set = new HashSet<>();
        while (dummy.next != null) {
            if (set.add(dummy.next.val)) {
                dummy = dummy.next;
            } else {
                dummy.next = dummy.next.next;
            }
        }
        ListNode dummy2 = head;
        if (dummy2.next == null) {
            return null;
        }
        while (dummy2.next != null) {
            if (set.add(dummy2.next.val)) {
                dummy2 = dummy2.next;
            } else {
                dummy2.next = dummy2.next.next;
            }
        }

        return head;

    }

    /**
     * 234. 回文链表
     * https://leetcode-cn.com/problems/palindrome-linked-list/
     *
     * @param head
     *
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        while (head != null) {
            if (stack.pop().val != head.val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 876. 链表的中间结点
     * 快慢指针解法
     * https://leetcode.cn/problems/middle-of-the-linked-list/
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 206. 反转链表  解法1
     * https://leetcode-cn.com/problems/reverse-linked-list/
     *
     * @param head
     *
     * @return
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
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
     * 102500
     *
     * @param head
     *
     * @return
     */
    public ListNode reverse2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode curr = head, n;
        while (curr != null) {
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



    public static void main(String[] args) {
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(1);
        l2.next.next = new ListNode(0);
//        l2.next.next.next = new ListNode(1);
        System.out.println(getDecimalValue2(l2));
//        l2.next.next.next.next = new ListNode(3);
//        l2.next.next.next.next.next = new ListNode(4);
//        l2.next.next.next.next.next.next = new ListNode(5);
//        l2.next.next.next.next.next.next.next = new ListNode(5);
//        ListNode node = removeDuplicateListNode(l2);
//        ListNode node = demo.addTwoNumbers(l1,l2);
//        Print.printNode(node);
    }

}