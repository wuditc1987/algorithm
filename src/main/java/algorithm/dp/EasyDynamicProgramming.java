package algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/6/30
 * @description 动态规划
 */
public class EasyDynamicProgramming {

    /**
     * 70. 爬楼梯
     * https://leetcode.cn/problems/climbing-stairs/
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n == 0 || n == 1){
            return n;
        }
        int first, second = 0;
        // 结果集：可以保证至少有一个结果
        int result = 1;
        for (int i = 1; i <= n; i ++){
            first = second;
            second = result;
            result = first + second;
        }
        return result;
    }

    /**
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n){
        if (n <= 2){
            return n;
        }
        // n = 1, n = 2;
        // dp[1] = 1, dp[2] = 2;
        int first = 1, second = 2;
        int result = 0;
        while (n >= 3){
            result = first + second;
            first = second;
            second = result;
            n--;
        }
        return result;
    }

    /**
     *
     *
     * 剑指 Offer 10- I. 斐波那契数列  动态规划
     * https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
     * https://leetcode.cn/problems/fibonacci-number/
     *
     * 状态定义： 设 dp 为一维数组，其中 dp[i] 的值代表 斐波那契数列第 i 个数字 。
     * 转移方程：  dp[i + 1] = dp[i] + dp[i - 1]  ，即对应数列定义 f(n + 1) = f(n) + f(n - 1)；
     * 初始状态： dp[0] = 0, dp[1] = 1 ，即初始化前两个数字；
     * 返回值： dp[n] ，即斐波那契数列的第 n 个数字。
     *
     * @description 斐波那契数列
     * @author wudi
     * @date 2018年3月7日
     * @return
     */
    public static int fibonacciSequence(int n){
        if (n < 2){
            return n;
        }
        // f(n-1) f(n-2)
        int minusOne = 1, minusTwo = 0;
        // f(n)
        int res;
        while (n >= 2){
            res = (minusOne + minusTwo) % 1000000007;
            minusTwo = minusOne;
            minusOne = res;
            n--;
        }
        return 0;
    }

    /**
     * 118. 杨辉三角
     * https://leetcode.cn/problems/pascals-triangle/
     * @param numRows
     * @return
     */
    public List<List<Integer>> pascalsTriangle(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0 ; i < numRows; i ++){
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++){
                // 三角形的边
                if (j == 0 || j == i){
                    row.add(1);
                }else {
                    //计算中间值
                    //上一层的值
                    int val1 = lists.get(i - 1).get(j - 1);
                    int val2 = lists.get(i - 1).get(j);
                    row.add(val1 + val2);
                }
            }
            lists.add(row);
        }
        return lists;
    }

    /**
     * 119. 杨辉三角 II
     * https://leetcode.cn/problems/pascals-triangle-ii/
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < rowIndex; i++){
            List<Integer> cur = new ArrayList<>();
            for (int j = 0 ; j <= i; j++){
                if (j == 0 || j == i){
                    cur.add(1);
                }else {
                    cur.add(row.get(j - 1) + row.get(j));
                }
            }
            row = cur;
        }
        return row;
    }


    /**
     * 120. 三角形最小路径和
     * https://leetcode.cn/problems/triangle/
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int[] mini = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--){
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++){
                mini[j] = list.get(j) + Math.min(mini[j], mini[j+1]);
            }
        }
        return mini[0];
    }

    /**
     * 152. 乘积最大子数组
     * https://leetcode.cn/problems/maximum-product-subarray/
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i ++){
            if (nums[i] < 0){
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(nums[i] * imax, nums[i]);
            imin = Math.min(nums[i] * imin, nums[i]);
            max = Math.max(imax, max);
        }
        return max;
    }

    /**
     * 300. 最长递增子序列
     * https://leetcode.cn/problems/longest-increasing-subsequence/
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        // 1.定义状态 dp[i] 从0 -> i元素（选中）的最长子序列的长度
        int[] dp = new int[nums.length];
        // 返回结果 - 数组长度
        int res = 0;
        // 2.转移方程
        // dp[i] = max(dp[j] + 1, dp[i]),注：j -> [0,i) 且 nums[j] < nums[i]

        // 3.初始化状态  初始时，每个元素都至少可以单独成为子序列 因此长度为1
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 338. 比特位计数
     * https://leetcode.cn/problems/counting-bits/
     * https://leetcode.cn/problems/w3tCBm/
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        // 从1开始循环，因为res[0] 初始值即为0，可以忽略计算
        for (int i = 1; i <= n; i ++){
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }

    /**
     * 392. 判断子序列 ===== 双指针
     * https://leetcode.cn/problems/is-subsequence/
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if(s.equals(t)){
            return true;
        }
        int slen = s.length(), tlen = t.length();
        int i = 0, j = 0;
        while (i < slen && j < tlen){
            if (s.charAt(i) == t.charAt(j)){
                i ++;
            }
            j ++;
        }
        return i == slen;
    }

    /**
     * 392. 判断子序列 解法2 ===== 动态规划 =====
     * 解法 https://leetcode.cn/problems/is-subsequence/solution/pan-duan-zi-xu-lie-by-leetcode-solution/
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        if(s.equals(t)){
            return true;
        }
        int tlen = t.length();
        // 定义DP table 行为t中出现的字符, 26为小写字母数量
        // dp[i][j] 为第一次出现该字符的位置
        int[][] dp = new int[tlen + 1][26];
        // 填充最后一行数据
        for (int i = 0; i < 26; i ++){
            dp[tlen][i] = tlen;
        }

        // 定位字符串t中各个字符出现的位置
        for (int i = tlen - 1; i >= 0; i--){
            for (int j = 0; j < 26; j++){
                // 定位出现字符的位置
                if (t.charAt(i) == j + 'a'){
                    dp[i][j] = i;
                } else {
                    // 如果不是对应的字符，则将
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        // 初始状态(位置)
        int index = 0;
        // 从dp table 中找对应的位置
        for (int i = 0; i < s.length(); i++){
            // 如果已经到了字符串t的末尾还没有找到，那么就直接返回false
            if (dp[index][s.charAt(i) - 'a'] == tlen){
                return false;
            }
            // 如果找到了,那么就在对应的位置上+1
            index = dp[index][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

    /**
     * 746. 使用最小花费爬楼梯
     * https://leetcode.cn/problems/min-cost-climbing-stairs/
     * https://leetcode.cn/problems/GzCJIP/
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        if(cost == null || cost.length == 0){
            return 0;
        }
        // 楼梯层数
        int len = cost.length;
        // 下标：楼梯数量
        // 值：最小花费
        int[] dp = new int[len + 1];

        // 1.初始化状态
        // 0层和1层时，不需要任何花费即可到达
        dp[0] = dp[1] = 0;
        // 只有2层时，挑出cost[0]和cost[1]中最小花费即可到达
        dp[2] = Math.min(cost[0], cost[1]);

        for (int i = 3; i <= len; i++){
            // 2.动态转移方程
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[len];
    }

    /**
     * 1025. 除数博弈 数学归纳法
     * https://leetcode.cn/problems/divisor-game/
     * @param n
     * @return
     */
    public boolean divisorGame(int n) {
        return (n & 1) == 0;
    }

    /**
     * 1137. 第 N 个泰波那契数
     * https://leetcode.cn/problems/n-th-tribonacci-number/
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        if(n <= 1){
            return n;
        }
        int t0 = 0, t1 = 0, t2 = 1;
        int res = 1;
        while(n > 2){
            t0 = t1;
            t1 = t2;
            t2 = res;
            res = t0 + t1 + t2;
            n--;
        }
        return res;
    }

    /**
     * 1646. 获取生成数组中的最大值
     * https://leetcode.cn/problems/get-maximum-in-generated-array/
     * @param n
     * @return
     */
    public int getMaximumGenerated(int n) {
        if(n < 2){
            return n;
        }
        int len = n + 1;
        int[] arr = new int[len + 1];
        arr[1] = 1;
        for (int i = 2; i <= n; i ++){
            // 偶数
            if ((i & 1) == 0){
                arr[i] = arr[i/2];
            }else {
                // 奇数
                arr[i] = arr[i/2] + arr[i/2 + 1];
            }
        }

        return Arrays.stream(arr).max().getAsInt();
    }

    public static void main(String[] args) {

    }
}
