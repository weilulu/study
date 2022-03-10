package list;

/**
 * 判断链表是否有环
 * 使用快慢指针，快指针一次走两步，慢指针一次走一步,如果两者相遇了说明有环
 * 也可以使用一个Set来判断是否有重复节点
 */
public class HasCycle {
    public boolean hasCycle(ListNode head){
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 找出环的位置
     * 使用快慢指针，相遇时暂停，然后慢指针归零，接着两个指针再次后移至到相遇，相遇点就是环入口
     * https://blog.csdn.net/weixin_43729854/article/details/104246017
     * 1、设置快慢指针，假如有环，他们最后一定相遇。
     *
     * 2、两个指针分别从链表头和相遇点继续出发，每次走一步，最后一定相遇在环入口。
     * @param pHead
     * @return
     */
    public ListNode find(ListNode pHead){
        ListNode fast = pHead;
        ListNode slow = pHead;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        slow = pHead;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
    static class ListNode{
        public int value;
        public ListNode next;
        public ListNode(int data){
            this.value = data;
        }
    }
}
