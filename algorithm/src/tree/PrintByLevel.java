package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层打印，且输出格式为：
 * level 1 : 1
 * level 2 : 2 3
 * level 3 : 4 5 6
 * level 4 : 7 8
 */
public class PrintByLevel {
    public void printByLevel(Node head){
        if(head == null)return;
        Queue<Node> queue = new LinkedList<>();
        int level = 1;
        Node  last = head;//last是正在打印的当前行的最右节点
        Node nLast = null;//nLast是下一行的最右节点
        queue.offer(head);
        System.out.print("level " + (level++) +" : ");
        while (!queue.isEmpty()){
            head = queue.poll();
            System.out.print( head.value + "");
            if(head.left != null){
                queue.offer(head.left);
                nLast = head.left;
            }
            if(head.right != null){
                queue.offer(head.right);
                nLast = head.right;
            }
            //这里是重点：如果遍历到最右节点说明要进行换行
            if(head == last && ! queue.isEmpty()){
                System.out.print("\nlevel "+(level++)+":");
                last = nLast;
            }
        }
        System.out.println();
    }
}

class Node{
    public int value;
    public Node left;
    public Node right;
    public Node(int value){
        this.value = value;
    }
}
