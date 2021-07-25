package programmers.level2;

import java.util.HashSet;

public class 소수찾기 {
    public static void main(String[] args) {
        new Solution().solution("17");
    }
}

class Solution {
    public int solution(String numbers) {
        int[] nums = new int[numbers.length()];
        for(int i=0; i<numbers.length(); i++)
            nums[i] = numbers.charAt(i) - '0';
        boolean[] visited = new boolean[nums.length];
        HashSet<Integer> set = new HashSet<>();
        return func(nums, visited, set, 1, 0);
    }

    public int func(int[] nums, boolean[] visited, HashSet<Integer> set, int count, int number) {
        int ret = 0;
        if(count > nums.length)
            return 0;
        for(int i=0; i<nums.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                int temp = number + nums[i]*(int)Math.pow(10, count-1);
                if(isPrime(temp) && !set.contains(temp)) {
                    ret++;
                    set.add(temp);
                }
                ret += func(nums, visited, set, count+1, temp);
                visited[i] = false;
            }
        }
        return ret;
    }

    public boolean isPrime(int number) {
        if(number <= 1)
            return false;
        else if(number == 2)
            return true;
        for(int i=2; i*i<=number; i++) {
            if(number % i == 0)
                return false;
        }
        return true;
    }
}