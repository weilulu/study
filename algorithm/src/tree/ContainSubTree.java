package tree;

/**
 * 判断t1树是否包含t2树全部的拓扑结构，比如：
 *     1                      2
 *  2     3                 4   5
 *4   5 6   7
 *
 * 左为t1,右为t2，返回true
 *
 * 如果t1中某棵子树头节点的值与t2头节点的值一样，则从这两个头节点开始匹配，匹配的每一步都让t1上的节点跟着t2上的节点的先序遍历移动，
 * 每移动一步，都检查t1的当前节点是否与t2当前节点的值一样。如果匹配过程中发现有不匹配的情况，则直接返回false，需要再去寻找t1的下
 * 一棵子树。
 */
public class ContainSubTree {
    static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value = data;
        }
    }

    /**
     * @param t1
     * @param t2
     * @return
     */
    public static boolean contains(Node t1,Node t2){
        if(t2 == null){
            return true;
        }
        if(t1 == null){
            return false;
        }
        return check(t1,t2) || contains(t1.left,t2) || contains(t1.right,t2);
    }

    public static boolean check(Node h,Node t2){
        if(t2 == null){
            return true;
        }
        if(h == null || h.value != t2.value){//t1节点为null 或者t1与t2头节点不等。
            return false;
        }
        return check(h.left,t2.left) && check(h.right,t2.right);
    }
}
