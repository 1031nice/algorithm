package boj.gold;

import java.util.Scanner;

public class P121002048 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println(Math.max(getMax(matrix), func(matrix, 0)));
    }

    private static int func(int[][] matrix, int cnt) {
        int ret = 0;
        if (cnt == 5) {
            return ret;
        }

        for (int dir = 0; dir < 4; dir++) {
            int[][] copiedMatrix = copy(matrix);
            calc(copiedMatrix, dir);
            ret = Math.max(ret, getMax(copiedMatrix));
            ret = Math.max(ret, func(copiedMatrix, cnt + 1));
        }

        return ret;
    }

    private static void calc(int[][] matrix, int dir) {
        if (dir == 0) { // UP
            for (int col = 0; col < matrix[0].length; col++) {
                int index = 0;

                // 0이 아닌걸 위로 쭉 당긴다
                for (int row = 0; row < matrix.length; row++) {
                    if (matrix[row][col] != 0) {
                        matrix[index++][col] = matrix[row][col];
                    }
                }

                // 마지막에 쓴 위치 다음부터 모두 0으로 지운다
                for (int row = index; row < matrix.length; row++) {
                    matrix[row][col] = 0;
                }

                // 공백이 없음이 보장된 상태
                for (int row = 0; row < matrix.length - 1; row++) {
                    if (matrix[row][col] == matrix[row + 1][col]) { // 같은 수가 연속하면
                        matrix[row][col] *= 2;
                        matrix[row + 1][col] = 0;
                        row++;
                    }
                }

                // 0이 아닌걸 위로 쭉 당긴다
                index = 0;
                for (int row = 0; row < matrix.length; row++) {
                    if (matrix[row][col] != 0) {
                        matrix[index++][col] = matrix[row][col];
                    }
                }

                // 마지막에 쓴 위치 다음부터 모두 0으로 지운다
                for (int row = index; row < matrix.length; row++) {
                    matrix[row][col] = 0;
                }
            }
        } else if (dir == 1) { // RIGHT
            for (int row = 0; row < matrix.length; row++) {
                int index = matrix[row].length - 1;

                // 0이 아닌걸 오른쪽으로 쭉 당긴다
                for (int col = matrix[row].length - 1; col >= 0; col--) {
                    if (matrix[row][col] != 0) {
                        matrix[row][index--] = matrix[row][col];
                    }
                }

                // 마지막에 쓴 위치 다음부터 모두 0으로 지운다
                for (int col = index; col >= 0; col--) {
                    matrix[row][col] = 0;
                }

                // 공백이 없음이 보장된 상태
                for (int col = matrix[row].length - 1; col >= 1; col--) {
                    if (matrix[row][col] == matrix[row][col - 1]) { // 같은 수가 연속하면
                        matrix[row][col] *= 2;
                        matrix[row][col - 1] = 0;
                        col--;
                    }
                }

                // 0이 아닌걸 오른쪽으로 쭉 당긴다
                index = matrix[row].length - 1;
                for (int col = matrix[row].length - 1; col >= 0; col--) {
                    if (matrix[row][col] != 0) {
                        matrix[row][index--] = matrix[row][col];
                    }
                }

                // 마지막에 쓴 위치 다음부터 모두 0으로 지운다
                for (int col = index; col >= 0; col--) {
                    matrix[row][col] = 0;
                }
            }
        } else if (dir == 2) { // DOWN
            for (int col = 0; col < matrix[0].length; col++) {
                int index = matrix.length - 1;

                // 0이 아닌걸 아래로로 쭉 당긴다
                for (int row = matrix.length - 1; row >= 0; row--) {
                    if (matrix[row][col] != 0) {
                        matrix[index--][col] = matrix[row][col];
                    }
                }

                // 마지막에 쓴 위치 다음부터 모두 0으로 지운다
                for (int row = index; row >= 0; row--) {
                    matrix[row][col] = 0;
                }

                // 공백이 없음이 보장된 상태
                for (int row = matrix.length - 1; row >= 1; row--) {
                    if (matrix[row][col] == matrix[row - 1][col]) { // 같은 수가 연속하면
                        matrix[row][col] *= 2;
                        matrix[row - 1][col] = 0;
                        row--;
                    }
                }

                // 0이 아닌걸 아래로 쭉 당긴다
                index = matrix.length - 1;
                for (int row = matrix.length - 1; row >= 0; row--) {
                    if (matrix[row][col] != 0) {
                        matrix[index--][col] = matrix[row][col];
                    }
                }

                // 마지막에 쓴 위치 다음부터 모두 0으로 지운다
                for (int row = index; row >= 0; row--) {
                    matrix[row][col] = 0;
                }
            }
        } else { // LEFT
            for (int row = 0; row < matrix.length; row++) {
                int index = 0;

                // 0이 아닌걸 왼쪽으로 쭉 당긴다
                for (int col = 0; col < matrix[row].length; col++) {
                    if (matrix[row][col] != 0) {
                        matrix[row][index++] = matrix[row][col];
                    }
                }

                // 마지막에 쓴 위치 다음부터 모두 0으로 지운다
                for (int col = index; col < matrix[row].length; col++) {
                    matrix[row][col] = 0;
                }

                // 공백이 없음이 보장된 상태
                for (int col = 0; col < matrix[row].length - 1; col++) {
                    if (matrix[row][col] == matrix[row][col + 1]) { // 같은 수가 연속하면
                        matrix[row][col] *= 2;
                        matrix[row][col + 1] = 0;
                        col++;
                    }
                }

                // 0이 아닌걸 왼쪽으로 쭉 당긴다
                index = 0;
                for (int col = 0; col < matrix[row].length; col++) {
                    if (matrix[row][col] != 0) {
                        matrix[row][index++] = matrix[row][col];
                    }
                }

                // 마지막에 쓴 위치 다음부터 모두 0으로 지운다
                for (int col = index; col < matrix[row].length; col++) {
                    matrix[row][col] = 0;
                }
            }
        }

    }

    private static int[][] copy(int[][] matrix) {
        int[][] copiedMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < copiedMatrix.length; i++) {
            System.arraycopy(matrix[i], 0, copiedMatrix[i], 0, copiedMatrix[i].length);
        }
        return copiedMatrix;
    }

    private static int getMax(int[][] matrix) {
        int max = -1;
        for (int[] row : matrix) {
            for (int element : row) {
                max = Math.max(max, element);
            }
        }
        return max;
    }

}
