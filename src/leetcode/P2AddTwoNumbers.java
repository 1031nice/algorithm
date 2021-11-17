package leetcode;
public class P2AddTwoNumbers {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    private ListNode add(ListNode l1, ListNode l2, int carry) {
        int sum = carry;
        if(l1 == null && l2 == null) {
            return carry == 0 ? null : new ListNode(carry);
        }
        sum += (l1 == null) ? 0 : l1.val;
        sum += (l2 == null) ? 0 : l2.val;
        ListNode newNode = new ListNode(sum % 10);
        newNode.next = add(l1 == null ? null : l1.next, l2 == null ? null : l2.next, sum / 10);
        return newNode;
    }
}