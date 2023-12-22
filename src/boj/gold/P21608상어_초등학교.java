package boj.gold;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P21608상어_초등학교 {
    static int[] dirRow = {-1, 0, 1, 0};
    static int[] dirCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] matrix = new int[N][N];
        int[][] students = new int[N*N][5];
        int[] studentToIndex = new int[N*N+1];
        for (int i=0; i<students.length; i++) {
            int student = scanner.nextInt();
            studentToIndex[student] = i;
            students[i][0] = student;
            for (int j=1; j<=4; j++) {
                students[i][j] = scanner.nextInt();
            }
        }

        for (int s=0; s<students.length; s++) {
            Queue<Integer> q = new LinkedList<>();
            int[] like = students[s];
            int student = students[s][0];

            // 1. 비어있는 칸 중 인접한 칸에 좋아하는 학생이 제일 많은 칸을 찾는다
            int max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j] == 0) {
                        int cnt = getAdjacentLikeCount(i, j, matrix, like);
                        if (cnt == max) {
                            q.add(i);
                            q.add(j);
                        } else if (cnt > max) {
                            max = cnt;
                            q.clear();
                            q.add(i);
                            q.add(j);
                        }
                    }
                }
            }

            if (q.size() == 2) {
                Integer row = q.poll();
                Integer col = q.poll();
                matrix[row][col] = student;
                continue;
            }

            // 2. 1을 만족하는 칸 중 비어있는 칸이 가장 많은 칸을 고른다
            Queue<Integer> q2 = new LinkedList<>();
            max = 0;
            while (!q.isEmpty()) {
                Integer row = q.poll();
                Integer col = q.poll();

                int cnt = 0;
                for (int dir=0; dir<4; dir++) {
                    int newRow = row + dirRow[dir];
                    int newCol = col + dirCol[dir];
                    if (newRow < 0 || newRow >= matrix.length || newCol < 0 || newCol >= matrix.length) {
                        continue;
                    }

                    for (int l=1; l<=4; l++) {
                        if (matrix[newRow][newCol] == 0) {
                            cnt++;
                        }
                    }
                }

                if (cnt > max) {
                    max = cnt;
                    q2.clear();
                    q2.add(row);
                    q2.add(col);
                } else if (max == cnt) {
                    q2.add(row);
                    q2.add(col);
                }
            }

            if (q2.size() == 2) {
                Integer row = q2.poll();
                Integer col = q2.poll();
                matrix[row][col] = student;
                continue;
            }

            // 3. 행의 번호가 가장 작은, 열의 번호가 가장 작은 칸을 찾는다
            int rowMin = N;
            int colMin = N;
            while (!q2.isEmpty()) {
                Integer row = q2.poll();
                Integer col = q2.poll();
                if (row < rowMin) {
                    rowMin = row;
                    colMin = col;
                } else if (row == rowMin) {
                    if (col < colMin) {
                        colMin = col;
                    }
                }
            }

            matrix[rowMin][colMin] = student;
        }

        int ret = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                int[] like = students[studentToIndex[matrix[i][j]]];
                int cnt = getAdjacentLikeCount(i, j, matrix, like);

                if (cnt > 0) {
                    ret += (int) Math.pow(10, cnt - 1);
                }
            }
        }

        System.out.println(ret);
    }

    private static int getAdjacentLikeCount(int row, int col, int[][] matrix, int[] like) {
        int cnt = 0;
        for (int dir=0; dir<4; dir++) {
            int newRow = row + dirRow[dir];
            int newCol = col + dirCol[dir];
            if (newRow < 0 || newRow >= matrix.length || newCol < 0 || newCol >= matrix.length) {
                continue;
            }

            for (int l=1; l<=4; l++) {
                if (matrix[newRow][newCol] == like[l]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
