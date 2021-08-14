package algorithm;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/14
 * @description 链表
 */
public class LinkedListDemo {

    /**
     * LeetCode  https://leetcode-cn.com/problems/add-two-numbers/
     * 提交未通过
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1,ListNode l2){
        int sum1 = 0,sum2 = 0;
        int carry1 = 0,carry2 = 0;
        ListNode tmp1 = l1, tmp2 = l2;
        while(tmp1 != null){
            sum1 += tmp1.val * Math.pow(10,carry1);
            tmp1 = tmp1.next;
            carry1++;
        }
        while(tmp2 != null){
            sum2 += tmp2.val * Math.pow(10,carry2);
            tmp2 = tmp2.next;
            carry2++;
        }
        sum1 += sum2;
        //重组链表
        ListNode pre = new ListNode(0);
        ListNode dummy = pre;
        while(sum1 != 0){
            dummy.next = new ListNode(sum1 % 10);
            sum1 /= 10;
            dummy = dummy.next;
        }
        return pre.next;
    }

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
        while (node != null){
            System.out.print(node.val);
            node = node.next;
        }
//        System.out.println(node.val + "" + node.next.val + "" + node.next.next.val);
    }

}



class ListNode{

    ListNode next;

    int val;

    ListNode() {}

    ListNode(int val){
        this.val = val;
    }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}