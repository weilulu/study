package tree;

/**
 * 获取二叉树的最小深度
 * 思路：先将深度初始化为１，然后不断遍历当前节点的左、右节点，当一个节点
 * 的左、右节点都不存在时，那个时候的高度就是最小深度
 */
public class TreeDepth {
    public int minDepth(Node head){
        if(head == null)return 0;
        return process(head,1);
    }
    public int process(Node cur,int level){
        if(cur.left == null && cur.right == null){
            return level;
        }
        int high = Integer.MAX_VALUE;
        if(cur.left != null){
            high = Math.min(high,process(cur.left,level + 1));
        }
        if(cur.right != null){
            high = Math.min(high,process(cur.right,level + 1));
        }
        return high;
    }

    /**
     * 二叉树的最大深度
     * 最大深度是指树的根结点到最远叶子结点的最长路径上结点的数量。
     * @param node
     * @return
     */
    public int maxDepth(Node node){
        if(node==null){
            return 0;
        }
        return Math.max(maxDepth(node.left),maxDepth(node.right))+1;
    }

    class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }
}


