package boj.gold;

import java.util.Scanner;

public class P14500테트로미노 {
    public static void main(String[] args) {
        int[][] figures = {
                {0, 0, 0, 0}, // 1번도형 가로
                {0, 1, 2, 3},
                {0, 1, 2, 3}, // 1번도형 세로
                {0, 0, 0, 0},

                {0, 0, 1, 1}, // 2번도형
                {0, 1, 0, 1},

                {0, 1, 2, 2}, // 3번도형 L
                {0, 0, 0, 1},
                {0, 0, 0, -1}, // 3번도형 반대 'ㄴ'
                {0, 1, 2, 2},
                {0, 0, 0, 1}, // 3번도형 'ㄱ'
                {0, 1, 2, 2},
                {0, 0, 0, 1}, // 3번도형 반대 'ㄱ'
                {0, 1, 2, 0},
                {0, 1, 2, 2}, // 3번도형 반대 'L'
                {0, 0, 0, -1},
                {0, 1, 1, 1}, // 3번도형 'ㄴ'
                {0, 0, 1, 2},
                {0, 0, 1, 2}, // 3번도형 길쭉한 'ㄱ'
                {0, 1, 1, 1},
                {0, 0, 1, 2}, // 3번도형 반대 길쭉한 'ㄱ'
                {0, 1, 0, 0},

                {0, 1, 1, 2}, // 4번도형1
                {0, 0, 1, 1},
                {0, 1, 1, 2}, // 4번도형1의 y축 대칭
                {0, 0, -1, -1},
                {0, 0, 1, 1}, // 4번도형1의 90도 회전
                {0, 1, 0, -1},
                {0, 0, 1, 1}, // 4번도형1의 270도 회전
                {0, 1, 1, 2},
                
                {0, 0, 0, 1}, // 5번도형 ㅜ
                {0, 1, 2, 1},
                {0, 0, 0, -1}, // 5번도형 ㅗ
                {0, 1, 2, 1},
                {0, 0, -1, 1}, // 5번도형 ㅓ
                {0, 1, 1, 1},
                {0, 0, -1, 1}, // 5번도형 ㅏ
                {0, 1, 0, 0}
        };

        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int[][] matrix = new int[row][col];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int ret = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                ret = Math.max(ret, setFigure(matrix, figures, i, j));
            }
        }

        System.out.println(ret);
    }

    private static int setFigure(int[][] matrix, int[][] figures, int row, int col) {
        int ret = -1;
        for (int i = 0; i < figures.length; i += 2) {
            int[] dirRow = figures[i];
            int[] dirCol = figures[i + 1];

            int sum = 0;
            boolean isOk = true;
            for (int j = 0; j < 4; j++) {
                int newRow = row + dirRow[j];
                int newCol = col + dirCol[j];
                if (!isIndexOkay(matrix, newRow, newCol)) {
                    isOk = false;
                    break;
                }

                sum += matrix[newRow][newCol];
            }

            if (isOk) {
                ret = Math.max(ret, sum);
            }
        }

        return ret;
    }

    private static boolean isIndexOkay(int[][] matrix, int row, int col) {
        return row >= 0 && col >= 0 && row < matrix.length && col < matrix[row].length;
    }
}
