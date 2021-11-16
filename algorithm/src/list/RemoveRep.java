package list;

import java.util.HashSet;

/**
 * 删除无序链表里重复出现的节点
 * 如1->2->2->3
 * 删除后为1->2->3
 */
public class RemoveRep {
    /**
     * 使用HashSet实现，先将头节点加入，在加入后续节点时进行判断hashSet里是否己存在
     * @param head
     */
    public void removeRep(Node head){
        if(head == null){
            return;
        }
        HashSet<Integer> hashSet = new HashSet<>();
        Node pre = head;
        Node cur = head.next;
        hashSet.add(head.value);
        while(cur != null){
            if(hashSet.contains(cur.value)){//set里己经存在，通过修改指针删除节点，并后移
                pre.next = cur.next;
                cur = cur.next;
            }else{//set里不存在，加入set，并后移
                hashSet.add(cur.value);
                pre = cur;
                cur = cur.next;
            }
        }
    }

    static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }
}
