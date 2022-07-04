package algorithm.tree;

import algorithm.utils.TreeNode;

import java.util.*;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/7/3
 * @description 中等难度二叉树
 */
public class MediumTreeDemo {

    /**
     * 226. 翻转二叉树
     * https://leetcode-cn.com/problems/invert-binary-tree/
     * https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
     *
     * @param root
     *
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        //交换树的左右孩子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 226. 翻转二叉树 2  BFS
     * https://leetcode-cn.com/problems/invert-binary-tree/
     *
     * @param root
     *
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }

    /**
     * 236. 二叉树的最近公共祖先
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
     * https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
     * @param root
     * @param p
     * @param q
     *
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }

        TreeNode right = lowestCommonAncestor(root.right, p, q);
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    /**
     * 面试题 04.05. 合法二叉搜索树
     * https://leetcode.cn/problems/legal-binary-search-tree-lcci/
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    public boolean isValidBSTHelper(TreeNode node, long max, long min){
        if(node == null){
            return true;
        }
        if(node.val >= max || node.val <= min){
            return false;
        }
        return isValidBSTHelper(node.left, node.val, min) && isValidBSTHelper(node.right, max, node.val);
    }

}
