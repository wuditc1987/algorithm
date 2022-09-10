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
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(6);
        List<String> list = new ArrayList<>();
        preorderWithSymbol(root, list);
        System.out.println(list);
    }

    private static void preorderWithSymbol(TreeNode root, List<String> list){
        if (root != null){
            list.add(String.valueOf(root.val));
            preorderWithSymbol(root.left, list);
            preorderWithSymbol(root.right, list);
        }else {
            list.add("#");
        }
    }


}
