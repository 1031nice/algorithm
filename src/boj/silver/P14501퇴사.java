package boj.silver;

import java.util.Scanner;

/**
 * S3 완전탐색
 */

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

        System.out.println(func(matrix, new boolean[N], 0, 0));
    }

    private static int func(int[][] matrix, boolean[] visited, int now, int sum) {
        int ret = sum;
        if (now >= visited.length || visited[now]) {
            return ret;
        }

        for (int i = now; i < visited.length; i++) {
            if (!visited[i] && i + matrix[i][0] - 1 < visited.length) {
                for (int j = 0; j < matrix[i][0]; j++) {
                    visited[i + j] = true;
                }
                ret = Math.max(ret, func(matrix, visited, i + matrix[i][0], sum + matrix[i][1]));
                for (int j = 0; j < matrix[i][0]; j++) {
                    visited[i + j] = false;
                }
            }

        }

        return ret;
    }

}