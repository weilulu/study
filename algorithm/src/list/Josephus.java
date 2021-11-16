package list;

/**
 * 41个人排成一个圆圈，由第1个人开始报数，每报数到第3人该人就必须自杀，然后再由下一个重新报数，依次下去，
 * 直到最终只剩下一个人留下。
 * 输入：一个环形单向链表的头节点head和报数的值m
 * 输出：最后剩下的节点
 *
 * 思路：
 * 1，先定位尾节点
 * 2，同时移动头节点与尾节点，如果头节点移动到的下标正好是要找的m，则通过修改指针删除相应节点，再移动头节点。
 * 3，如果没找到，继续同时移动头节点与尾节点
 */
public class Josephus {

    public Node josephus(Node head,int m){
        Node last = head;
        //定位到最后一个节点
        while (last != head){
            last = last.next;
        }
        int count = 0;
        while (head != null){
            if(++count == m){//找到了，修改指针指向直接从链表里移除找到的节点
                last.next = head.next;//删除找到的节点
                head = last.next;//将head节点向前移一步。
                count = 0;
            }else{//没找到，先将last节点向前一步，再将head向前移一步
                last = last.next;
                head = last.next;
            }

        }
        return head;
    }

    static class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }
}
