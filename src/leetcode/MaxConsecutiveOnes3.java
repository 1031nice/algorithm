package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MaxConsecutiveOnes3 {

    // ------------------------------------------------------------
    // final solution
    public int longestOnes(int[] nums, int k) {
        int start = 0;
        int end = 0;
        int maxLen = -1;

        while(end < nums.length) {
            // move end to right
            if(nums[end] == 1) {
                end++;
            }
            else if(k > 0) {
                end++;
                k--;
            }

            // move start to right(cause it can't expands anymore)
            else {
                int len = end - start;
                maxLen = Math.max(len, maxLen);

                int index = findFirstZero(nums, start, end);
                // give up first zero
                if(index >= 0) {
                    start = index + 1;
                    end++;
                }
                // there is no zero to give up
                // so give up the entire sequence
                // and restart from one
                else {
                    start = end = findFirstOne(nums, end);
                    if(start == -1) break; // if no one exists, no need to keep going
                }
            }
        }

        maxLen = Math.max(end - start, maxLen);

        return maxLen;
    }

    private int findFirstZero(int[] nums, int from, int to) {
        for(int i=from; i<to; i++) {
            if(nums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private int findFirstOne(int[] nums, int from) {
        for(int i=from; i<nums.length; i++) {
            if(nums[i] == 1) {
                return i;
            }
        }
        return -1;
    }

    // ------------------------------------------------------------
    // fail solution: 접근 방법은 맞았으나 너무 어렵게 구현하려고 시도
    /*public int longestOnes(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        int before = nums[0];
        int count = 1;
        for(int i=1; i<nums.length; i++) {
            int now = nums[i];
            if(now != before) {
                list.add(count);
                count = 1;
                before = now;
            } else {
                count++;
            }
        }
        list.add(count); // last element

        int[] cumSum = new int[list.size()]; // 누적합
        cumSum[0] = list.get(0);
        for(int i=1; i<list.size(); i++) {
            cumSum[i] = cumSum[i-1] + list.get(i);
        }

        Queue<Integer> q = new LinkedList<>();
        int zeroIndex = nums[0] == 0 ? 0 : 1;
        int maxLen = 0;
        for(int i=zeroIndex^1; i<list.size(); i+=2) {
            int now = list.get(i);
            if(now > maxLen) maxLen = now;
        }
        maxLen = Math.min(nums.length, maxLen + k);

        for(int i=zeroIndex; i<list.size(); i+=2) {
            int nZero = list.get(i);
            if(k >= nZero) {
                k -= nZero;
                q.add(i);
            } else if (!q.isEmpty()){
                int len = cumSum[i-1];
                if(q.peek() > 0) len += list.get(q.peek()-1);
                if(q.peek() > 1) len -= cumSum[q.peek()-1];
                len += k;
                if(len > maxLen) maxLen = len;

                while(k < nZero && !q.isEmpty()) {
                    int frontIndex = q.poll();
                    k += list.get(frontIndex);
                }

                if(k >= nZero) {
                    k -= nZero;
                    q.add(i);
                }
            }
        }
        int len = 0;
        if(!q.isEmpty()) {
            len = cumSum[cumSum.length - 1];
            if(q.peek() > 0) len -= cumSum[q.peek()-1];
            if(q.peek() > 0) len += list.get(q.peek()-1);
        }
        if(len > maxLen) maxLen = len;

        return maxLen;
    }*/
}
