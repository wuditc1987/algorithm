package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 排序说明   https://www.cnblogs.com/onepixel/articles/7674659.html
 *
 * @author wudi
 * @description
 * @date 2018年3月7日
 */
public class Sort {

    /**
     * 冒泡排序
     *
     * @param nums
     *
     * @return
     * @description 它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
     * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
     * 算法描述
     * 1.比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3.针对所有的元素重复以上的步骤，除了最后一个；
     * 4.重复步骤1~3，直到排序完成。
     * <p>
     * 算法分析 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     * @author wudi
     * @date 2018年3月7日
     */
    public static int[] bubble(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j , j + 1);
//                    int temp = nums[j];
//                    nums[j] = nums[j + 1];
//                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }

    /**
     * @param arr
     * @param n   尾边界位置
     *
     * @return
     * @description 冒泡排序（优化版）
     * @author wudi
     * @date 2018年3月7日
     */
    public static int[] bubble(int[] arr, int n) {
        int j, k = Math.min(n, arr.length);
        //发生了交换就为true, 没发生就为false，第一次判断时必须标志位true。
        boolean flag = true;
        while (flag) {
            //每次开始排序前，都设置flag为未排序过
            flag = false;
            for (j = 1; j < k; j++) {
                //前面的数字大于后面的数字就交换
                if (arr[j - 1] > arr[j]) {
                    //交换a[j-1]和a[j]
                    int temp;
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    //表示交换过数据;
                    flag = true;
                }
            }
            //减小一次排序的尾边界
            k--;
        }//end while
        return arr;
    }

    /**
     * @param arr
     * @param n   尾边界位置
     *
     * @return
     * @description 冒泡排序（优化进阶版）
     * @author wudi
     * @date 2018年3月7日
     */
    public static int[] bubble1(int[] arr, int n) {
        int j, k;
        //flag来记录最后交换的位置，也就是排序的尾边界
        int flag = Math.min(n, arr.length);

        //排序未结束标志
        while (flag > 0) {
            //k 来记录遍历的尾边界
            k = flag;
            flag = 0;

            for (j = 1; j < k; j++) {
                //前面的数字大于后面的数字就交换
                if (arr[j - 1] > arr[j]) {
                    //交换a[j-1]和a[j]
                    int temp;
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    //表示交换过数据,记录最新的尾边界.
                    flag = j;
                }
            }
        }//end while
        return arr;
    }

    /**
     * 选择排序
     *
     * @param nums
     *
     * @return
     * @description 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。
     * 唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
     * 选择排序(Selection-sort)是一种简单直观的排序算法。
     * 它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 以此类推，直到所有元素均排序完毕。
     * 算法描述 :  n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。
     * 1.初始状态：无序区为R[1..n]，有序区为空；
     * 2.第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
     * 该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     * 3.n-1趟结束，数组有序化了。
     * @author wudi
     * @date 2018年3月8日
     */
    public static int[] selection(int[] nums) {
        int len = nums.length;
        int minIndex, temp;
        for (int i = 0; i < len - 1; i++) {
            minIndex = i;
            //无序区遍历
            for (int j = i + 1; j < len; j++) {
                //寻找最小的数
                if (nums[j] < nums[minIndex]) {
                    //将最小数的索引保存
                    minIndex = j;
                }
            }
            temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
        return nums;
    }

    /**
     * 插入排序
     *
     * @param nums
     *
     * @return
     * @description 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
     * 算法描述
     * 1.从第一个元素开始，该元素可以认为已经被排序；
     * 2.取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 3.如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 5.将新元素插入到该位置后；
     * 6.重复步骤2~5。
     * <p>
     * 算法分析  最佳情况：T(n) = O(n) 最坏情况：T(n) = O(n2) 平均情况：T(n) = O(n2)
     * @author wudi
     * @date 2018年3月7日
     */
    public static int[] insertion(int[] nums) {
        int len = nums.length;
        int preIndex, current;
        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = nums[i];
            while (preIndex >= 0 && nums[preIndex] > current) {
                nums[preIndex + 1] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex + 1] = current;
            System.out.println(preIndex);
        }
        return nums;
    }


    public static int[] insertion2(int[] nums) {
        int j;
        int target;
        // 假定第一个元素被放到了正确的位置上
        // 这样，仅需遍历1 - n-1
        for (int i = 1; i < nums.length; i++) {
            j = i;
            target = nums[i];
            while (j > 0 && target < nums[j - 1]) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = target;
        }
        return nums;
    }


    /**
     * 希尔排序
     *
     * @param arr
     *
     * @return
     * @description 1959年Shell发明，第一个突破O(n^2)的排序算法，是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序
     * 算法描述 :先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序
     * 1.选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     * 2.按增量序列个数k，对序列进行k 趟排序；
     * 3.每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为1时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     * <p>
     * 算法分析 :最佳情况：T(n) = O(nlog2 n)  最坏情况：T(n) = O(nlog2 n)  平均情况：T(n) =O(nlog n)
     * @author wudi
     * @date 2018年3月8日
     */
    public static int[] shell(int[] arr) {
        int len = arr.length, temp, gap = 1;
        //动态定义间隔序列(步长)
        while (gap < len / 3) {
            gap = gap * 3 + 1;
        }
        for (; gap > 0; gap = (int) Math.floor(gap / 3)) {
            for (int i = gap; i < len; i++) {
                temp = arr[i];
                for (int j = i - gap; j > 0 && arr[j] > temp; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[i + gap] = temp;
            }
        }
        return arr;
    }

    /**
     * 归并排序
     *
     * @param arr
     *
     * @return
     * @description 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是O(n log n）的时间复杂度。代价是需要额外的内存空间。
     * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     * 归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并
     * 算法描述
     * 1.把长度为n的输入序列分成两个长度为n/2的子序列；
     * 2.对这两个子序列分别采用归并排序；
     * 3.将两个排序好的子序列合并成一个最终的排序序列。
     * <p>
     * 算法分析  最佳情况：T(n) = O(n)  最差情况：T(n) = O(nlogn)  平均情况：T(n) = O(nlogn)
     * @author wudi
     * @date 2018年3月8日
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        //分成两半
        int mid = left + (right - left) / 2;
        //左边排序
        mergeSort(arr, left, mid);
        //右边排序
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid + 1, right);
    }

    /**
     * @param arr 数组
     * @param leftPtr  指数组最左边
     * @param rightPtr  指数组中间
     * @param rightBound  数组最右边
     */
    static void merge(int[] arr, int leftPtr, int rightPtr, int rightBound) {
        int mid = rightPtr - 1;
        int[] temp = new int[rightBound - leftPtr + 1];

        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

        while (i <= mid && j <= rightBound) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 将右边剩余的归并
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        //将左边剩余的归并
        while (j <= rightBound) {
            temp[k++] = arr[j++];
        }

        if (temp.length > 0) System.arraycopy(temp, 0, arr, leftPtr, temp.length);
    }



    /**
     * 快速排序
     *
     * @param nums
     * @param lo
     * @param hi
     *
     * @return
     * @description 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序
     * 算法描述:快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
     * 1.从数列中挑出一个元素，称为 “基准”（pivot）；
     * 2.重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     * <p>
     * 算法分析 最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(nlogn)
     * <p>
     * 首先在数组中选择一个基准点（该基准点的选取可能影响快速排序的效率，后面讲解选取的方法），然后分别从数组的两端扫描数组，
     * 设两个指示标志（lo指向起始位置，hi指向末尾)，首先从后半部分开始，如果发现有元素比该基准点的值小，就交换lo和hi位置的值，
     * 然后从前半部分开始扫秒，发现有元素大于基准点的值，就交换lo和hi位置的值，如此往复循环，直到lo>=hi,然后把基准点的值放到hi这个位置。
     * 一次排序就完成了。以后采用递归的方式分别对前半部分和后半部分排序，当前半部分和后半部分均有序时该数组就自然有序了。
     * <p>
     * 取基准点的方法   https://www.cnblogs.com/coderising/p/5708801.html
     * @author wudi
     * @date 2018年3月7日
     */
    public static int[] quick(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return nums;
        }
        int index = partition(nums, lo, hi);
        quick(nums, lo, index - 1);
        quick(nums, index + 1, hi);
        return nums;
    }

    /**
     * @param arr
     * @param lo
     * @param hi
     *
     * @return
     * @description 分区操作
     * @author wudi
     * @date 2018年3月7日
     */
    public static int partition(int[] arr, int lo, int hi) {
        //固定的切分方式
        int key = arr[lo];
        while (lo < hi) {
            // 前后顺序不能变
            //1.从后半部分向前扫描
            while (arr[hi] >= key && hi > lo) {
                hi--;
            }
            arr[lo] = arr[hi];
            //2.从前半部分向后扫描
            while (arr[lo] <= key && hi > lo) {
                lo++;
            }
            arr[hi] = arr[lo];
        }
        arr[hi] = key;
        return hi;
    }

    /**
     * @param arr
     * @param i   前一个元素索引
     * @param j   后一个元素索引
     *
     * @description 交换元素
     * @author wudi
     * @date 2018年3月7日
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 创建堆，
     * @param arr 待排序列
     */
    private static void heapSort(int[] arr) {
        //创建堆
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            //将堆顶元素与末尾元素进行交换
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            //重新对堆进行调整
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 调整堆
     * @param arr 待排序列
     * @param parent 父节点
     * @param length 待排序列尾元素索引
     */
    private static void adjustHeap(int[] arr, int parent, int length) {
        //将temp作为父节点
        int temp = arr[parent];
        //左孩子
        int lChild = 2 * parent + 1;

        while (lChild < length) {
            //右孩子
            int rChild = lChild + 1;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && arr[lChild] < arr[rChild]) {
                lChild++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= arr[lChild]) {
                break;
            }

            // 把孩子结点的值赋给父结点
            arr[parent] = arr[lChild];

            //选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        arr[parent] = temp;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{4, 10, 5, 8, 22, 15, 77, 55};
        mergeSort(arr, 0 , arr.length - 1);
    	for(int num : arr){
    		System.out.println(num);
    	}
    }

}
