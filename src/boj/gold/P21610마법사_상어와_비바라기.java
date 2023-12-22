package boj.gold;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P21610마법사_상어와_비바라기 {
    static int[] dirRow = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dirCol = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[][] matrix = new int[N][N];
        int[][] moves = new int[M][2];
        Queue<Integer> clouds = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        for (int i=0; i<M; i++) {
            moves[i][0] = scanner.nextInt();
            moves[i][1] = scanner.nextInt();
        }

        int lastIndex = matrix.length - 1;
        clouds.add(lastIndex);
        clouds.add(0);
        clouds.add(lastIndex);
        clouds.add(1);
        clouds.add(lastIndex - 1);
        clouds.add(0);
        clouds.add(lastIndex - 1);
        clouds.add(1);

        for (int m=0; m<moves.length; m++) {
            int dir = moves[m][0] - 1;
            int distRow = dirRow[dir] * moves[m][1];
            int distCol = dirCol[dir] * moves[m][1];

            int size = clouds.size();
            for (int i=0; i<size; i+=2) {
                Integer row = clouds.poll();
                Integer col = clouds.poll();

                if (distRow > 0) {
                    row = (row + distRow) % matrix.length;
                } else {
                    row += distRow;
                    while (row < 0) {
                        row += N;
                    }
                }

                if (distCol > 0) {
                    col = (col + distCol) % matrix[0].length;
                } else {
                    col += distCol;
                    while (col < 0) {
                        col += N;
                    }
                }

                matrix[row][col]++;
                clouds.add(row);
                clouds.add(col);
            }

            boolean[][] visited = new boolean[N][N];
            while (!clouds.isEmpty()) {
                Integer row = clouds.poll();
                Integer col = clouds.poll();
                visited[row][col] = true;

                int cnt = 0;
                for (int i = 1; i < 8; i += 2) { // 1,3,5,7 대각선
                    int newRow = row + dirRow[i];
                    int newCol = col + dirCol[i];
                    if (newRow < 0 || newCol < 0 || newRow >= N || newCol >= N) {
                        continue;
                    }

                    if (matrix[newRow][newCol] > 0) {
                        cnt++;
                    }
                }

                matrix[row][col] += cnt;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && matrix[i][j] >= 2) {
                        matrix[i][j] -= 2;
                        clouds.add(i);
                        clouds.add(j);
                    }
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ret += matrix[i][j];
            }
        }

        System.out.println(ret);
    }
}

