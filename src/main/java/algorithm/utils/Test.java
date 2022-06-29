package algorithm.utils;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/9/4
 * @description TODO
 */
public class Test {

    public static void mergeSort(int[] arr, int low, int high){
        if (low >= high){
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid + 1, high);
    }

    private static void merge(int[] arr, int low, int mid1, int high){
        int mid = mid1 - 1;
        int[] temp = new int[high - low + 1];
        int i = low, j = mid1, k = 0;

        while (i <= mid && j <= high){
            if (arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            }else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid){
            temp[k++] = arr[i++];
        }
        while (j <= high){
            temp[k++] = arr[j++];
        }
        if (temp.length > 0)
            System.arraycopy(temp, 0 , arr, low, temp.length);
    }


    public static int[] quick(int[] arr, int low, int high){
        if (low >= high){
            return arr;
        }
        int index = partition(arr, low, high);
        quick(arr, low, index - 1);
        quick(arr, index + 1, high);
        return arr;
    }

    private static int partition(int[] arr, int low, int high){
        int pivot = arr[low];
        while (low < high){
            //从后半部分向前扫描
            while (arr[high] >= pivot && low < high){
                high --;
            }
            arr[low] = arr[high];
            //从前半部分向后扫描
            while (arr[low] <= pivot && low < high){
                low ++;
            }
            arr[high] = arr[low];
        }
        arr[high] = pivot;
        return high;
    }

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
        System.out.println(1 << 1);
        //  1011
    }
}
