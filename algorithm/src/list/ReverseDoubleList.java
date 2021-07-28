package list;

/**
 * 反转双向链表
 */
public class ReverseDoubleList {

    public DoubleNode reverseList(DoubleNode head){
        DoubleNode prev = null;
        DoubleNode next = null;
        while (head != null){
            next = head.next;
            //设置当前节点的前后指针
            head.next = prev;
            head.prev = next;
            //节点后移
            prev = head;
            head = next;
        }
        return prev;
    }
}

class DoubleNode{
    public int value;
    public DoubleNode prev;//上一节点
    public DoubleNode next;//下一节点
    public DoubleNode(int data){
        this.value = data;
    }
}
