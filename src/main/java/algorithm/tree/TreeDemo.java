package algorithm.tree;

import algorithm.utils.Print;
import algorithm.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/24
 * @description TODO
 */
public class TreeDemo {

    /**
     * 112. 路径总和 解法2
     * https://leetcode-cn.com/problems/path-sum/
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        //判断叶子节点的值
        if(root.left == null && root.right == null){
            return targetSum == root.val;
        }
        //分解为子问题
        return hasPathSum2(root.left,targetSum - root.val)
                || hasPathSum2(root.right,targetSum - root.val);
    }

    /**
     * 226. 翻转二叉树
     * https://leetcode-cn.com/problems/invert-binary-tree/
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
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
     * 114. 二叉树展开为链表
     * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) return;

        List<TreeNode> list = new ArrayList<>();
        preorder(root,list);
        for (int i = 0, len = list.size(); i < len - 1; i ++){
            TreeNode node = list.get(i);
            node.right = list.get(i + 1);
            node.left = null;
        }
        Print.printTreeNodePreorder(root);
    }

    private void preorder(TreeNode node,List<TreeNode> list){
        if(node != null){
            list.add(node);
            preorder(node.left,list);
            preorder(node.right,list);
        }
    }

    /**
     * 236. 二叉树的最近公共祖先
     * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root){
            return root;
        }

        TreeNode right = lowestCommonAncestor(root.right,p,q);
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        if(left == null){
            return right;
        }else if(right == null){
            return left;
        }else {
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        TreeDemo demo = new TreeDemo();
        demo.flatten(root);
    }

}
