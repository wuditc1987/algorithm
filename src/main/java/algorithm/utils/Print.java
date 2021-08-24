package algorithm.utils;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/8/22
 * @description TODO
 */
public class Print {

    public static void printNode(final ListNode node){
        ListNode tmp = node;
        while (tmp!= null){
            System.out.print(tmp.val);
            System.out.print(",");
            tmp = tmp.next;
        }
    }

    public static void printArray(int[] array){
        if(array == null || array.length == 0){
            System.out.println("array is empty!");
            return ;
        }
        for (int a : array){
            System.out.print(a);
            System.out.print(",");
        }
    }

}
