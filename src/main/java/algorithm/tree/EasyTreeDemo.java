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
     * 100. 相同的树
     * https://leetcode.cn/problems/same-tree/
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }
        if(p == null || q == null){
            return false;
        }
        if(p.val != q.val){
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 101. 对称二叉树
     * 层序遍历
     * https://leetcode.cn/problems/symmetric-tree/
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right){
        if (left == null && right == null){
            return true;
        }
        if (left == null || right == null){
            return false;
        }

        if (left.val != right.val){
            return false;
        }
        return isSymmetricHelper(left.left, right.right)
                && isSymmetricHelper(left.right, right.left);
    }

    /**
     * 110. 平衡二叉树
     * https://leetcode-cn.com/problems/balanced-binary-tree/
     * https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/
     * https://leetcode.cn/problems/check-balance-lcci/
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
     * 112. 路径总和
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
     * 257. 二叉树的所有路径
     * https://leetcode.cn/problems/binary-tree-paths/
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        binaryTreePathsHelper(root, list, "");
        return list;
    }

    private void binaryTreePathsHelper(TreeNode node, List<String> list, String path){
        if (node == null){
            return ;
        }
        StringBuilder sb = new StringBuilder(path);
        sb.append(node.val);
        //叶子节点时，直接将路径放入list中，待返回
        if (node.left == null && node.right == null){
            list.add(sb.toString());
        }else {
            //非叶子节点递归查找对应的路径
            sb.append("->");
            binaryTreePathsHelper(node.left, list, sb.toString());
            binaryTreePathsHelper(node.right, list, sb.toString());
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
     * 530. 二叉搜索树的最小绝对差
     * 最小绝对差一定是两个相邻节点的差值，使用中序遍历即可
     * https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
     * https://leetcode.cn/problems/minimum-distance-between-bst-nodes/
     * @param root
     * @return
     */
    //前驱节点的值
    int getMinimumDifferencePre = -1;
    //前驱节点和当前节点的差值
    int getMinimumDifferenceSub = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        getMinimumDifferenceHelper(root);
        return getMinimumDifferenceSub;
    }
    private void getMinimumDifferenceHelper(TreeNode node){

        // 中序遍历节点
        if (node == null){
            return ;
        }
        getMinimumDifferenceHelper(node.left);
        if (getMinimumDifferencePre != -1){
            getMinimumDifferenceSub = Math.min(getMinimumDifferenceSub, node.val - getMinimumDifferencePre);
        }
        getMinimumDifferencePre = node.val;

        getMinimumDifferenceHelper(node.right);
    }

    /**
     * 543. 二叉树的直径
     * https://leetcode.cn/problems/diameter-of-binary-tree/
     * @param root
     * @return
     */
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        diameterOfBinaryTreeHelper(root);
        return diameter;
    }
    private int diameterOfBinaryTreeHelper(TreeNode node){
        if (node == null){
            return 0;
        }
        int l = diameterOfBinaryTreeHelper(node.left);
        int r = diameterOfBinaryTreeHelper(node.right);
        diameter = Math.max(l + r, diameter);
        return Math.max(l, r) + 1;
    }

    /**
     * 563. 二叉树的坡度
     * https://leetcode.cn/problems/binary-tree-tilt/
     * @param root
     * @return
     */
    int findTiltAns = 0;
    public int findTilt(TreeNode root) {
        findTiltHelper(root);
        return findTiltAns;
    }
    private int findTiltHelper(TreeNode node){
        if (node == null){
            return 0;
        }
        int leftNodeSum = findTiltHelper(node.left);
        int rightNodeSum = findTiltHelper(node.right);
        findTiltAns += Math.abs(leftNodeSum - rightNodeSum);
        return leftNodeSum + rightNodeSum + node.val;
    }

    /**
     * 572. 另一棵树的子树
     * https://leetcode.cn/problems/subtree-of-another-tree/
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null){
            return true;
        }
        if (root == null || subRoot == null){
            return true;
        }
        return isSubtree(root.left, subRoot)
                || isSubtree(root.right, subRoot)
                || isSameTree(root, subRoot);
    }

    /**
     * 617. 合并二叉树
     * https://leetcode.cn/problems/merge-two-binary-trees/
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null){
            return root2;
        }
        if (root2 == null){
            return root1;
        }
        int sum = root1.val + root2.val;
        TreeNode root = new TreeNode(sum);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
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
     * https://leetcode.cn/problems/opLdQZ/
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
     * 671. 二叉树中第二小的节点  TODO
     * https://leetcode.cn/problems/second-minimum-node-in-a-binary-tree/
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        return findSecondMinimumValueHelper(root, root.val);
    }
    private int findSecondMinimumValueHelper(TreeNode node, int min){
        if (node == null){
            return -1;
        }
        if (node.val > min){
            return node.val;
        }
        int left = findSecondMinimumValueHelper(node.left, min);
        int right = findSecondMinimumValueHelper(node.right, min);
        if (left == -1){
            return right;
        }
        if (right == -1){
            return left;
        }
        return Math.min(left, right);
    }

    /**
     * 872. 叶子相似的树
     * https://leetcode.cn/problems/leaf-similar-trees/
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        String str1 = leafSimilarHelper(root1, "");
        String str2 = leafSimilarHelper(root2, "");
        return str1.equals(str2);
    }
    private String leafSimilarHelper(TreeNode node, String str){
        if (node == null){
            return str;
        }
        if (node.right == null && node.left == null){
            str += "-" + node.val;
            return str;
        }
        return leafSimilarHelper(node.left, str) + leafSimilarHelper(node.right, str);
    }

    public boolean leafSimilar2(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        leafSimilar2Helper(root1, list1);

        List<Integer> list2 = new ArrayList<>();
        leafSimilar2Helper(root2, list2);

        return list1.equals(list2);
    }
    private void leafSimilar2Helper(TreeNode node, List<Integer> list){
        if (node == null){
            return ;
        }
        if (node.left == null && node.right == null){
            list.add(node.val);
        }else {
            leafSimilar2Helper(node.left, list);
            leafSimilar2Helper(node.right, list);
        }
    }

    /**
     * 938. 二叉搜索树的范围和
     * https://leetcode.cn/problems/range-sum-of-bst/
     * @param root
     * @param low
     * @param high
     * @return
     */
    int rangeSumBSTSum = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        rangeSumBSTHelper(root, low, high);
        return rangeSumBSTSum;
    }
    private void rangeSumBSTHelper(TreeNode node, int low, int high){
        if (node == null){
            return;
        }
        rangeSumBSTHelper(node.left, low, high);
        if (node.val >= low && node.val <= high){
            rangeSumBSTSum += node.val;
        }
        rangeSumBSTHelper(node.right, low, high);
    }

    /**
     * 965. 单值二叉树  TODO
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

    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        TreeNode.inorder(root, list);
        return list.get(list.size() - k);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        EasyTreeDemo demo = new EasyTreeDemo();
//        demo.flatten(root);
    }

}
