package algorithm.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    /**
     * 二叉树最大深度
     * @param node
     * @return
     */
    public static int maxDepth(TreeNode node) {
        if (node == null) return 0;
        return Math.max(maxDepth(node.right), maxDepth(node.left)) + 1;
    }

    public static List<Integer> bfs(TreeNode node){
        List<Integer> list = new ArrayList<>();
        if (node == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            list.add(temp.val);
            if (temp.right != null){
                queue.offer(temp.right);
            }
            if (temp.left != null){
                queue.offer(temp.left);
            }
        }
        return list;
    }
}
