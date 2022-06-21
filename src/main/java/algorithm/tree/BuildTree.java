package algorithm.tree;

import algorithm.utils.Print;
import algorithm.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/31
 * @description TODO
 */
public class BuildTree {


    /**
     * 面试题 04.02. 最小高度树
     * https://leetcode-cn.com/problems/minimum-height-tree-lcci/
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return helper(nums,0,nums.length - 1);
    }
    public TreeNode helper(int[] nums,int low,int high){
        if(low == high){
            return null;
        }
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums,low,mid - 1);
        root.right = helper(nums,mid + 1,high);
        return root;
    }

    /**
     * 897. 递增顺序搜索树
     * https://leetcode-cn.com/problems/increasing-order-search-tree/
     * 与下题相同
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        Print.inorder(root,list);
        TreeNode dummy = new TreeNode(-1);
        TreeNode curr = dummy;
        for (Integer num : list){
            curr.right = new TreeNode(num);
            curr = curr.right;
        }
        return dummy.right;
    }

    /**
     * 面试题 17.12. BiNode
     * https://leetcode-cn.com/problems/binode-lcci/
     * @param root
     * @return
     */
    TreeNode head = null,pre = null;
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null){
            return null;
        }

        convertBiNode(root.left);
        if (head == null){
            head = root;
        }else {
            pre.right = root;
        }
        pre = root;
        root.left = null;

        convertBiNode(root.right);
        return head;
    }


}
