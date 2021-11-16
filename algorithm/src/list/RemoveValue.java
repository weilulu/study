package list;

import java.util.Stack;

/**
 * 删除链表中指定值的节点
 * 给定一个链表的头节点head和一个整数num，需要将值为num的节点全部删除
 * 比如：1,2,3,4,num=3，调整为链表为1,2,4
 */
public class RemoveValue {

    /**
     * 使用栈完成，空间O(n),时间O(n)
     * @param head
     * @return
     */
    public Node removeValue(Node head,int num){
        Stack<Node> stack = new Stack<>();
        while (head != null){
            if(head.value != num){
                stack.add(head);
            }
            head = head.next;
        }
        //上面执行完后head的值为null
        //下面是在构造新的链表，且是从尾部向头部进行构造。
        while (!stack.isEmpty()){/**很巧妙，这个循环体不是很好理解，这相当于是用栈在构造列表*/
            stack.peek().next = head;//第一次执行的时候是在构造尾节点
            head = stack.pop();
        }
        return head;
    }

    /**
     * 不借助外部工具，时间O(n),空间(1)
     * @param head
     * @param num
     * @return
     */
    public Node removeValue2(Node head,int num){
        while (head != null){
            if(head.value != num){
                break;
            }
            head = head.next;
        }
        Node pre = head;//定义一个pre节点，用来记录cur节点的前一节点
        Node cur = head;
        while (cur != null){
            if(cur.value == num){
                pre.next = cur.next;//调整指针，删除cur节点
                cur = cur.next;
            }else{
                pre = cur;//如果不相等，将cur赋值给pre,然后将cur后移
                cur = cur.next;
            }
        }
        return head;
    }
    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = null;
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        RemoveValue value = new RemoveValue();
        value.removeValue(head,3);
    }
    static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }
}
