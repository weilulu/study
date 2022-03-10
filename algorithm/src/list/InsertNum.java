package list;

/**
 * 一个环形单链表从头节点head开始不降序(感觉就是升序)，同时由最后的节点指回头节点。
 * 给定这个环形链表的头节点，与一个整数num，请生成节点值为num的新节点，插入到这个环形链表中，保证依然有序，最后返回这个链表的头节点
 */
public class InsertNum {

    /**
     * 分2种情况：
     * 1，num的值小于链表里的所有值或者大于链表里的所有值，则num构造的节点需要插入到之前头节点前面
     * 2，num的值介于链表节点里的值
     * @param head
     * @param num
     * @return
     */
    public Node insertNum(Node head,int num){
        Node node = new Node(num);
        if(head == null){
            node.next = node;
            return node;
        }
        Node pre = head;
        Node cur = head.next;
        /**
         * 如果num的值小于或大于链表里所有节点的值，则循环结束后循环里面的if判断都不会成功，
         * 此时，cur为头节点，pre为尾节点；比如2，3，4，5链表，需要插入1，过程如下：
         * cur与pre都会移动，直到cur为head时break,这个时候就是cur为头节点，pre为尾节点
         * 如果num的值介于链表里的节点对应的值，则循环里的if判断会成立，
         * 此时，cur为尾节点，pre为尾节点的上一节点。
         */
        while (cur != head){
            if(pre.value <= num && cur.value >= num){//这是在定位num节点应该插入的位置，num的值介于链表所有节点值之间,这里不会成立
                break;
            }
            pre = cur;//保存上一节点
            cur = cur.next;
        }
        pre.next = node;//当前节点的上一节点
        node.next = cur;//当前节点的下一节点
        return head.value < num ? head : node;
    }
    static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }
}
