package algorithm.structure;

import java.util.Stack;

/**
 * @author wudi
 * @version 1.0.0
 * @date 2022/6/30
 * @description 使用Stack实现队列
 */
public class Stack2Queue {

    private Stack<Integer> in;
    private Stack<Integer> out;

    public Stack2Queue(){
        this.in = new Stack<>();
        this.out = new Stack<>();
    }

    public void push(int x){
        while (!this.out.empty()){
            in.push(out.pop());
        }
        in.push(x);
    }

    public int pop(){
        while (!this.in.empty()){
            out.push(in.pop());
        }
        return out.pop();
    }

    public int peek(){
        while (!this.in.empty()){
            out.push(in.pop());
        }
        return out.peek();
    }

    public boolean empty(){
        return in.empty() && out.empty();
    }

}
