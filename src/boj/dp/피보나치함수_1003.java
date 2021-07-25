package boj.dp;

import java.util.*;

public class 피보나치함수_1003 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int tcs = scanner.nextInt();

        int[][] cache = new int[41][2];

        for(int t=0; t<tcs; t++){
            int n = scanner.nextInt();
            int[] fibo = fibo(cache, n);
            sb.append(fibo[0]).append(' ').append(fibo[1]).append('\n');
        }
        System.out.println(sb);
    }

    private static int[] fibo(int[][] cache, int n) {
        if((cache[n][0] + cache[n][1]) != 0)
            return cache[n];
        if(n == 0)
            return new int[]{1, 0};
        else if(n==1)
            return new int[]{0, 1};
        else {
            int[] fibo1 = fibo(cache, n - 1);
            int[] fibo2 = fibo(cache, n - 2);
            int[] ret = new int[]{fibo1[0] + fibo2[0], fibo1[1] + fibo2[1]};
            cache[n] = ret;
            return ret;
        }
    }

}