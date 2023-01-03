package algorithm.utils;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/21
 * @description 单链表
 */
public class ListNode {

    public ListNode next;

    public int val;

    public ListNode() {}

    public ListNode(int val){
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
