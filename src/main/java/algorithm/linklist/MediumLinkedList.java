package algorithm.linklist;

import algorithm.utils.ListNode;
import algorithm.utils.Print;
import algorithm.utils.TreeNode;

import java.util.*;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/6/28
 * @description 中等难度
 *
 * 技巧1. 修改(删除，新增)链表节点时，需要构建一个dummy节点作为辅助，最后返回dummy.next即可。
 */
public class MediumLinkedList {

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    //// ==================== 中等难度 Medium difficulty ==================
    /**
     * 2. 两数相加
     * https://leetcode-cn.com/problems/add-two-numbers/
     * https://leetcode.cn/problems/sum-lists-lcci/
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2 + carry;
            //构建节点
            curr.next = new ListNode(sum % 10);
            carry = sum / 10;
            //指向下一节点
            curr = curr.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            curr.next = new ListNode(1);
        }

        return dummy.next;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n == 0){
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode temp = dummy.next;
        //计算链表长度
        int len = 0;
        while (temp != null){
            len ++;
            temp = temp.next;
        }
        temp = dummy;
        //循环到倒数第n个节点的前驱节点位置
        for (int i = 0; i < len - n; i ++){
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return dummy.next;
    }

    /**
     * 24. 两两交换链表中的节点  TODO
     * https://leetcode.cn/problems/swap-nodes-in-pairs/
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        // 保存新的头节点
        ListNode newHead = head.next;
        //递归置换节点
        head.next = swapPairs(newHead.next);
        //新节点后继节点指向原始头节点
        newHead.next = head;
        return newHead;
    }

    /**
     * 61. 旋转链表  与倒数第k个节点解法相同
     * https://leetcode.cn/problems/rotate-list/
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0){
            return head;
        }
        //链表长度
        int len = 0;
        ListNode temp = head;
        while (temp != null){
            temp = temp.next;
            len ++;
        }
        //取模 f(x) = k + len * n 与 k的效果一致
        k = k % len;
        if (k == 0){
            return head;
        }
        // 快慢指针
        ListNode slow = head, fast = head;
        for (int i = 0; i < k; i ++){
            fast = fast.next;
        }

        //此处fast为链表的最后一个节点
        //此时slow是倒数第k个节点的前驱节点
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        //让链表成环
        fast.next = head;
        //最新的头节点是slow节点的后继节点
        ListNode newHead = slow.next;
        slow.next = null;

        return newHead;
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 前驱节点、当前节点
        ListNode pre = dummy, curr = head;
        while (curr != null && curr.next != null){
            //当前节点的值等于后继结点的值
            if (curr.val == curr.next.val){
                //记录当前节点的值
                int val = curr.val;
                // 如果发现后置节点和当前节点的值相同，则一直向后找，直到值不同为止
                while (curr.next != null && curr.next.val == val){
                    curr = curr.next;
                }
                //移动当前节点位置，此时curr.next.val != val
                curr = curr.next;
                //将前置节点的后继指针指向当前节点
                pre.next = curr;
            }else {
                // 正常向后移动指针
                curr = curr.next;
                pre = pre.next;
            }
        }
        return dummy.next;
    }

    /**
     * 86. 分隔链表
     * https://leetcode.cn/problems/partition-list/
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode low = new ListNode(x), lowHead = low;
        ListNode high = new ListNode(x), highHead = high;
        while (head != null){
            if (x > head.val){
                low.next = head;
                low = low.next;
            }else {
                high.next = head;
                high = high.next;
            }
            head = head.next;
        }
        high.next = null;
        low.next = highHead.next;
        return lowHead.next;
    }

    /**
     * 92. 反转链表 II
     * https://leetcode-cn.com/problems/reverse-linked-list-ii/
     * 翻转从left位置到right位置链表
     *
     * @param head
     * @param left
     * @param right
     *
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);

        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode leftNode = pre.next;
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        ListNode curr = rightNode.next;

        //切断指针
        pre.next = null;
        rightNode.next = null;
        //翻转链表 leftNode为需要翻转的子链表
        reverse(leftNode);

        //翻转以后rightNode就变成了子链表的头部
        pre.next = rightNode;
        leftNode.next = curr;

        return dummy.next;
    }


    /**
     * 109. 有序链表转换二叉搜索树
     * https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null){
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        while (head != null){
            list.add(head);
            head = head.next;
        }
        return sortedListToBSTHelper(list, 0, list.size() - 1);
    }
    private TreeNode sortedListToBSTHelper(List<ListNode> list, int low, int high){
        if (low > high){
            return null;
        }
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(list.get(mid).val);
        root.left = sortedListToBSTHelper(list, low, mid - 1);
        root.right = sortedListToBSTHelper(list, mid + 1, high);
        return root;
    }

    /**
     * 142. 环形链表 II
     * https://leetcode.cn/problems/linked-list-cycle-ii/
     * https://leetcode.cn/problems/c32eOV/
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null){
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode temp = head;
        while (temp != null){
            if (!set.add(temp)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 143. 重排链表
     * https://leetcode-cn.com/problems/reorder-list/
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head.next == null) {
            return;
        }

        ListNode temp = head;
        List<ListNode> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }

        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            //需要判断是否已经循环到头了
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        //使用list.get(i).next = null;也可以
        list.get(j).next = null;
    }

    /**
     * 147. 对链表进行插入排序
     * https://leetcode.cn/problems/insertion-sort-list/
     * @param head
     * @return
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        // 两个节点永远齐头并进
        ListNode curr = head.next, pre = head;
        // 创建虚拟节点
        ListNode dummy = new ListNode(Integer.MIN_VALUE, head);

        while (curr != null){
            if (curr.val >= pre.val){
                pre = pre.next;
            }else {
                // 从链表头开始判断
                ListNode dummyPre = dummy;
                while (dummyPre.next.val <= curr.val){
                    dummyPre = dummyPre.next;
                }

                //前驱节点指针指向后继节点的next元素(交换位置)
                pre.next = curr.next;
                curr.next = dummyPre.next;
                dummyPre.next = curr;

            }
            curr = pre.next;
        }
        return dummy.next;
    }

    /**
     * 148. 排序链表
     * https://leetcode.cn/problems/sort-list/
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        return sortListSortHelper(head, null);
    }

    // 分离链表  归并排序
    private static ListNode sortListSortHelper(ListNode start, ListNode end){
        if (start == end){
            return start;
        }
        //使用快慢指针二分链表
        ListNode slow = start, fast = start;
        while (fast != end && fast.next != end){
            //循环到最后时slow是中间节点的前驱节点
            slow = slow.next;
            fast = fast.next.next;
        }
        //高位节点
        ListNode high = sortListSortHelper(slow.next, end);
        // 断开指针,避免变成环形链表
        slow.next = null;
        //低位节点
        ListNode low = sortListSortHelper(start, slow);
        //合并链表
        return new EasyLinkedList().mergeTwoLists(low, high);
    }

    /**
     * 328. 奇偶链表
     * 将所有 "索引" 为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     * https://leetcode.cn/problems/odd-even-linked-list/
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null){
            return null;
        }
        // 记录偶数索引头结点位置
        ListNode evenHead = head.next;
        // 记录奇数和偶数索引节点位置
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    /**
     * 1019. 链表中的下一个更大节点
     * https://leetcode.cn/problems/next-greater-node-in-linked-list/
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode reversed = reverse(head);
        Deque<Integer> stack = new LinkedList<>();
        ListNode temp = reversed;
        int size = 0;
        while (temp != null){
            temp = temp.next;
            size ++;
        }
        int[] res = new int[size];
        int index = size - 1;
        while (index >= 0){
            while(!stack.isEmpty() && reversed.val >= stack.peek()){
                stack.poll();
            }
            res[index--] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(reversed.val);
            reversed = reversed.next;
        }
        return res;
    }

    /**
     * 1171. 从链表中删去总和值为零的连续节点
     * https://leetcode.cn/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
     * @param head
     * @return
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        Map<Integer, ListNode> map = new HashMap<>();
        // 前缀和
        // 首次建立hash表，同一和出现多次会被覆盖，记录sum最后一次出现的节点
        int sum = 0;
        for (ListNode d = dummy; d != null ; d = d.next){
            sum += d.val;
            map.put(sum, d);
        }
        sum = 0;
        // 当map中含有sum包含的节点，说明出现了节点值和为0的节点，直接删除即可
        for (ListNode d = dummy; d != null; d = d.next){
            sum += d.val;
            d.next = map.get(sum).next;
        }
        return dummy.next;
    }

    /**
     * 1367. 二叉树中的列表
     * https://leetcode.cn/problems/linked-list-in-binary-tree/
     * 与 https://leetcode.cn/problems/subtree-of-another-tree/ 类似
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(head == null){
            return true;
        }
        if (root == null){
            return false;
        }
        return isSubPathHelper(head, root)
                || isSubPath(head, root.left) || isSubPath(head, root.right);
    }
    /**
     * 判断当前root节点与当前head节点的内容是否相同。
     * @param head
     * @param root
     * @return
     */
    private boolean isSubPathHelper(ListNode head, TreeNode root){
        //则说明head已经遍历完毕，已经在root中寻找到了与head相同连续向下的路径
        if(head == null){
            return true;
        }
        //head不为空但root以为空，在此路径上不可能寻找到相同路径了，返回false
        if (root == null){
            return false;
        }
        if (head.val != root.val){
            return false;
        }
        return isSubPathHelper(head.next, root.left) || isSubPathHelper(head.next, root.right);
    }

    /**
     * 1669. 合并两个链表
     * https://leetcode.cn/problems/merge-in-between-linked-lists/
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        if(list1 == null){
            return list2;
        }

        ListNode dummy = new ListNode(-1, list1);
        ListNode pre = dummy, curr = dummy.next;
        //获得a位置之前的节点
        for (int i = 0; i < a; i++){
            pre = pre.next;
        }
        //获得b位置之后的节点
        for (int i = 0; i < b + 1; i ++){
            curr = curr.next;
        }
        //修改指针指向list2头结点
        pre.next = list2;

        //取得list2尾节点
        ListNode tail2 = list2;
        while (tail2.next != null){
            tail2 = tail2.next;
        }
        //尾结点指向b之后的节点
        tail2.next = curr;
        return dummy.next;
    }

    /**
     * 1721. 交换链表中的节点
     * https://leetcode.cn/problems/swapping-nodes-in-a-linked-list/
     * @param head
     * @param k
     * @return
     */
    public static ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode curr = dummy.next, last = dummy.next;

        for (int i = 0; i < k - 1; i++){
            curr = curr.next;
        }
        int len = 0;
        ListNode temp = dummy.next;
        while (temp != null){
            temp = temp.next;
            len ++;
        }
        for (int i = 0; i < len - k; i++){
            last = last.next;
        }
        // 值交换
        int currVal = curr.val;
        curr.val = last.val;
        last.val = currVal;
        return dummy.next;
    }

    /**
     * 2095. 删除链表的中间节点
     * https://leetcode.cn/problems/delete-the-middle-node-of-a-linked-list/
     * @param head
     * @return
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy.next, fast = dummy.next, prev = dummy;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = slow.next;
        return dummy.next;
    }

    /**
     * 2181. 合并零之间的节点
     * https://leetcode.cn/problems/merge-nodes-in-between-zeros/
     * @param head
     * @return
     */
    public static ListNode mergeNodes(ListNode head) {
        if (head == null){
            return null;
        }
        // 跳过头结点从第二个节点开始遍历
        ListNode dummy = head.next;
        ListNode newHead = new ListNode(0);
        ListNode ans = newHead;
        int sum = 0;
        while (dummy != null){
            if (dummy.val == 0){
                newHead.next = new ListNode(sum);
                // 重新设置新链表头
                newHead = newHead.next;
                sum = 0;
            }else {
                sum += dummy.val;
            }
            dummy = dummy.next;
        }
        return ans;
    }

    /**
     * 面试题 04.03. 特定深度节点链表
     * https://leetcode.cn/problems/list-of-depth-lcci/
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        List<ListNode> list = new ArrayList<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            ListNode listNode = new ListNode(0);
            ListNode ans = listNode;
            for (int i = 0; i < size; i++){
                TreeNode treeNode = queue.poll();
                listNode.next = new ListNode(treeNode.val);
                listNode = listNode.next;
                if (treeNode.left != null){
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null){
                    queue.offer(treeNode.right);
                }
            }
            list.add(ans.next);
        }
        return list.toArray(new ListNode[0]);
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next = new ListNode(0);
//        head.next.next.next.next.next = new ListNode(1);
//        head.next.next.next.next.next.next = new ListNode(5);
        ListNode newNode = mergeNodes(head);
        Print.printListNode(newNode);
    }

}
