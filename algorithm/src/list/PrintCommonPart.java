package list;

/**
 * 输出两个有序链表里相同的元素，比如：
 * list1:1,2,3,4
 * list2:3,4,5,6
 * 则输出元素3，4
 */
public class PrintCommonPart {

    public void printCommonPart(Node node1,Node node2){
        while (node1 != null && node2 != null){
            if(node1.value < node2.value){//链表1里的值小于链表2里的值，则后移链表1节点
                node1 = node1.next;
            }else if(node1.value > node2.value){//链表2里的值小于链表1里的值，则后移链表2节点
                node2 = node2.next;
            }else {//说明两链表里的值相等了，输出，然后都进行后移
                System.out.println(node1.value + " ");
                node1 = node1.next;
                node2 = node2.next;
            }
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        Node node4 = new Node(2);
        Node node5 = new Node(3);
        Node node6 = new Node(4);
        node4.next = node5;
        node5.next = node6;
        node6.next = null;

        PrintCommonPart commonPart = new PrintCommonPart();
        commonPart.printCommonPart(node1,node4);
    }

    static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }
}


