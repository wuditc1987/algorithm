package algorithm.utils;

import java.util.*;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/9/4
 * @description TODO
 */
public class Test {

    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int low = 0, high = 0;
        int sum = 0;
        while (high < len){
            sum += nums[high ++];
            while (sum >= target){
                min = Math.min(min, high - low);
                sum -= nums[low ++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
    }
}
