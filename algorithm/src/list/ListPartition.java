package list;

/**
 * 将单向链表按某值分成左边小、中间相等、右边小的形式
 * 如：9,0,4,5,1 pivot=3
 * 调整后：
 * 1,0,4,9,5或,0,1,9,5,4
 * １，先遍历一遍链表，得到链表长度n
 * ２，生成长度为n的数组，再遍历一遍链表，将节点依次放进数组中
 * ３，交换数组中的位置
 * ４，将数组中的节点重新边起来
 */
public class ListPartition {
    public Node listPartition(Node head,int pivot){
        if(head == null)return head;
        Node cur = head;
        int i = 0;
        while (cur!= null){
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i=1;i != nodeArr.length;i++){
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr,pivot);
        for(i =1;i != nodeArr.length;i++){
            nodeArr[i -1].next = nodeArr[i];
        }
        nodeArr[i-1].next = null;
        return nodeArr[0];
    }

    public void arrPartition(Node[] nodeArr,int pivot){
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big){
            //注意理解下面的交换
            if(nodeArr[index].value < pivot){
                swap(nodeArr,++small,index++);
            }else if(nodeArr[index].value == pivot){
                index ++;
            }else {
                swap (nodeArr,--big,index);
            }
        }
    }
    public void swap(Node[] nodeArr,int a,int b){
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }
}


class Node{
    public int value;
    public Node next;
    public Node(int value){
        this.value = value;
    }
}
