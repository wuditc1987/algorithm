package algorithm.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/8/31
 * @description 前缀和算法
 */
public class PrefixSum {

    /**
     * 1. 两数之和
     * https://leetcode.cn/problems/two-sum/
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i])){
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 560. 和为 K 的子数组
     * https://leetcode.cn/problems/subarray-sum-equals-k/
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        // key -> 前缀和， value -> 对应的前缀和的个数
        Map<Integer, Integer> map = new HashMap<>();
        // 因为每个前缀和为0的元素在数组里只能为1个，保证了合为0的情况有且只有一个
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int num : nums){
            sum += num;
            if (map.containsKey(sum - k)){
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    /**
     * 560. 和为 K 的子数组
     * @param nums  解法2
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i ++){
            int sum = 0;
            for (int j = i; j >= 0; j --){
                sum += nums[j];
                if (sum == k){
                    count ++;
                }
            }
        }
        return count;
    }

    /**
     * 724. 寻找数组的中心下标
     * https://leetcode.cn/problems/find-pivot-index/solution/
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        // 中心点左侧和右侧的和都为sum
        int sum = 0;
        for (int i = 0; i < nums.length; i ++){
            // 2 * sum + num[i] = total
            if (sum * 2 + nums[i] == total){
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }

    /**
     * 1480. 一维数组的动态和
     * https://leetcode.cn/problems/running-sum-of-1d-array/
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        int[] arr = new int[nums.length];
        arr[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            arr[i] = arr[i - 1] + nums[i];
        }
        return arr;
    }

    /**
     * 1893. 检查是否区域内所有整数都被覆盖
     * https://leetcode.cn/problems/check-if-all-the-integers-in-a-range-are-covered/
     * 解法：差分数组
     * https://leetcode.cn/problems/check-if-all-the-integers-in-a-range-are-covered/solution/yi-ti-san-jie-bao-li-you-hua-chai-fen-by-w7xv/
     * @param ranges
     * @param left
     * @param right
     * @return
     */
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        //对差分数组进行处理
        for(int i = 0; i < ranges.length; i++){
            diff[ranges[i][0]]++;
            diff[ranges[i][1]+1]--;
        }
        // 差分数组前缀和
        for(int i = 1; i <= 51; i++){
            diff[i] = diff[i-1] + diff[i];
        }
        //从left到right判断是否满足sum > 0
        for(int i = left; i <= right; i++){
            if(diff[i] <= 0) {
                return false;
            }
        }
        return true;
    }
}
