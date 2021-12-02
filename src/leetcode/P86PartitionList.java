package leetcode;

public class P86PartitionList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode partition(ListNode head, int x) {
        if(head == null) return null;
        ListNode left, right, lIter, rIter;
        left = right = lIter = rIter = null;
        while(head != null) {
            if(head.val < x) {
                 if(lIter == null) {
                     lIter = new ListNode(head.val);
                     left = lIter;
                 } else {
                     lIter.next = new ListNode(head.val);
                     lIter = lIter.next;
                 }
            } else {
                if(rIter == null) {
                    rIter = new ListNode(head.val);
                    right = rIter;
                } else {
                    rIter.next = new ListNode(head.val);
                    rIter = rIter.next;
                }
            }
            head = head.next;
        }
        if(lIter != null) {
            lIter.next = right;
            return left;
        }
        return right;
    }

}
