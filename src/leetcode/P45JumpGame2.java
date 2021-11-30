package leetcode;

public class P45JumpGame2 {
    public int jump(int[] nums) {
        return func(nums, 0);
    }

    //----------------------------------------------------
    // final solution Greedy(각 칸에서 갈 수 있는 모든 칸을 검사할 필요가 없다)
    private int func(int[] nums, int now) {
        if(now >= nums.length-1) {
            return 0;
        }
        else if(now + nums[now] >= nums.length-1) {
            return 1;
        }

        int maxLen = 0;
        int maxIdx = 0;
        for(int i=1; i<=nums[now]; i++) {
            int nowMaxLen = nums[now + i] + i;
            if(nowMaxLen > maxLen) {
                maxLen = nowMaxLen;
                maxIdx = now + i;
            }
        }
        return func(nums, maxIdx) + 1;
    }

    //----------------------------------------------------
    // DP 풀이 && 앞에서부터 시작하면 불필요한 재귀호출이 있으므로 뒤에서부터 출발하자
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
