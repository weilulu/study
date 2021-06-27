package stack_list;

import java.sql.Statement;
import java.util.Stack;

/**
 * 要求：逆序一个栈，只能使用递归
 * 3        1
 * 2 =====> 2
 * 1        3
 *
 * 使用两个递归，第一个getAndRemoveLastElement用来获取栈底元素，
 * 第二个reverse将栈底元素再压入栈
 */
public class ReverseStack {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverse(stack);
        while(!stack.empty()){
            System.out.println(stack.pop());
        }
    }

    /**
     * 取出栈中最底元素
     * @param stack
     * @return
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        //每次进入方法时先删除栈顶元素，直到返回栈元素
        int result = stack.pop();
        if(stack.empty()){
            return result;
        }else {
            int last = getAndRemoveLastElement(stack);
            System.out.println("last "+last);
            stack.push(result);
            System.out.println("push "+result);
            return last;
        }
    }

    /**
     * 每次取出最底元素后压入栈
     * @param stack
     */
    public static void reverse(Stack<Integer> stack){
        if(stack.empty()){
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }
}
