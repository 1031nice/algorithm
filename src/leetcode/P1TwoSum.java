package leetcode;

import java.util.HashMap;

public class P1TwoSum {

    // one-pass
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> valueToIndex = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            if(valueToIndex.containsKey(target - nums[i])) {
                return new int[]{valueToIndex.get(target - nums[i]), i};
            }
            valueToIndex.put(nums[i], i);
        }
        return null;
    }

    // two-pass
    /*
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> valueToIndex = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            valueToIndex.put(nums[i], i);
        }
        for(int i=0; i<nums.length; i++) {
            if(valueToIndex.containsKey(target - nums[i]) &&
                    valueToIndex.get(target - nums[i]) != i) {
                return new int[]{valueToIndex.get(target - nums[i]), i};
            }
        }
        return null;
    }
    */

    // O(NlogN)
     /*
     static class Pair implements Comparable<Pair> {
         int index;
         int value;
         public Pair(int index, int value) {
             this.index = index;
             this.value = value;
         }
         @Override
         public int compareTo(Pair other) {
             return Integer.compare(this.value, other.value);
         }
     }

     public int[] twoSum(int[] nums, int target) {
         Pair[] pairs = new Pair[nums.length];
         for(int i=0; i<nums.length; i++) {
             pairs[i] = new Pair(i, nums[i]);
         }
         Arrays.sort(pairs);
         int toEnd = 0;
         int toStart = pairs.length - 1;
         while(toEnd >= 0 && toStart < pairs.length) {
             int sum = pairs[toEnd].value + pairs[toStart].value;
             if(sum > target) {f
                 toStart--;
             }
             else if(sum < target){
                 toEnd++;
             }
             else {
                 return new int[]{pairs[toStart].index, pairs[toEnd].index};
             }
         }
         return null;
     }
     */
}