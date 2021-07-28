package stack_list;

import java.util.Stack;

/**
 * 判断一个链表是否是一个回文结构
 * 思路：使用一个栈，先将整个链表元素入栈，然后出栈时与链表节点进行比较
 *
 * 另一种方式：只将链表的右半部分入栈，然后出栈时与链表左半部分节点进行比较
 * 这种方法的关键点是定位到链表的右半部分起点
 *
 * 是否还可以使用Set,然后判断set里的个数，这个不行，可能顺序不对
 */
public class Palindrome {
    public boolean isPalindrome(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null){
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }
}

class Node {
    public int value;
    public Node next;
    public Node(int value){
        this.value = value;
    }
}
