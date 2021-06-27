package stack_list;

import java.util.Stack;

/**
 * 要求：
 * 在实现现有栈的基础上，再实现一个返回栈中最小元素的操作
 * 使用两个栈，stackData作为一个普通的栈，stackMin作为只保存相当较小元素的栈
 * 入栈时：如果stackMin为空，直接将新值push stackMin
 *        如果新值小于stackMin里栈顶元素，也将新值push stackMin
 *        最后将新值也push stackData
 * 出栈时：
 * 　　　　先将stackData出栈；
 * 　　　　判断stackData出栈的元素是否等于stackMin栈顶元素，如果相等也将stackMin出栈
 */
public class MyStack1 {

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack1(){
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum){
        if(this.stackMin.empty()){
            stackMin.push(newNum);
        }else if (newNum <= getMin()){
            stackMin.push(newNum);
        }
        stackData.push(newNum);
    }
    public int pop(){
        if(stackData.isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        int value = stackData.pop();
        if(value == getMin()){
            stackMin.pop();
        }
        return value;
    }
    public int getMin(){
        if(stackMin.isEmpty()){
            throw new RuntimeException("stack is empty");
        }
        return stackMin.peek();
    }
}
