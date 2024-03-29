package algorithm.tree;

import algorithm.utils.Print;
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
     * 98. 验证二叉搜索树
     * https://leetcode.cn/problems/validate-binary-search-tree
     * https://leetcode.cn/problems/legal-binary-search-tree-lcci/
     *
     * 右子树的左子树的值如果不在
     *
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

    /**
     * 98. 验证二叉搜索树  解法2
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null){
            return true;
        }
        List<Integer> list = new ArrayList<>();
        TreeNode.inorder(root, list);
        for (int i = 1; i < list.size(); i++){
            if (list.get(i - 1) >= list.get(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 99. 恢复二叉搜索树
     * https://leetcode.cn/problems/recover-binary-search-tree/
     * @param root
     */
    public static void recoverTree(TreeNode root) {
        if (root == null){
            return ;
        }
        List<Integer> list = new ArrayList<>();
        //中序遍历获取值列表
        TreeNode.inorder(root, list);
        // 找到错误的两个节点值
        int x = 0,y = 0, count = 0;
        for (int i = 0, size = list.size() - 1; i < size; i++){
            if (list.get(i) > list.get(i+1)){
                //找到第一个节点
                if (count == 0){
                    x = list.get(i);
                }
                //如果后续没有符合list.get(i) > list.get(i+1)条件的值，则是两个相邻节点之间互换
                y = list.get(i + 1);
                count++;
            }
        }
        //恢复
        recoverTreeHelper(root, x, y, 2);
    }
    public static void recoverTreeHelper(TreeNode root, int x, int y, int count){
        if (root != null){
            if (root.val == x || root.val == y){
                root.val = root.val == y ? x : y;
                if (--count == 0){
                    return;
                }
            }
            recoverTreeHelper(root.left, x, y, count);
            recoverTreeHelper(root.right, x, y, count);
        }
    }

    /**
     * 113. 路径总和 II
     * https://leetcode.cn/problems/path-sum-ii/
     * @param root
     * @param targetSum
     * @return
     */
    List<List<Integer>> pathSumRes = new ArrayList<>();
    Deque<Integer> path = new LinkedList<Integer>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        pathSumHelper(root, targetSum);
        return pathSumRes;
    }
    private void pathSumHelper(TreeNode node, int target){
        if (node == null){
            return;
        }
        path.offerLast(node.val);

        if (node.left == null && node.right == null && target == node.val){
            pathSumRes.add(new LinkedList<>(path));
        }
        target -= node.val;
        pathSumHelper(node.left, target);
        pathSumHelper(node.right, target);
        path.pollLast();
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
        flattenPreorder(root, list);
        for (int i = 0, len = list.size(); i < len - 1; i++) {
            TreeNode node = list.get(i);
            node.right = list.get(i + 1);
            node.left = null;
        }
        Print.printTreeNodePreorder(root);
    }
    private void flattenPreorder(TreeNode node, List<TreeNode> list) {
        if (node != null) {
            list.add(node);
            flattenPreorder(node.left, list);
            flattenPreorder(node.right, list);
        }
    }

    /**
     * 129. 求根节点到叶节点数字之和
     * https://leetcode.cn/problems/sum-root-to-leaf-numbers/
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return sumNumbersHelper(root, 0);
    }
    private int sumNumbersHelper(TreeNode node, int sum){
        if (node == null){
            return 0;
        }
        int s = sum * 10 + node.val;
        if (node.left == null && node.right == null){
            return s;
        }
        return sumNumbersHelper(node.left, s) + sumNumbersHelper(node.right, s);
    }

    /**
     * 199. 二叉树的右视图
     * https://leetcode.cn/problems/binary-tree-right-side-view/
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (i == queue.size() - 1){
                    list.add(node.val);
                }
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return list;
    }

    /**
     * 199. 二叉树的右视图 解法2
     *
     * 按照  根->右->左 深度遍历
     * @param root
     * @return
     */
    List<Integer> rightSideView2List = new ArrayList<>();
    public List<Integer> rightSideView2(TreeNode root) {
        rightSideView2Helper(root, 0);
        return rightSideView2List;
    }
    private void rightSideView2Helper(TreeNode node, int depth){
        if (node == null){
            return;
        }
        if (depth == rightSideView2List.size()){
            rightSideView2List.add(node.val);
        }
        depth ++;
        rightSideView2Helper(node.right, depth);
        rightSideView2Helper(node.left, depth);
    }



    /**
     * 226. 翻转二叉树 DFS
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
     * https://leetcode.cn/problems/first-common-ancestor-lcci/
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
     * 331. 验证二叉树的前序序列化  TODO
     * https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/
     * @param preorder
     * @return
     */
    public static boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(1);
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#'){
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 437. 路径总和 III  TODO
     * https://leetcode.cn/problems/path-sum-iii/
     * 解法:
     * https://leetcode.cn/problems/path-sum-iii/solution/qian-zhui-he-di-gui-hui-su-by-shi-huo-de-xia-tian/
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSumIII(TreeNode root, int targetSum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路
        return pathSumIIIHelper(root, prefixSumCount, targetSum, 0);
    }
    private int pathSumIIIHelper(TreeNode node, Map<Integer, Integer> prefixSumCount, int sum, int currSum){
        // 1.递归终止条件
        if (node == null){
            return 0;
        }
        int res = 0;
        // 当前路径上的和
        currSum += node.val;
        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - sum, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += pathSumIIIHelper(node.left, prefixSumCount, sum, currSum);
        res += pathSumIIIHelper(node.right, prefixSumCount, sum, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }

    /**
     * 450. 删除二叉搜索树中的节点
     * https://leetcode.cn/problems/delete-node-in-a-bst/
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }
        // 删除的是根节点
        if(root.val == key){
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            // 如果删除的根节点含有左右子树，则需要重新构建子树，从左子树或右子树重新构建都可以。
            // 需要找到对应的根节点
            TreeNode temp = root.left;
            while(temp.right != null){
                temp = temp.right;
            }
            // 删除对应的节点
            temp.right = root.right;
            return root.left;
        }else if(root.val < key){
            // 从右子树找对应的节点删除
            root.right = deleteNode(root.right, key);
        } else {
            // 从左子树找对应的节点删除
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    /**
     * 513. 找树左下角的值
     * https://leetcode.cn/problems/find-bottom-left-tree-value/
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        int res = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            //从右向左添加节点，访问到最后就是最深处的最左侧的叶子结点
            TreeNode node = queue.poll();
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            res = node.val;
        }
        return res;
    }

    /**
     * 515. 在每个树行中找最大值
     * https://leetcode.cn/problems/find-largest-value-in-each-tree-row/
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                max = Math.max(node.val, max);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            result.add(max);
        }
        return result;
    }

    /**
     * 623. 在二叉树中增加一行  (DFS)
     * https://leetcode.cn/problems/add-one-row-to-tree/
     * @param root
     * @param val
     * @param depth
     * @return
     */
    public TreeNode addOneRowDFS(TreeNode root, int val, int depth) {
        if (root == null){
            return null;
        }
        if(depth == 1){
            return new TreeNode(val, root, null);
        }
        if (depth == 2){
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
        }else {
            root.left = addOneRowDFS(root.left, val, depth - 1);
            root.right = addOneRowDFS(root.right, val, depth - 1);
        }
        return root;
    }

    /**
     * 623. 在二叉树中增加一行  (BFS)
     * @param root
     * @param val
     * @param depth
     * @return
     */
    public TreeNode addOneRowBFS(TreeNode root, int val, int depth) {
        if (depth == 1){
            return new TreeNode(val, root, null);
        }
        List<TreeNode> addNodes = new ArrayList<>();
        addNodes.add(root);
        for (int i = 0; i < depth - 1; i ++){
            List<TreeNode> temp = new ArrayList<>();
            for (TreeNode node : addNodes){
                if (node.left != null){
                    addNodes.add(node.left);
                }
                if (node.right != null){
                    addNodes.add(node.right);
                }
            }
            addNodes = temp;
        }
        for (TreeNode node : addNodes){
            node.right = new TreeNode(val, null, node.right);
            node.left = new TreeNode(val, node.left, null);
        }
        return root;
    }

    /**
     * 654. 最大二叉树
     * https://leetcode.cn/problems/maximum-binary-tree/
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTreeHelper(nums, 0, nums.length);
    }
    private TreeNode constructMaximumBinaryTreeHelper(int[] nums, int low, int high){
        if (low == high){
            return null;
        }
        int maxIndex = constructMaximumBinaryTreeMaxIndex(nums, low, high);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = constructMaximumBinaryTreeHelper(nums, low, maxIndex);
        root.right = constructMaximumBinaryTreeHelper(nums, maxIndex + 1, high);
        return root;
    }
    private int constructMaximumBinaryTreeMaxIndex(int[] nums, int low, int high){
        int maxIndex = low;
        for (int i = low; i < high; i++) {
            if (nums[maxIndex] < nums[i])
                maxIndex = i;
        }
        return maxIndex;
    }

    /**
     * 662. 二叉树最大宽度
     * https://leetcode.cn/problems/maximum-width-of-binary-tree/
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        int width = 0;
        if(root == null){
            return width;
        }
        // 保存树节点
        Queue<TempNode> queue = new LinkedList<>();
        // 放入根节点, 根节点的位置索引为0，深度为0
        queue.offer(new TempNode(root, 0, 0));
        int leftPos = 0, currDepth = 0;
        while(!queue.isEmpty()){
            TempNode temp = queue.poll();
            int pos = temp.pos;
            if (temp.node != null){
                // 左节点的初始位置索引为 2* pos, 深度+1
                queue.add(new TempNode(temp.node.left, 2 * pos, temp.depth + 1));
                // 右节点的初始位置索引为 2* pos + 1， 深度+1
                queue.add(new TempNode(temp.node.right, 2 * pos + 1, temp.depth + 1));
                // 如果当前深度与当前节点深度不一致，则说明是向下一层遍历了, 第一次遍历的时候的第一个节点即是最左侧节点
                if (currDepth != temp.depth){
                    // 记录当前深度
                    currDepth = temp.depth;
                    // 记录最左侧节点位置索引
                    leftPos = temp.pos;
                }
                width = Math.max(width, pos - leftPos + 1);
            }
        }
        return width;
    }

    /**
     * 669. 修剪二叉搜索树
     * https://leetcode.cn/problems/trim-a-binary-search-tree/
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null){
            return null;
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }else if (root.val < low){
            return trimBST(root.right, low, high);
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }



    /**
     * 687. 最长同值路径
     * https://leetcode.cn/problems/longest-univalue-path/
     * @param root
     * @return
     */
    int longestUnivaluePathMax = 0;
    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathHelper(root);
        return longestUnivaluePathMax;
    }

    public int longestUnivaluePathHelper(TreeNode node){
        if(node == null){
            return 0;
        }
        int left = longestUnivaluePathHelper(node.left);
        int right = longestUnivaluePathHelper(node.right);
        int val = node.val;
        int l = 0, r = 0;
        if(node.left != null && node.left.val == val){
            l += left + 1;
        }
        if(node.right != null && node.right.val == val){
            r += right + 1;
        }
        longestUnivaluePathMax = Math.max(longestUnivaluePathMax, l + r);
        return Math.max(l, r);
    }

    /**
     * 701. 二叉搜索树中的插入操作 (解法1)
     * 1.先中序遍历节点到列表中
     * 2.加入需要插入的节点
     * 3.重新构建二叉搜索树
     * https://leetcode.cn/problems/insert-into-a-binary-search-tree/
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            return new TreeNode(val);
        }
        List<Integer> list = new ArrayList<>();
        TreeNode.inorder(root, list);
        if (list.contains(val)){
            return root;
        }
        list.add(val);
        Collections.sort(list);

        return insertIntoBSTHelper(list, 0, list.size() - 1);
    }

    private TreeNode insertIntoBSTHelper(List<Integer> list, int low, int high){
        if (low > high){
            return null;
        }
        int mid = low + (high - low)/2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = insertIntoBSTHelper(list, low, mid - 1);
        root.right = insertIntoBSTHelper(list, mid + 1, high);
        return root;
    }

    /**
     * 701. 二叉搜索树中的插入操作 (解法2)
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null){
            return new TreeNode(val);
        }
        if (val < root.val){
            root.left = insertIntoBST2(root.left, val);
        }else {
            root.right = insertIntoBST2(root.right, val);
        }
        return root;
    }

    /**
     * 1302. 层数最深叶子节点的和
     * https://leetcode.cn/problems/deepest-leaves-sum/
     * @param root
     * @return
     */
    int deepestLeavesSum = 0;
    int deepestLeavesSumDepth = -1;
    public int deepestLeavesSum(TreeNode root) {
        deepestLeavesSumHelper(root, 0);
        return deepestLeavesSum;
    }
    private void deepestLeavesSumHelper(TreeNode node, int depth){
        if (node == null){
            return;
        }
        // 前序遍历即可，无论是前中后序遍历，都需要在根节点进行计算。
        // 当前深度大于最大深度时，说明还没有遍历到最深处的叶子结点，
        if (deepestLeavesSumDepth < depth){
            deepestLeavesSumDepth = depth;
            deepestLeavesSum = node.val;
        }else if (deepestLeavesSumDepth == depth){
            // 当前深度等于最大深度时，进行累加
            deepestLeavesSum += node.val;
        }
        // 遍历左右叶子节点
        deepestLeavesSumHelper(node.left, depth + 1);
        deepestLeavesSumHelper(node.right, depth + 1);
    }

    public static void main(String[] args) {
        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

}

class TempNode{
    TreeNode node;
    int pos;
    int depth;
    public TempNode(TreeNode node, int pos, int depth){
        this.node = node;
        this.pos = pos;
        this.depth = depth;
    }
}
