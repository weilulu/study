package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的遍历
 * https://blog.csdn.net/coder__666/article/details/80349039
 * https://www.cnblogs.com/zhi-leaf/p/10813048.html
 */
public class TraverseTree {

    public static void main(String[] args)
    {
        TreeNode[] node = new TreeNode[10];//以数组形式生成一棵完全二叉树
        for(int i = 0; i < 10; i++)
        {
            node[i] = new TreeNode(i);
        }
        for(int i = 0; i < 10; i++)
        {
            if(i*2+1 < 10)
                node[i].left = node[i*2+1];
            if(i*2+2 < 10)
                node[i].right = node[i*2+2];
        }

        preOrder(node[0]);
    }


    /**
     * 先序遍历
     * 先访问根节点，再遍历左子树，最后遍历右子树
     */
    public static void preOrder(TreeNode head){
        if(head != null){
            Stack<TreeNode> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()){
                head = stack.pop();//会把栈顶的值删除
                System.out.println(head.value);
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }
            }
        }
    }

    /**
     * 中序：先遍历左子树，然后访问根结点，最后遍历右子树
     * @param head
     */
    public void midOrder(TreeNode head){
        if(head != null){
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null){
                if(head != null){
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.println(head.value+" ");
                    head = head.right;
                }
            }
        }
    }

    /**
     * 首先遍历左子树，然后遍历右子树，最后访问根结点，
     * 在遍历左、右子树时，仍然先遍历左子树，然后遍历右子树，最后遍历根结点
     * @param node
     */
    public void endOrder(TreeNode node){
        if(node != null){
            Stack<TreeNode> stack = new Stack<>();
            stack.push(node);//node代表最近一次弹出并打印的节点
            TreeNode  c = null;//c代表栈顶节点
            while (!stack.isEmpty()){
                c = stack.peek();//不会把栈顶的值删除
                if(c.left != null && node != c.left && node != c.right){//说明c的左子树还没处理完
                    stack.push(c.left);
                }else if(c.right != null && node != c.right){//说明c的右子树还没处理完
                    stack.push(c.right);
                }else{//说明c的的左子树与右子树都已处理或c己无左子树与右子树，输出当前节点
                    System.out.println(stack.pop().value + " ");
                    node = c;
                }
            }
        }
    }

    /**
     * 使用两个栈进行后序遍历
     * @param head
     */
    public void endOrderByTwoStack(TreeNode head){
        Stack<TreeNode> s1= new Stack<>();
        Stack<TreeNode> s2= new Stack<>();
        s1.push(head);
        while (!s1.isEmpty()){
            TreeNode node = s1.pop();
            s2.push(node);
            if(node.left != null){
                s1.push(node.left);
            }
            if(node.right != null){
                s1.push(node.right);
            }
        }
        while (!s2.isEmpty()){
            System.out.println(s2.pop());
        }
    }

    /**
     * 层次遍历
     * @param head
     */
    public void levelOrder(TreeNode head){
        if(head != null){
            //使用了队列
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();//队列头部
                System.out.println(node.value+" ");
                if(node.left != null){
                    queue.add(node.left);//加在尾部
                }
                if(node.right != null){
                    queue.add(node.right);
                }

            }
        }
    }

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int value){
            this.value = value;
        }
    }
}

