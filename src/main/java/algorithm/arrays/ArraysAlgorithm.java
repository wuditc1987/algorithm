package algorithm.arrays;

import algorithm.utils.Print;

import java.util.*;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/24
 * @description 数组相关算法
 */
public class ArraysAlgorithm {

    /**
     * 26. 删除有序数组中的重复项
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        //计数
        int l = 0;
        for(int i = 0,len = nums.length; i < len;i++){
            //将非重复项全部向前移动，
            if(i == 0 || nums[i] != nums[i-1]){
                nums[l++] = nums[i];
            }
        }
        return l;
    }

    /**
     * 215. 数组中的第K个最大元素
     * https://leetcode.cn/problems/kth-largest-element-in-an-array/
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 347. 前 K 个高频元素
     * https://leetcode.cn/problems/top-k-frequent-elements/
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        //key -> 数字， value -> 出现次数
        Map<Integer,Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num,map.getOrDefault(num,0) + 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));

        for (Integer key : map.keySet()){
            //当队列数量小于k时，将数字加到队列中
            if(queue.size() < k){
                queue.add(key);
            }else if(map.get(key) > map.get(queue.peek())){
                //当队列中的数量大于等于k时，且出现频率大于头部元素时，删除头部元素，并添加新元素
                queue.remove();
                queue.add(key);
            }
        }

        // 取出最小堆中的元素
        int[] res = new int[k];
        while (!queue.isEmpty()) {
            res[--k] = queue.remove();
        }
        return res;
    }

    public static void main(String[] args) {
        ArraysAlgorithm test = new ArraysAlgorithm();
//        int[] arr = {1,2,2,4,4,4,7,7,7,7,7,6,5,3,6};
//        int[] newnm = test.topKFrequent(arr,2);
//        Print.printArray(newnm);
        int[] arr = new int[]{1, 1, 3, 3, 4, 4, 5, 6, 7};
        test.removeDuplicates(arr);
        Print.printArray(arr);
    }
}
