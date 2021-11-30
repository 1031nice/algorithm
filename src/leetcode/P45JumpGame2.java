package leetcode;

public class P45JumpGame2 {
    public int jump(int[] nums) {
        int[] cache = new int[nums.length];
        return func(nums, cache, 0);
    }

    //----------------------------------------------------
    // final solution: 각 칸에서 갈 수 있는 모든 칸을 검사할 필요가 없다
    private int func(int[] nums, int[] cache, int now) {
        if(now >= nums.length-1) {
            return cache[now] = 0;
        }

        int maxIndex = 0;
        int maxLen = 0;
        for(int i=1; i<=nums[now]; i++) {
            if(now + i >= nums.length-1) {
                return cache[now] = 1;
            }
            int nextMaxLen = i + nums[now + i];
            if(nextMaxLen > maxLen) {
                maxLen = nextMaxLen;
                maxIndex = now + i;
            }
        }
        return cache[now] = func(nums, cache, maxIndex) + 1;
    }

    //----------------------------------------------------
    // 개선 v1: 앞에서부터 시작하면 불필요한 재귀호출이 있으므로 뒤에서부터 출발하자
    /*public int jump(int[] nums) {
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
    }*/
}
