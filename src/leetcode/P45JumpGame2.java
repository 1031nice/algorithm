package leetcode;

public class P45JumpGame2 {
    public int jump(int[] nums) {
        int[] cache = new int[nums.length];
        func(nums, cache, nums.length-1);
        return cache[0];
    }

    private void func(int[] nums, int[] cache, int now) {
        int ret = 10_000;
        if(now >= nums.length-1) {
            cache[now] = 0;
        }
        else {
            for(int i=1; i<=nums[now]; i++) {
                if(now + i < cache.length) {
                    ret = Math.min(ret, cache[now + i]);
                } else {
                    break;
                }
            }
            cache[now] = ret + 1;
        }
        if(now != 0) {
            func(nums, cache, now-1);
        }
    }
}
