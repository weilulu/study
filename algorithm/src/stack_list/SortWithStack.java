package stack_list;

import java.util.Stack;

/**
 * 要求：使用栈进行排序，可借助另一个栈,从栈顶到栈底从大到小排序
 * 如：
 * 3      5
 * 5 ===> 3
 * 2      2
 */
public class SortWithStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(5);
        stack.push(3);
        sortWithStack(stack);
        while (!stack.empty()){
            System.out.println(stack.pop());
        }
    }

    /**
     * peek 不改变栈的值(不删除栈顶的值)
     * pop  会把栈顶的值删除
     * push 方法插入stack的顶端
     * @param stack
     */
    public static void sortWithStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        while(!stack.empty()){
            int cur = stack.pop();
            //如果新栈栈顶的值小于旧栈顶的值
            while (!help.empty() && help.peek() < cur){
                stack.push(help.pop());
            }
            help.push(cur);//当外层while结束时，help里栈顶到栈底元素为2,3,5
        }
        while (!help.empty()){
            stack.push(help.pop());
        }
    }
}
