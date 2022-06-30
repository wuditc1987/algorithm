package algorithm;

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
     * 
     * @description 斐波那契数列
     * @author wudi
     * @date 2018年3月7日
     * @return
     */
    public static int fibonacciSequence(){
        
        return 0;
    }

    /**
     * x 的平方根
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
    

    public static void main(String[] args) {
        int x = 2147483647;
        System.out.println(mySqrt(x));
//        int[] nums = new int[]{5,8,14,20,31,55,78,81,93,97,111};
//        System.out.println(binarySearch(nums,111));
    }

}
