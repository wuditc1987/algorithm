package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @description 查找算法
 * @author wudi
 * @date 2018年3月7日
 */
public class Search {

    /**
     *
     * @description 二分查找
     * @author wudi
     * @date 2018年3月7日
     * @param array 待查找的数组(有序数组)
     * @param value 需要查找的数字
     * @return 返回检索次数/返回数据在数组中的位置
     */
    public static int binarySearch(int[] array, int value){
        int low = 0;
        int high = array.length - 1;
        int count = 1;
        while(low <= high) {
            int middle = low + (high - low) / 2;
            //int middle = (high + low)/2 此处在极端情况下会出现溢出
            //找到则直接返回
            if(value == array[middle]){
                return count;
            }
            //若数据大于数组中的数，则从middle后一位开始计算，重新查找
            if(value > array[middle]){
                low = middle + 1;
            }
            if(value < array[middle]){
                high = middle - 1;
            }
            count ++;
        }
        return count;
    }

    /**
     * 69. x 的平方根
     * https://leetcode.cn/problems/sqrtx/
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        long left = 0, right = x / 2 + 1;
        while(left < right){
            long mid = (left + right + 1) >>> 1;
            long square = mid * mid;
            if(square > x){
                right = mid - 1;
            }else{
                left = mid;
            }
        }
        return (int)left;
    }

    /**
     * 658. 找到 K 个最接近的元素
     * https://leetcode.cn/problems/find-k-closest-elements/
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int right = findClosestElementsHelper(arr, x);
        int left = right - 1;
        List<Integer> list = new ArrayList<>();
        while (k -- > 0){
            // 如果左侧索引溢出，则右侧索引向右移动
            if (left < 0){
                right ++;
            } else if (right >= arr.length){
                left --;
            } else if (x - arr[left] <= arr[right] - x){
                left --;
            }else {
                right ++;
            }
        }
        for (int i = left + 1; i < right; i++){
            list.add(arr[i]);
        }
        return list;
    }
    // 二分法找到数组的中间位置
    private int findClosestElementsHelper(int[] arr, int x){
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int x = 2147483647;
        System.out.println(mySqrt(x));
//        int[] nums = new int[]{5,8,14,20,31,55,78,81,93,97,111};
//        System.out.println(binarySearch(nums,111));
    }

}
