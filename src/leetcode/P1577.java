package leetcode;

import java.util.HashMap;

/*
1577. Number of Ways Where Square of Number Is Equal to Product of Two Numbers - medium
 */

public class P1577 {

    public static void main(String[] args) {
        System.out.println(new P1577().numTriplets(new int[]{1,1}, new int[]{1,1,1}));
    }

    public int numTriplets(int[] nums1, int[] nums2) {
        int ret = 0;

        HashMap<Long, Integer> mulToCount2 = getMulToCount(nums2);
        HashMap<Long, Integer> mulToCount1 = getMulToCount(nums1);

        long[] freqNums1 = new long[nums1.length];
        long[] freqNums2 = new long[nums2.length];

        for(int i=0; i<nums1.length; i++) {
            freqNums1[i] = (long) nums1[i] * nums1[i];
        }
        for(int i=0; i<nums2.length; i++) {
            freqNums2[i] = (long) nums2[i] * nums2[i];
        }

        ret += countFreqOfSquare(mulToCount2, freqNums1);
        ret += countFreqOfSquare(mulToCount1, freqNums2);

        return ret;
    }

    private int countFreqOfSquare(HashMap<Long, Integer> mulToCount2, long[] longNums1) {
        int ret = 0;
        for (long num : longNums1) {
            if (mulToCount2.containsKey(num))
                ret += mulToCount2.get(num);
        }
        return ret;
    }

    private HashMap<Long, Integer> getMulToCount(int[] nums2) {
        HashMap<Long, Integer> mulToCount = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            for (int j = i + 1; j < nums2.length; j++) {
                long now = (long) nums2[i] * nums2[j];
                mulToCount.put(now, mulToCount.containsKey(now) ? mulToCount.get(now) + 1 : 1);
            }
        }
        return mulToCount;
    }

}
