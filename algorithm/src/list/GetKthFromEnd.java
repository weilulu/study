package list;

public class GetKthFromEnd {

    public ListNode getKthFromEnd(ListNode head,int k){
        ListNode node = head;
        while(node != null && k > 0){
            node = node.getNext();
            k--;
        }
        System.out.println("K:"+k+",node value:"+node.getValue());
        ListNode listNode = head;
        while (node.getNext() != null){
            node = node.getNext();
            listNode = listNode.getNext();
            System.out.println("node:"+node.getValue()+",listNode:"+listNode.getValue());
        }
        return listNode.getNext();
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
