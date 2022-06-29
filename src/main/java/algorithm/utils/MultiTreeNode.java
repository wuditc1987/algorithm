package algorithm.utils;

import java.util.List;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2021/9/4
 * @description 多叉树
 */
public class MultiTreeNode {

    public int val;

    public List<MultiTreeNode> children;

    public MultiTreeNode(){

    }

    public MultiTreeNode(int val) {
        this.val = val;
    }

    public MultiTreeNode(int val, List<MultiTreeNode> children) {
        this.val = val;
        this.children = children;
    }
}
