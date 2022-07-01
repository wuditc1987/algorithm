package algorithm.dp;

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

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }
}
