package algorithm.arrays;

import java.util.HashMap;
import java.util.Map;

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
}
