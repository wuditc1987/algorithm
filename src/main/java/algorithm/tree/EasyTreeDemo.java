package algorithm.tree;

import algorithm.utils.Print;
import algorithm.utils.TreeNode;

import java.util.*;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/24
 * @description 简单难度二叉树
 */
public class EasyTreeDemo {

    /**
     * 110. 平衡二叉树
     * https://leetcode-cn.com/problems/balanced-binary-tree/
     * https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/
     * @param root
     *
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(TreeNode.maxDepth(root.left) - TreeNode.maxDepth(root.right)) <= 1
                && isBalanced(root.right) && isBalanced(root.left);
    }

    /**
     * 112. 路径总和 解法2
     * https://leetcode-cn.com/problems/path-sum/
     *
     * @param root
     * @param targetSum
     *
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        //判断叶子节点的值
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        //分解为子问题
        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 114. 二叉树展开为链表
     * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) return;

        List<TreeNode> list = new ArrayList<>();
        preorder(root, list);
        for (int i = 0, len = list.size(); i < len - 1; i++) {
            TreeNode node = list.get(i);
            node.right = list.get(i + 1);
            node.left = null;
        }
        Print.printTreeNodePreorder(root);
    }

    private void preorder(TreeNode node, List<TreeNode> list) {
        if (node != null) {
            list.add(node);
            preorder(node.left, list);
            preorder(node.right, list);
        }
    }

    /**
     * 404. 左叶子之和
     * https://leetcode.cn/problems/sum-of-left-leaves/
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null){
            return 0;
        }
        int val = 0;
        // 得到左叶子节点的值
        if (root.left != null && root.left.left == null && root.left.right == null){
            val = root.left.val;
        }
        //计算左右子树中左叶子节点的和
        int lval = sumOfLeftLeaves(root.left);
        int rval = sumOfLeftLeaves(root.right);
        return val + lval + rval;
    }

    /**
     * 637. 二叉树的层平均值
     * https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0.0D;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                sum += node.val;

                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
            list.add(sum / size);
        }
        return list;
    }

    /**
     * 653. 两数之和 IV - 输入 BST
     * https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
     * 实际上和BST没有关系，只要是二叉树即可
     *
     * @param root
     * @param k
     *
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return findTargetHelper(root, k, set);
    }

    private boolean findTargetHelper(TreeNode root, int k, Set<Integer> set) {
        if (root == null)
            return false;
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return findTargetHelper(root.left, k, set) || findTargetHelper(root.right, k, set);
    }

    /**
     * 965. 单值二叉树
     * https://leetcode.cn/problems/univalued-binary-tree/
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null){
            return true;
        }
        int val = root.val;
        if (root.right != null && root.right.val != val){
            return false;
        }
        if (root.left != null && root.left.val != val){
            return false;
        }

        return isUnivalTree(root.left)
                && isUnivalTree(root.right);
    }

    /**
     * 1022. 从根到叶的二进制数之和
     * https://leetcode-cn.com/problems/sum-of-root-to-leaf-binary-numbers/
     *
     * @param root
     *
     * @return
     */
    public int sumRootToLeaf(TreeNode root) {
        return sumRootToLeafHelper(root, 0);
    }

    private int sumRootToLeafHelper(TreeNode node, int sum) {
        if (node == null) return 0;
        sum = (sum << 1) + node.val;
        if (node.left == null && node.right == null) return sum;

        return sumRootToLeafHelper(node.left, sum) + sumRootToLeafHelper(node.right, sum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        EasyTreeDemo demo = new EasyTreeDemo();
        demo.flatten(root);
    }

}
