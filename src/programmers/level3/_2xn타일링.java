package programmers.level3;

public class _2xn타일링 {

    public int solution(int n) {
        int[] cache = new int[n];
        return func(cache, n, 0);
    }

    public int func(int[] cache, int n, int now) {
        if(now == n)
            return 1;
        else if(now > n)
            return 0;
        else if(cache[now] != 0)
            return cache[now];
        return cache[now] = (func(cache, n, now+1) + func(cache, n, now+2)) % 1000000007;
    }

}
