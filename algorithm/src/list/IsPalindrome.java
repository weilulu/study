package list;


import java.util.Stack;

/**
 * 判断一个链表是否为回文结构，如：
 * 1->2->1 true
 * 1->2->2->1 true
 * 17->4->17 true
 * 1->2->3 false
 */
public class IsPalindrome {
    /**
     * 使用一个栈完成，当链表所有元素都入栈后，
     * 从栈顶到栈底元素的顺序与链表从左到右的顺序正好相反，
     * 如果链表为回文结构，逆序之后，元素出现的次数还是一样
     * @param head
     * @return
     */
    public boolean isPalindrome(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null){
            //如果是回文才会相等
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        head.next = node1;
        node1.next = node2;
        node2.next = null;
        IsPalindrome isPalindrome = new IsPalindrome();
        boolean palindrome = isPalindrome.isPalindrome(head);
        System.out.println(palindrome);
    }

    /**
     * 将链表分为左右两个区间，右区间入栈，以栈为基础进行跌代，同时比较出栈元素与链表元素
     * @param head
     * @return
     */
    public boolean test(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node right = head.next;
        Node cur = head;
        //定位到右半区间的第一个节点，如1,2,3,2,1,则right为第4个节点也就是2
        while (cur.next != null && cur.next.next != null){
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (right != null){
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()){
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }
}
