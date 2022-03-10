package list;

/**
 * 两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 输入：l1 = [2,4,3],l2=[5,6]
 * 输出：[4,0,7]
 * 解释：342+65 = 704
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 思路：
 * 1，将两个链表看作是相同长度进行遍历，较短链表前补0，如342+65=342+065
 * 2，计算出一位后需要考虑是否需要向上一位进位
 * 3，两个链表遍历完，进位为1则在新链表最前方添加节点1
 */
public class AddTwoNumbersWithList {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;//进位
        while(l1 != null || l2 != null){
            int x = l1 == null ? 0 : l1.value;
            int y = l2 == null ? 0 : l2.value;
            int sum = x + y + carry;//如果进位是1则需要加上进位
            carry = sum > 9 ? 1 : 0;
            sum = sum % 10;
            cur.next = new ListNode(sum);//构造新的节点
            cur = cur.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        if(carry ==1){//其中一个链表遍历完之后，如果进位是1，则需要添加一个新节点
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    static class ListNode{
        int value;
        ListNode next;

        ListNode(int x){
            this.value = x;
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


