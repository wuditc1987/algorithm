package algorithm.arrays;

import algorithm.utils.Print;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/24
 * @description TODO
 */
public class ArraysAlgorithm {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num,map.getOrDefault(num,0) + 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));

        for (Integer key : map.keySet()){
            if(queue.size() < k){
                queue.add(key);
            }else if(map.get(key) > map.get(queue.peek())){
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
        int[] arr = {1,2,2,4,4,4,7,7,7,7,7,6,5,3,6};
        ArraysAlgorithm test = new ArraysAlgorithm();
        int[] newnm = test.topKFrequent(arr,2);
        Print.printArray(newnm);
    }
}
