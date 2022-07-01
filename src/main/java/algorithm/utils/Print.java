package algorithm.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/22
 * @description TODO
 */
public class Print {

    public static void printNode(final ListNode node) {
        ListNode tmp = node;
        while (tmp != null) {
            System.out.print(tmp.val);
            System.out.print(",");
            tmp = tmp.next;
        }
    }

    public static void printArray(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("array is empty!");
            return;
        }
        for (int a : array) {
            System.out.print(a + ",");
        }
    }

    /**
     * 前序遍历打印节点值
     *
     * @param root
     */
    public static void printTreeNodePreorder(TreeNode root) {
        if (root == null) {
            System.out.println("root is null");
            return;
        }
        List<Integer> list = new ArrayList<>();
        TreeNode.preorder(root, list);
        System.out.println(list);
    }

    /**
     * 中序遍历打印节点值
     *
     * @param root
     */
    public static void printTreeNodeInorder(TreeNode root) {
        if (root == null) {
            System.out.println("root is null");
            return;
        }
        List<Integer> list = new ArrayList<>();
        TreeNode.inorder(root, list);
        System.out.println(list);
    }

    /**
     * 后序遍历打印节点
     *
     * @param root
     */
    public static void printTreeNodePostorder(TreeNode root) {
        if (root == null) {
            System.out.println("root is null");
            return;
        }
        List<Integer> list = new ArrayList<>();
        TreeNode.postorder(root, list);
        System.out.println(list);
    }

    /**
     * 广度遍历节点
     *
     * @param root
     */
    public static void printTreeNodeBFS(TreeNode root) {
        if (root == null) {
            System.out.println("root is null");
            return;
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.println(list);
    }

}
