package list;

/**
 * 两种方式进行链表反转
 * 参考：https://blog.csdn.net/moakun/article/details/79996966
 */
public class ReverseList {

    public static void main(String[] args) {
        Node head = new ReverseList.Node(1);
        Node node1 = new ReverseList.Node(2);
        Node node2 = new ReverseList.Node(3);
        Node node3 = new ReverseList.Node(4);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        Node first = head;
        while(first != null){
            System.out.println(first.getValue());
            first = first.getNext();
        }
        System.out.println("-----------");
        head = reverseList(head);
        while(head != null){
            System.out.println(head.getValue());
            head = head.getNext();
        }
    }

    /**
     * 递归
     * @param head
     * @return
     * 思想：从后往前逆序反转指针域的指向
     */
    public static Node reverse(Node head){
        if(head == null || head.getNext() == null)return head;
        Node newHead = reverse(head.getNext());
        Node temp = head.getNext();
        temp.setNext(head);
        head.setNext(null);
        return newHead;
    }

    /**
     *
     * @param head
     * @return
     * pre:上一节点
     * cur:当前节点
     * tmp:临时节点，用于保存当前节点的下一节点
     * 思想：从前往后反转各个节点的指针域的指向
     * 过程：与交换两变量的值一样，先使用一个临时变量保存当前节点的下一节点，然后交换pre与cur节点
     */
    public static Node reverseList(Node head){
        if(head == null){return head;}
        Node pre = head;
        Node cur = head.getNext();
        Node tmp;
        while(cur != null){
            tmp = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = tmp;
        }
        head.setNext(null);
        return pre;
    }

    static class Node<E>{
        int value;
        Node next;
        Node(int x){
            value = x;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}

