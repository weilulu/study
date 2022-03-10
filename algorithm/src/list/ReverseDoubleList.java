package list;

/**
 * 反转双向链表
 */
public class ReverseDoubleList {

    /**
     * 就地反转，不会涉及节点的移动。每个节点的值不变，将每个节点前后指针进行调整，即之前指向前的调整为指向后；之前指向后的调整为指向前。
     * @param head
     * @return
     */
    public DoubleNode reverseList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null){
            next = head.next;//保存头节点的下一节点
            //调整指针方向
            head.next = pre;//->改为<-
            head.last = next;//<-改为->
            //上面两行调整了头节点的指针，下面是依次向后推移
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        DoubleNode head = new DoubleNode(1);
        DoubleNode doubleNode2 = new DoubleNode(2);
        DoubleNode doubleNode3 = new DoubleNode(3);
        DoubleNode doubleNode4 = new DoubleNode(4);
        head.last = null;
        head.next = doubleNode2;
        doubleNode2.last = head;
        doubleNode2.next = doubleNode3;
        doubleNode3.last = doubleNode2;
        doubleNode3.next = doubleNode4;
        doubleNode4.last = doubleNode3;
        doubleNode4.next = null;

        ReverseDoubleList reverseDoubleList = new ReverseDoubleList();
        DoubleNode doubleNode = reverseDoubleList.reverseList(head);
        while (doubleNode != null){
            System.out.println(doubleNode.value);
            doubleNode = doubleNode.next;
        }
    }
    static class DoubleNode{
        public int value;
        public DoubleNode last;//上一节点
        public DoubleNode next;//下一节点
        public DoubleNode(int value){
            this.value = value;
        }

    }
}


