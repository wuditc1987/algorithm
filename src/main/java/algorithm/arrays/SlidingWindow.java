package algorithm.arrays;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/6/30
 * @description
 * 滑动窗口指的是这样一类问题的求解方法，在数组上通过双指针同向移动而解决的一类问题。
 * 其实这样的问题我们可以不必为它们专门命名一个名字，它们的解法其实是很自然的。
 * 使用滑动窗口解决的问题通常是暴力解法的优化，掌握这一类问题最好的办法就是练习，然后思考清楚为什么可以使用滑动窗口。
 */
public class SlidingWindow {

    /**
     * 3.无重复字符的最长子串 leetcode 3
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        //key 遇到的字符
        //value 最近一次遇到字符的位置
        Map<Character, Integer> map = new HashMap<>(s.length());
        //max为最大长度，left为遇到重复的字符最左边字符的位置
        int max = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, i);
            max = Math.max(max, i - left + 1);
        }

        return max;
    }

    /**
     * 209. 长度最小的子数组 滑动窗口
     * https://leetcode.cn/problems/minimum-size-subarray-sum/
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        if (len == 0){
            return 0;
        }
        //数组最小长度
        int min = Integer.MAX_VALUE;
        //数组间距
        int lo = 0, hi = 0;
        //值和
        int sum = 0;
        while (hi < len){
            //从数组尾部加
            sum += nums[hi++];
            //当和大于目标值时
            while (sum >= target){
                //取到长度最小的值
                //每次都需要判断一下当前数组取值长度是否是最小
                min = Math.min(min, hi - lo);
                //从数组头开始减
                sum -= nums[lo++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * 219. 存在重复元素 II
     * https://leetcode.cn/problems/contains-duplicate-ii/
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 220. 存在重复元素 III
     * 桶排序
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length == 0 || t < 0){
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;

        for (int i = 0; i < nums.length; i++){
            long val = getBucketValue(nums[i], w);
            // 同一个桶中如果有值，则直接返回
            if (map.containsKey(val)){
                return true;
            }
            //相邻的桶中如有值，则判断距离是否符合条件
            if(map.containsKey(val - 1)
                    && Math.abs((long) nums[i] - map.get(val - 1)) < w){
                return true;
            }

            if(map.containsKey(val + 1)
                    && Math.abs((long) nums[i] - map.get(val + 1)) < w){
                return true;
            }
            map.put(val, (long) nums[i]);

            // 如果后边有相同的桶，则直接删除
            if (i >= k){
                map.remove(getBucketValue(nums[i - k], w));
            }
        }
        return false;
    }
    private long getBucketValue(long num, long w){
        if (num >= 0){
            return num / w;
        }
        return (num + 1) / w - 1;
    }



    /**
     * 643. 子数组最大平均数 I
     * https://leetcode.cn/problems/maximum-average-subarray-i/
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        // 先取前k个数相加
        for (int i = 0; i < k; i++){
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < nums.length; i ++){
            // 从下标为0开始减，加入尾部数字
            sum = sum - nums[i - k] + nums[i];
            // 比较原数和修改后的数的大小
            maxSum = Math.max(sum, maxSum);
        }

        return 1.0 * maxSum / k;
    }

    /**
     * 1004. 最大连续1的个数 III
     * https://leetcode.cn/problems/max-consecutive-ones-iii/
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int len = nums.length;
        int res = 0;
        int l = 0, r = 0, zeros = 0;
        while(r < len){
            if(nums[r] == 0){
                zeros ++;
            }
            while(zeros > k){
                if (nums[l++] == 0) {
                    zeros --;
                }
            }
            res = Math.max(res, r - l + 1);
            r ++;
        }
        return res;
    }

    /**
     * 1343. 大小为 K 且平均值大于等于阈值的子数组数目
     * https://leetcode.cn/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
     * @param arr
     * @param k
     * @param threshold
     * @return
     */
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0;
        for (int i = 0 ; i < k; i ++){
            sum += arr[i];
        }
        int res = sum >= k * threshold ? 1 : 0;
        for (int i = k; i < arr.length; i++){
            sum -= arr[i - k];
            sum += arr[i];
            if (sum >= k * threshold){
                res ++;
            }
        }
        return res;
    }

    private boolean numOfSubarraysHelper(int[] nums, int val){
        int sum = 0;

        return sum >= val;
    }



    /**
     * 1876. 长度为三且各字符不同的子字符串
     * https://leetcode.cn/problems/substrings-of-size-three-with-distinct-characters/
     * @param s
     * @return
     */
    public static int countGoodSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length() - 2; i++){
            String str = s.substring(i, i + 3);
            if (countGoodSubstringsUnique(str)){
                res ++;
            }
        }
        return res;
    }
    private static boolean countGoodSubstringsUnique(String str){
        char a = str.charAt(0);
        char b = str.charAt(1);
        char c = str.charAt(2);
        int res = a ^ b ^ c;
        return res != a && res != b && res != c;
    }

    /**
     * 1984. 学生分数的最小差值
     * https://leetcode.cn/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
     * @param nums
     * @param k
     * @return
     */
    public int minimumDifference(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        // 找到
        for (int i = 0; i + k - 1 < nums.length; i ++){
            min = Math.min(min, Math.abs(nums[i + k - 1] - nums[i]));
        }

        return min;
    }

    public static void main(String[] args) {
        String s = "aababcabc";
        System.out.println(countGoodSubstrings(s));
    }
}
