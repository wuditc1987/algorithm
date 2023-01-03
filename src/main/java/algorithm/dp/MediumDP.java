package algorithm.dp;

import algorithm.utils.TreeNode;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/9/1
 * @description 中等难度
 */
public class MediumDP {

    /**
     * 5. 最长回文子串
     * https://leetcode.cn/problems/longest-palindromic-substring/
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()){
            return "";
        }
        // 起始位置作为中间点开始向外扩散查找
        int len = s.length();
        int start = 0, end = 0;
        for (int mid = 0; mid < len; mid++){
            // 字符长度为奇数时
            int lenOdd = longestPalindromeHelper(s, mid, mid);
            // 字符长度为偶数时
            int lenEven = longestPalindromeHelper(s, mid, mid + 1);
            int maxLen = Math.max(lenOdd, lenEven);
            if (maxLen > end - start){
                start = mid - (maxLen - 1)/2;
                end = mid + maxLen/2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int longestPalindromeHelper(String s, int left, int right){
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left --;
            right ++;
        }
        return right - left -1;
    }

    /**
     * 45. 跳跃游戏 II
     * https://leetcode.cn/problems/jump-game-ii/
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int len = nums.length;
        // 一共需要走的次数
        int steps = 0;
        // 走到的位置
        int pos = 0;
        // 走到的边界位置
        int boundary = 0;
        // 这里使用len-1 是因为起始位置从num[0]开始，已经有一步在其中了
        for (int i = 0; i < len - 1; i++){
            // nums[i] + i 为最终可以到达的位置
            pos = Math.max(pos, nums[i] + i);
            // 如果到达了边界，则步数+1， 边界指的是走完步数后到达的位置
            if (i == boundary){
                boundary = pos;
                steps ++;
            }
        }
        return steps;
    }

    /**
     * 53. 最大子数组和  有后效性（需要考虑之前的决策）
     * https://leetcode.cn/problems/maximum-subarray/
     * https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
     * https://leetcode.cn/problems/contiguous-sequence-lcci/
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        // 1.定义状态：下标：数组长度； 值：最大和
        int[] dp = new int[len + 1];
        // 2.初始化状态 数组只有一个元素时，最大值为数组第一个元素,即以XXX结尾的连续子数组最大和
        dp[0] = nums[0];
        int res = dp[0];
        // 3.状态转移方程:
        // (1) dp[i - 1] >= 0 ，dp[i] = dp[i - 1] + nums[i]
        // (2) 当dp[i - 1] < 0, dp[i] = nums[i] 不能将dp[i - 1]与nums[i]相加，会导致越加越小
        for (int i = 1; i < len; i ++){
            if (dp[i - 1] > 0){
                dp[i] = dp[i - 1] + nums[i];
            }else {
                dp[i] = nums[i];
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }

    /**
     * 55. 跳跃游戏
     * https://leetcode.cn/problems/jump-game/
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int pos = 0;
        for (int i = 0; i < len; i++){
            if (i > pos){
                return false;
            }
            // 可以到达的最大边界
            pos = Math.max(pos, nums[i] + i);
        }
        return true;
    }

    /**
     * 62. 不同路径
     * https://leetcode.cn/problems/unique-paths/
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        // 1.定义状态：m 行 n列有多少种不同走法
        int[][] dp = new int[m][n];

        // 2.初始化状态 只有一行或者一列时，只有一种走法。
        for (int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++){
            dp[0][n] = 1;
        }
        // 3.动态转移方程
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 62. 不同路径
     * 解法2
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        //只跟第几行第几列有关，从m+n-2步中抽出m-1步
        long ans = 1;
        for (int i = 0; i < Math.min(m - 1, n - 1); i++) {
            ans *= m + n - 2 - i;
            ans /= i + 1;
        }
        return (int) ans;
    }

    /**
     * 63. 不同路径 II
     * https://leetcode.cn/problems/unique-paths-ii/
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        // 1.定义状态：下标：数组长和宽，值：路径条数 dp[i][j]
        int[][] dp = new int[m][n];
        // 2.初始化状态 当没有障碍物时，才有路径，否则路径为0
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++){
            dp[i][0] = 1;
        }
        for (int i = 0; i < m && obstacleGrid[0][i] == 0; i++){
            dp[0][i] = 1;
        }
        // 3.动态转移方程
        // 当有障碍物时，dp[i][j] = 0;
        // 当没有障碍物时，dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        // 类似 62题
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                // 没有障碍物时，直接走即可
                if (obstacleGrid[i][j] == 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 64. 最小路径和
     * https://leetcode.cn/problems/minimum-path-sum/
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 1.定义状态 下标：数组格式， 值：代表的最小值
        int[][] dp = new int[m][n];

        // 2.初始化状态 只有一行或者一列数据的数组，直接相加即为最小值
        // 第一个位置即为grid[0][0]
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++){
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // 3.状态转移方程
        for (int i = 1; i < m; i ++){
            for (int j = 1; j < n; j ++){
                dp[i][j] += Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 91. 解码方法
     * https://leetcode.cn/problems/decode-ways/
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int len = s.length();
        // 1.定义状态：下标，字符串长度， 值：解码方法数量
        int[] dp = new int[len + 1];
        // 2.初始化状态：0个字符只有一个解法。
        dp[0] = 1;

        // 3.动态转移方程：
        // 使用一个字符进行解码时，可以解析出 A ~ I 共9种编码，
        // 使用两个字符进行解码时，可以解析出 J ~ Z 共17种编码，并且结果需要小于等于26
        for (int i = 1; i <= len; i++){
            if (s.charAt(i - 1) != '0'){
                dp[i] = dp[i - 1];
            }
            if (i >= 2){
                // 前面的数乘以10
                int temp = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
                if (temp >= 10 && temp <= 26){
//                    s[i - 1] 和 s[i - 2]组合解码
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[len];
    }

    /**
     * 96. 不同的二叉搜索树
     * https://leetcode.cn/problems/unique-binary-search-trees/
     * 题解
     * https://leetcode.cn/problems/unique-binary-search-trees/solution/hua-jie-suan-fa-96-bu-tong-de-er-cha-sou-suo-shu-b/
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        // 卡特兰数
        // G(n)=f(1)+f(2)+f(3)+f(4)+...+f(n)
        // f(i)=G(i−1)*G(n−i)
        // G(n)=G(0)*G(n−1)+G(1)*(n−2)+...+G(n−1)*G(0)
        for (int i = 2; i <= n; i++){
            for (int j = 1 ; j <= i; j++){
                dp[i] += dp[j-1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 120. 三角形最小路径和
     * https://leetcode.cn/problems/triangle/
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int[] mini = new int[triangle.size() + 1];
        for (int i = 0; i < triangle.size(); i++){
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++){
                mini[j] = list.get(j) + Math.min(mini[j], mini[j+1]);
            }
        }
//        for (int i = triangle.size() - 1; i >= 0; i--){
//            List<Integer> list = triangle.get(i);
//            for (int j = 0; j < list.size(); j++){
//                mini[j] = list.get(j) + Math.min(mini[j], mini[j+1]);
//            }
//        }
        return mini[triangle.size()];
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
     * @param prices
     * @return
     */
    public static int maxProfitII(int[] prices) {
        int len = prices.length;
        if (len < 2){
            return 0;
        }
        // 1.定义状态：dp[i][j] 表示第i天持有股票还是现金 j = 0持有现金， j = 1持有股票
        // 值为持有最大利润
        int[][] dp = new int[len][2];

        // 2.初始化状态 持有现金时最大值为0
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        // 3.状态转移方程
        for (int i = 1; i < len; i++){
            // 因为得到的是最大利润，因此需要保留其中的最大值
            // 持有现金时， 前一天持有现金 与 持有股票并卖出的值 进行比较，保留较大者
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 持有股票时， 前一天持有股票 与 持有现金并购入股票的值(利润) 进行比较，保留较大者
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 4.最终值：因为一定有dp[i][0] > dp[i][1]，即手中没有股票时的利润要大于持有股票的利润，因此最大利润即为dp[i][0]
        return dp[len - 1][0];
    }

    /**
     * 139. 单词拆分
     * https://leetcode.cn/problems/word-break/
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        // 1.定义状态  下标：字符串长度  值：是否出现在字典中
        boolean[] dp = new boolean[len + 1];
        // 2.初始化状态，空字符合法
        dp[0] = true;
        // 3.状态转移方程 dp[i]=dp[j] && check(s[j,i−1])
        for (int i = 1; i <= len; i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && wordDict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 198. 打家劫舍
     * https://leetcode.cn/problems/house-robber/
     * https://leetcode.cn/problems/the-masseuse-lcci/
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        if (len == 1){
            return nums[0];
        }
        // 下标：对应房间数量
        // 值：偷到房间中的最多的钱数
        int[] dp = new int[len];

        // 初始化原始状态
        // 只有一间房时
        dp[0] = nums[0];
        // 只有两间房时
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < len; i++){
            // 由于只能偷相隔房间中的钱，所以使用 下标i-2依次找到对应的最大钱数
            // 和前一间房间中的钱数进行比较，找到比较大的数
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        // 最后一间房间即为答案
        return dp[len - 1];
    }

    /**
     * 213. 打家劫舍 II
     * https://leetcode.cn/problems/house-robber-ii/
     * @param nums
     * @return
     */
    public static int robII(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        if(len == 1){
            return nums[0];
        } else if (len == 2){
            return Math.max(nums[0], nums[1]);
        }
        // 偷了第一个房间就不能偷最后一个房间
        return Math.max(robIIHelper(nums, 0, len - 2), robIIHelper(nums, 1, len - 1));
    }
    private static int robIIHelper(int[] nums, int start, int end){
        int[] dp = new int[end + 1];
        // 从start位置开始计算，即从0或者从1开始
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start+1]);
        for (int i = start + 2; i <= end; i++){
            dp[i] = Math.max(dp[i - 1], dp[i-2] + nums[i]);
        }
        return dp[end];
    }

    /**
     * 221. 最大正方形
     * https://leetcode.cn/problems/maximal-square/
     * 与 1277题类似
     * @see algorithm.dp.MediumDP#countSquares(int[][])
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int side = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return side;
        }

        int m = matrix.length, n = matrix[0].length;

        // 1.定义状态：下标：数组长和宽，值：
        int[][] dp = new int[m][n];
        // 2.初始化状态  默认dp数组中的值全部为0
        // 3.动态转移方程
        // 若某格子值为 1，则以此为右下角的正方形的、最大边长为：上面的正方形、左面的正方形或左上的正方形中，最小的那个，再加上此格。
        // dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == '1'){
                    if (i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                    }
                    side = Math.max(dp[i][j], side);
                }
            }
        }
        return side * side;
    }

    /**
     * 279. 完全平方数
     * https://leetcode.cn/problems/perfect-squares/
     * @param n
     * @return
     */
    public int numSquares(int n) {

        // 1.定义状态：数字为n的最少完全平方数的个数
        int[] dp = new int[n + 1];

        // 2.初始状态：0 = 0 * 0 即dp[0] = 0为边界条件

        // 3.状态转移方程：
        for (int i = 1; i <= n; i++){
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++){
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     * @param prices
     * @return
     */
    public int maxProfitIII(int[] prices) {
        int len = prices.length;

        // 1.定义状态：dp[i][j] 表示第i天持有股票还是现金 j = 0持有现金， j = 1冷冻期, j = 2持有股票
        // 冷冻期时即为已经卖出了股票，即：前一天持有股票的最大利润 + 当前股票金额
        // 值为持有最大利润
        int[][] dp = new int[len][3];

        // 2.初始化状态(边界条件)：第0天持有股票手中的最大利润为 -prices[0]
        // 隐含条件：
        // 1.第0天冷冻期 dp[0][1] = 0
        // 2.第0天持有现金的最大利润为 dp[0][0] = 0;
        dp[0][2] = -prices[0];

        for (int i = 1; i < len; i ++){
            // 持有股票
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] - prices[i]);
            // 冷冻期
            dp[i][1] = dp[i - 1][2] + prices[i];
            // 持有现金
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
        }
        // 4.最大利润为已经卖出股票后的状态，即max(dp[i][1], dp[i][0])
        return Math.max(dp[len - 1][1], dp[len - 1][0]);
    }

    /**
     * 322. 零钱兑换
     * https://leetcode.cn/problems/coin-change/
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];

        Arrays.fill(dp, max);
        // amount = 0 时，无需任何硬币即可兑换
        // dp[amount] 表示数组中amount值最小可兑换的硬币数量
        dp[0] = 0;

        for (int i = 1; i <= amount; i++){
            // 从coins中挑选
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 337. 打家劫舍 III
     * https://leetcode.cn/problems/house-robber-iii/
     * @param root
     * @return
     */
    public int robIII(TreeNode root) {
        if (root == null){
            return 0;
        }
        // 爷爷节点的值
        int money = root.val;
        // 左子树的值(孙子节点的值)
        if (root.left != null){
            money += robIII(root.left.left) + robIII(root.left.right);
        }
        // 右子树的值(孙子节点的值)
        if (root.right != null){
            money += robIII(root.right.right) + robIII(root.right.left);
        }
        // "孙子节点的钱" + "爷爷节点的钱" 与"父亲节点的钱"相比较，哪个多
        return Math.max(money, robIII(root.left) + robIII(root.right));
    }

    /**
     * 优化版
     * @param root
     * @return
     */
    public int robIII2(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>();
        return robIII2Helper(root, memo);
    }
    private int robIII2Helper(TreeNode node, Map<TreeNode, Integer> memo){
        if (node == null){
            return 0;
        }
        if (memo.containsKey(node)){
            return memo.get(node);
        }
        int val = node.val;
        if (node.left != null){
            val += robIII2Helper(node.left.left, memo) + robIII2Helper(node.left.right, memo);
        }
        if (node.right != null){
            val += robIII2Helper(node.right.left, memo) + robIII2Helper(node.right.right, memo);
        }
        int result = Math.max(val, robIII2Helper(node.left, memo) + robIII2Helper(node.right, memo));
        memo.put(node, result);
        return result;
    }

    /**
     * 1277. 统计全为 1 的正方形子矩阵
     * https://leetcode.cn/problems/count-square-submatrices-with-all-ones/
     * 与 221题 类似
     * @see MediumDP#maximalSquare(char[][])
     * 题解
     * https://leetcode.cn/problems/count-square-submatrices-with-all-ones/solution/tong-ji-quan-wei-1-de-zheng-fang-xing-zi-ju-zhen-2/
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        int res = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int m = matrix.length, n = matrix[0].length;
        // 1.定义状态：dp[i][j]表示以(i, j) 为右下角的正方形的最大边长
        int[][] dp = new int[m][n];
        // 2.初始化状态：默认dp[i]j]=0

        // 3.状态转移方程
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j ++){
                if (i == 0 || j == 0){
                    dp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0){
                    dp[i][j] = 0;
                } else {
                    // f[i][j] = Math.min(Math.min(f[i][j - 1], f[i - 1][j]), f[i - 1][j - 1]) + 1;
                    dp[i][j] = Math.min(Math.min(dp[i -1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                res += dp[i][j];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(maxProfitII(new int[]{7,1,5,3,6,4}));
    }
}
