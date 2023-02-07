package leetcode.hard;

import java.util.Arrays;
import java.util.Objects;

public class P23MergeKSortedLists {
    public static void main(String[] args) {
        ListNode[] input = new ListNode[3];

        ListNode a3 = new ListNode(5);
        ListNode a2 = new ListNode(4, a3);
        ListNode a1 = new ListNode(1, a2);
        input[0] = a1;

        ListNode b3 = new ListNode(4);
        ListNode b2 = new ListNode(3, b3);
        ListNode b1 = new ListNode(1, b2);
        input[1] = b1;

        ListNode c2 = new ListNode(6);
        ListNode c1 = new ListNode(2, c2);
        input[2] = c1;

        P23MergeKSortedLists solution = new P23MergeKSortedLists();
        solution.mergeKLists(input);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode iter = null;
        ListNode ret = null;
        if (lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }

        long count = Arrays.stream(lists).filter(Objects::isNull).count();
        if (count == lists.length) { // 모든 리스트가 비어있다면 바로 종료
            return null;
        } else if (count == lists.length - 1) { // 하나만 남았다면 바로 반환
            return Arrays.stream(lists).filter(Objects::nonNull).findFirst().orElseThrow();
        }

        // 하나를 제외한 모두가 끝에 도달할 때까지 반복한다
        while (count < lists.length - 1) {
            // 최소 노드와 그때의 인덱스를 찾는다
            ListNode min = null;
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    continue;
                }

                if (min == null || min.val > lists[i].val) {
                    min = lists[i];
                    minIndex = i;
                }
            }

            // ret에 최소 노드를 연결한다
            if (iter == null) {
                iter = new ListNode(min.val);
                ret = iter;
            } else {
                iter.next = new ListNode(min.val);
                iter = iter.next;
            }

            // 최소 노드를 다음 칸으로 이동시키고
            lists[minIndex] = lists[minIndex].next;
            // 최소 노드를 가지고 있던 리스트가 끝에 도달하면 카운트를 증가시킨다
            if (lists[minIndex] == null) {
                count++;
            }
        }

        // 마지막 남은 리스트는 끝에 그대로 연결한다
        iter.next = Arrays.stream(lists).filter(Objects::nonNull).findFirst().orElseThrow();
        return ret;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
