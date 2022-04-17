package list;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists){
        if(lists == null){
            return null;
        }
        return merge(lists,0,lists.length-1);
    }
    public ListNode merge(ListNode[] lists,int low,int high){
        if(low == high){
            return lists[low];
        }
        int mid = low + (high-low)/2;
        ListNode l1 = merge(lists,low,mid);
        ListNode l2 = merge(lists,mid+1,high);
        return merge2list(l1,l2);
    }

    public ListNode merge2list(ListNode list1,ListNode list2){
        ListNode list = new ListNode(-1);
        ListNode pre = list;
        while (list1 != null && list2 != null){
            if(list1.value < list2.value){
                list.next = list1;
                list1 = list1.next;
            }else{
                list.next = list2;
                list2 = list2.next;
            }
            list = list.next;
        }
        if(list1 != null)list.next = list1;
        if(list2 != null)list.next = list2;
        return pre.next;
    }
    class ListNode{
        int value;
        ListNode next;
        public ListNode(int value){
            this.value = value;
        }

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
    }
}
