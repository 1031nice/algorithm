package leetcode;
public class P2AddTwoNumbers {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //--------------------------------
    //재귀
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

    //------------------------------
    //반복문
    /*public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;

        ListNode before = null;
        ListNode head = null;
        ListNode now = null;
        while(l1 != null || l2 != null) {
            int sum = carry;
            sum += l1 == null ? 0 : l1.val;
            sum += l2 == null ? 0 : l2.val;
            carry = sum >= 10 ? 1 : 0; //올림수
            sum %= 10;
            now = new ListNode(sum);
            if(before == null) {
                head = now;
            } else {
                before.next = now;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            before = now;
        }
        if(carry != 0) {
            before.next = new ListNode(carry);
        }
        return head;
    }*/
}