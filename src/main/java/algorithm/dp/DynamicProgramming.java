package algorithm.dp;

import java.util.Arrays;
import java.util.List;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/6/30
 * @description 动态规划
 */
public class DynamicProgramming {

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
     * 剑指 Offer 10- I. 斐波那契数列  动态规划
     * https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
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

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }
}
