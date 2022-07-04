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

    /**
     * 700. 二叉搜索树中的搜索
     * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
     *
     * @param root
     * @param val
     *
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    public static void main(String[] args) {

    }
}
