package list;

/**
 * 合并两个有序链表
 * 0->2->3->null
 * 1->3->5->7->null
 * 合并后为：
 * 0->1->2->3->5->7->null
 *
 * 这个有点不太明白
 */
public class MergeList {
    public Node mergeList(Node head1,Node head2){
        if(head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }
        Node head = head1.value < head2.value ? head1 : head2;//取头节点小的
        Node cur1 = head == head1 ? head1 : head2;
        Node cur2 = head == head1 ? head2 : head1;
        Node pre = null;
        Node next = null;
        while (cur1 != null && cur2 != null){
            if(cur1.value <= cur2.value){
                pre = cur1;
                cur1 = cur1.next;
            }else{
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }

    static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value= value;
        }
    }
}
