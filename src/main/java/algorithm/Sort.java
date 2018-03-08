package algorithm;


/**
 * 排序说明   https://www.cnblogs.com/onepixel/articles/7674659.html
 * @description 
 * @author wudi
 * @date 2018年3月7日
 */
public class Sort {
    
    /**
     * 
     * @description 冒泡排序
     * @author wudi
     * @date 2018年3月7日
     * @param nums
     * @return
     */
    public static int[] bubble(int[] nums){
        for(int i = 1;i<nums.length;i++){
            for(int j = 0; j< nums.length - i; j++){
                if(nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        System.out.println(nums);
        return nums;
    }
    
    /**
     * 
     * @description 冒泡排序（优化版）
     * @author wudi
     * @date 2018年3月7日
     * @param arr
     * @param n 尾边界位置
     * @return
     */
    public static int[] bubble(int[] arr,int n){
        int j, k = n > arr.length ? arr.length : n;
        boolean flag = true;//发生了交换就为true, 没发生就为false，第一次判断时必须标志位true。
        while (flag){
            flag=false;//每次开始排序前，都设置flag为未排序过
            for(j=1; j<k; j++){
                if(arr[j-1] > arr[j]){//前面的数字大于后面的数字就交换
                    //交换a[j-1]和a[j]
                    int temp;
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j]=temp;

                    //表示交换过数据;
                    flag = true;
                }
            }
            k--;//减小一次排序的尾边界
        }//end while
        
        return arr;
    }
    
    /**
     * 
     * @description 冒泡排序（优化进阶版）
     * @author wudi
     * @date 2018年3月7日
     * @param arr
     * @param n 尾边界位置
     * @return
     */
    public static int[] bubble1(int[] arr,int n){
        int j , k;
        int flag = n > arr.length ? arr.length : n;//flag来记录最后交换的位置，也就是排序的尾边界

        while (flag > 0){//排序未结束标志
            k = flag; //k 来记录遍历的尾边界
            flag = 0;

            for(j=1; j<k; j++){
                if(arr[j-1] > arr[j]){//前面的数字大于后面的数字就交换
                    //交换a[j-1]和a[j]
                    int temp;
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j]=temp;
                    //表示交换过数据;
                    flag = j;//记录最新的尾边界.
                }
            }
        }
        return arr;
    }
    
    /**
     * 
     * @description 快速排序
     * @author wudi
     * @date 2018年3月7日
     * @param nums
     * @return
     */
    public int[] quick(int[] nums){
        
        return nums;
    }
    
    /**
     * 
     * @description 插入排序
     * @author wudi
     * @date 2018年3月7日
     * @param nums
     * @return
     */
    public int[] insert(int[] nums){
        int len = nums.length;
        int preIndex,current;
        for(int i = 1; i < len; i++){
            preIndex = i-1;
            current = nums[i];
            while (preIndex >= 0 && nums[preIndex] > current) {
                nums[preIndex] = nums[preIndex];
                preIndex --;
            }
            nums[preIndex - 1] = current;
        }
        return nums;
    }
    
    /**
     * 
     * @description 堆排序
     * @author wudi
     * @date 2018年3月7日
     * @param nums
     * @return
     */
    public int[] heap(int[] nums){
        
        return nums;
    }
    
    

    public static void main(String[] args) {

    }

}
