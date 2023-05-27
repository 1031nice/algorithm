package boj.silver;

import java.util.Arrays;
import java.util.Scanner;

public class P14501퇴사 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] matrix = new int[N][2];
        for (int i = 0; i < N; i++) {
            int t = scanner.nextInt();
            int p = scanner.nextInt();
            matrix[i][0] = t;
            matrix[i][1] = p;
        }

        int[] cache = new int[N];
        Arrays.fill(cache, -1);
        System.out.println(func(matrix, cache, 0));
    }

    private static int func(int[][] matrix, int[] cache, int now) {
        int ret = 0;
        if (now >= matrix.length) {
            return ret;
        } else if (cache[now] != -1) {
            return cache[now];
        }

        // 나를 골랐을 때와
        if (now + matrix[now][0] - 1 < matrix.length) {
            ret = matrix[now][1] + func(matrix, cache, now + matrix[now][0]);
        }

        // 나를 고르지 않았을 때
        for (int i = 1; i < matrix[now][0]; i++) {
            ret = Math.max(ret, func(matrix, cache, now + i));
        }

        cache[now] = ret;
        return ret;
    }

}