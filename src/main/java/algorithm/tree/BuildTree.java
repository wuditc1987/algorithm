package algorithm.tree;

import algorithm.utils.Print;
import algorithm.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/31
 * @description 构建二叉树
 */
public class BuildTree {


    /**
     * 面试题 04.02. 最小高度树
     * https://leetcode-cn.com/problems/minimum-height-tree-lcci/
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return sortedArrayToBSTHelper(nums,0,nums.length - 1);
    }
    public static TreeNode sortedArrayToBSTHelper(int[] nums, int low, int high){
        if(low > high){
            return null;
        }
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTHelper(nums,low,mid - 1);
        root.right = sortedArrayToBSTHelper(nums,mid + 1,high);
        return root;
    }

    /**
     * 897. 递增顺序搜索树
     * https://leetcode-cn.com/problems/increasing-order-search-tree/
     * https://leetcode.cn/problems/NYBBNL/
     * 与下题相同
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        TreeNode.inorder(root, list);
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

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTreePre(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i ++){
            map.put(inorder[i], i);
        }
        return buildTreePreHelper(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    private static TreeNode buildTreePreHelper(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd, Map<Integer, Integer> map){
        if (pStart == pEnd){
            return null;
        }
        // 根节点值
        int rootVal = preorder[pStart];
        // 创建根节点
        TreeNode root = new TreeNode(rootVal);
        // 根节点在中序遍历中的位置
        int rootIndex = map.get(rootVal);
        // 中序遍历中的根节点位置与左子树的位置偏移量,
        int offset = rootIndex - iStart;

        // 寻找node的左节点:
        // 在前序遍历中的位置就是： 根节点的下标 + 1（右边一个单位）
        // 在中序遍历中的位置就是： 1. 左边界不变，2. 右边界就是根节点的左边一个单位 rootIndex - 1
        root.left = buildTreePreHelper(preorder, pStart + 1, pStart + 1 + offset, inorder, iStart, rootIndex - 1, map);

        // 寻找node的右节点
        // 在前序遍历中的位置就是：根节点下标 + 左子树长度 + 1
        // 在中序遍历中的位置就是：根节点位置 + 1
        root.right = buildTreePreHelper(preorder, pStart + offset + 1, pEnd, inorder, rootIndex + 1, iEnd, map);
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
     * @param inorder
     * @param postorder
     * @return
     */
    public static TreeNode buildTreePost(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return buildTreePostHelper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }
    private static TreeNode buildTreePostHelper(int[] inorder, int iStart, int iEnd, int[] postorder, int pStart, int pEnd, Map<Integer, Integer> map){
        if (pStart > pEnd){
            return null;
        }
        int rootVal = postorder[pEnd];
        int rootIndex = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);

        int offset = iEnd - rootIndex;
        root.left = buildTreePostHelper(inorder, iStart, rootIndex - 1, postorder, pStart, pEnd - offset - 1, map);
        root.right = buildTreePostHelper(inorder, rootIndex + 1, iEnd, postorder, pEnd - offset, pEnd - 1, map);
        return root;
    }

    public static void main(String[] args) {
        int[] inorder = new int[] {9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        TreeNode root = buildTreePost(inorder, postorder);


    }
}
