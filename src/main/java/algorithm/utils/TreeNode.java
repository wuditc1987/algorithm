package algorithm.utils;

import java.util.List;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/24
 * @description 二叉树
 */
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 前序遍历
     *
     * @param node
     * @param list
     */
    public static void preorder(TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.val);
            preorder(node.left, list);
            preorder(node.right, list);
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     * @param list
     */
    public static void inorder(TreeNode node, List<Integer> list) {
        if (node != null) {
            inorder(node.left, list);
            list.add(node.val);
            inorder(node.right, list);
        }
    }

    /**
     * 后序遍历
     *
     * @param node
     * @param list
     */
    public static void postorder(TreeNode node, List<Integer> list) {
        if (node != null) {
            postorder(node.left, list);
            postorder(node.right, list);
            list.add(node.val);
        }
    }
}
