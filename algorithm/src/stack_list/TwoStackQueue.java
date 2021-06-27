package stack_list;

import java.util.Stack;

/**
 * 要求：用两个栈实现一个队列，支持队列的基本操作，add/poll/peek
 * 实现：一个栈作为压入栈，在压入时数据只往这个栈中压入，记为pushStack;另一个栈只作为弹出栈，
 * 　　　在弹出数据时只从这个栈弹出，记作popStack
 * 有两点需要注意：
 * １，如果pushStack要往popStack中压入栈，那么必须一次性把pushStack里的值全压入popStack
 * ２，如果popStack不为空，pushStack就不能向popStack压入数据
 */
public class TwoStackQueue {

    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;
    public TwoStackQueue(){
        this.pushStack = new Stack<>();
        this.popStack = new Stack<>();
    }

    private void pushToPop(){
        if(popStack.empty()){
            while (!pushStack.empty()){
                popStack.push(popStack.pop());
            }
        }
    }

    private void add(int pushInt){
        pushStack.push(pushInt);
        pushToPop();
    }

    private int pull(){
        if(pushStack.empty() && popStack.empty()){
            throw new RuntimeException("queue is empty");
        }
        pushToPop();
        return popStack.pop();
    }

    private int peek(){
        if(pushStack.empty() && popStack.empty()){
            throw new RuntimeException("queue is empty");
        }
        pushToPop();
        return popStack.peek();
    }
}
