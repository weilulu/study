package list;

/**
 * 获取链表里的倒数第K个元素
 * 使用了两个快、慢指针，让快指针先走到指定位置；接着快、慢指针同时走，当快指针走到最后时，慢指针下一个节点就是指定节点
 */
public class GetKthFromEnd {

    /**
     * 获取倒数第K个元素
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head,int k){
        ListNode node = head;
        //快指针先走
        while(node != null && k > 0){
            node = node.getNext();
            k--;
        }
        System.out.println("K:"+k+",node value:"+node.getValue());
        ListNode listNode = head;
        //快、慢指针同时走，当快指针走到结尾时，慢指针下一个节点就是要找的节点
        while (node.getNext() != null){
            node = node.getNext();
            listNode = listNode.getNext();
            System.out.println("node:"+node.getValue()+",listNode:"+listNode.getValue());
        }
        return listNode.getNext();
    }

    /**
     * 删除倒数第K个元素，并返回头节点
     * @param head
     * @param k
     * @return
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/hua-jie-suan-fa-19-shan-chu-lian-biao-de-dao-shu-d/
     */
    public ListNode removeKthFromEnd(ListNode head,int k){
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode fast = pre;
        ListNode slow = pre;
        while(k>0){
            fast = fast.next;
            k--;
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        //slow.next就是需要删除的元素，下面一行代码通过操作指针即可进行删除
        slow.next = slow.next.next;
        return pre.next;
    }
    public static void main(String[] args) {
        GetKthFromEnd.ListNode head = new GetKthFromEnd.ListNode(1);
        GetKthFromEnd.ListNode node1 = new GetKthFromEnd.ListNode(2);
        GetKthFromEnd.ListNode node2 = new GetKthFromEnd.ListNode(3);
        GetKthFromEnd.ListNode node3 = new GetKthFromEnd.ListNode(4);
        GetKthFromEnd.ListNode node4 = new GetKthFromEnd.ListNode(5);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        GetKthFromEnd getKthFromEnd = new GetKthFromEnd();
        ListNode kthFromEnd = getKthFromEnd.getKthFromEnd(head, 2);//获取倒数第二个节点
        System.out.println(kthFromEnd.getValue());
    }
    static class ListNode{
        int value;
        ListNode next;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        ListNode(int x){
            this.value = x;
        }
    }
}
