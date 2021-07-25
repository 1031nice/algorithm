package programmers.level1;

import java.util.HashSet;
import java.util.Set;

public class 폰켓몬 {
    public static void main(String[] args) {
        Solution폰켓몬 solution = new Solution폰켓몬();
        System.out.println(solution.solution(new int[]{3,3,3,2,2,4}));
    }
}
class Solution폰켓몬 {
    public int solution(int[] nums) {
        int length = nums.length;
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<length; i++) {
            set.add(nums[i]);
        }
        return Math.min(set.size(), length/2);
    }
}