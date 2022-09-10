package algorithm.dp;

import java.util.Arrays;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/9/1
 * @description 中等难度
 */
public class MediumDP {

    /**
     * 53. 最大子数组和
     * https://leetcode.cn/problems/maximum-subarray/
     * https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
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
     * 198. 打家劫舍
     * https://leetcode.cn/problems/house-robber/
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

    public static void main(String[] args) {
        int result = maxSubArray(new int[]{-2,1,-3});
        System.out.println(result);
    }
}
