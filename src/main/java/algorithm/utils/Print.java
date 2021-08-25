package algorithm.utils;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 前序遍历打印节点值
     * @param root
     */
    public static void printTreeNodePreorder(TreeNode root){
        if(root == null){
            System.out.println("root is null");
            return ;
        }
        List<Integer> list = new ArrayList<>();
        preorder(root,list);
        System.out.println(list);
    }

    /**
     * 中序遍历打印节点值
     * @param root
     */
    public static void printTreeNodeInorder(TreeNode root){
        if(root == null){
            System.out.println("root is null");
            return ;
        }
        List<Integer> list = new ArrayList<>();
        inorder(root,list);
        System.out.println(list);
    }

    /**
     * 后序遍历打印节点
     * @param root
     */
    public static void printTreeNodePostorder(TreeNode root){
        if(root == null){
            System.out.println("root is null");
            return ;
        }
        List<Integer> list = new ArrayList<>();
        postorder(root,list);
        System.out.println(list);
    }


    private static void preorder(TreeNode node,List<Integer> list){
        if(node != null){
            list.add(node.val);
            preorder(node.left,list);
            preorder(node.right,list);
        }
    }

    private static void inorder(TreeNode node,List<Integer> list){
        if(node != null){
            inorder(node.left,list);
            list.add(node.val);
            inorder(node.right,list);
        }
    }

    private static void postorder(TreeNode node,List<Integer> list){
        if(node != null){
            postorder(node.left,list);
            postorder(node.right,list);
            list.add(node.val);
        }
    }


}
