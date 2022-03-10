package list;

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
            list = list1.next;
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
