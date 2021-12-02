package leetcode;

public class P1365 {

    // prefix sum O(n)
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] prefixSum = new int[101];
        for(int i=0; i<nums.length; i++) {
            prefixSum[nums[i]]++;
        }
        for(int i=1; i<prefixSum.length; i++) {
            prefixSum[i] += prefixSum[i-1];
        }
        int[] ret = new int[nums.length];

        for(int i=0; i<nums.length; i++) {
            int now = nums[i];
            if(now == 0) ret[i] = 0;
            else {
                ret[i] = prefixSum[nums[i]-1];
            }
        }
        return ret;
    }

    //-------------------------------------
    //sorting O(nlogn)
    /*static class Pair implements Comparable<Pair>{
        int index;
        int value;
        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
        public int compareTo(Pair o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        Pair[] pairs = new Pair[nums.length];
        int[] ret = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            pairs[i] = new Pair(i, nums[i]);
        }
        Arrays.sort(pairs);
        int count = 0;
        ret[0] = 0;
        for(int i=1; i<nums.length; i++) {
            if(pairs[i-1].value == pairs[i].value) count++;
            else count = 0;
            ret[pairs[i].index] = i - count;
        }
        return ret;
    }*/
}
