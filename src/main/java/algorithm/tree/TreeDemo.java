package algorithm.tree;

import algorithm.utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/24
 * @description TODO
 */
public class TreeDemo {

    /**
     * 112. 路径总和  解法1
     * https://leetcode-cn.com/problems/path-sum/
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valueQueue = new LinkedList<>();



        return false;
    }

    /**
     * 112. 路径总和  解法2
     * https://leetcode-cn.com/problems/path-sum/
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        //
        if(root.left == null && root.right == null){
            return targetSum == root.val;
        }
        //分解为子问题
        return hasPathSum2(root.left,targetSum - root.val)
                || hasPathSum2(root.right,targetSum - root.val);
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



}
