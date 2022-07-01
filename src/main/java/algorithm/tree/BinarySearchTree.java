package algorithm.tree;

import algorithm.utils.TreeNode;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/6/30
 * @description 二叉搜索树
 */
public class BinarySearchTree {

    /**
     * 235. 二叉搜索树的最近公共祖先
     * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null){
            if(p.val < root.val && q.val < root.val){
                root = root.left;
            }else if(p.val > root.val && q.val > root.val){
                root = root.right;
            }else{
                return root;
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
