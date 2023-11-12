package boj.gold;

import java.util.Scanner;

public class P12865평범한_배낭 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        // weight, value
        int[][] matrix = new int[N][2];

        for (int i=0; i<N; i++) {
            matrix[i][0] = scanner.nextInt();
            matrix[i][1] = scanner.nextInt();
        }

        int[][] cache = new int[100_001][N+1];
        System.out.println(func(matrix, cache, K, N - 1));
    }

    private static int func(int[][] matrix, int[][] cache, int initWeight, int end) {
        if (cache[initWeight][end] != 0) {
            return cache[initWeight][end];
        } else if (end == 0) {
            if (initWeight >= matrix[0][0]) {
                return matrix[0][1];
            } else {
                return 0;
            }
        }

        if (matrix[end][0] > initWeight) { // 지금 아이템을 선택할 수 없다
            cache[initWeight][end] = func(matrix, cache, initWeight, end-1);
            return cache[initWeight][end];
        } else { // 선택할 수 있다
            cache[initWeight][end] = Math.max(
                    func(matrix, cache, initWeight, end-1),
                    func(matrix, cache, initWeight - matrix[end][0], end-1) + matrix[end][1]
            );
            return cache[initWeight][end];
        }
    }
}

