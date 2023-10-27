package boj.gold;

import java.util.Scanner;

public class P17090미로_탈출하기 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        char[][] matrix = new char[N][M];
        int[][] visited = new int[N][M];

        for (int i=0; i<N; i++) {
            String next = scanner.next();
            for (int j=0; j<M; j++) {
                matrix[i][j] = next.charAt(j);
                visited[i][j] = -1;
            }
        }

        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                dfs(matrix, visited, i, j, 1);
            }
        }

        int cnt = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (visited[i][j] == 1) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    // -1 방문하지 않음
    // 0 방문했고 길이 없음
    // 1 방문했고 길이 있음
    private static int dfs(char[][] matrix, int[][] visited, int row, int col, int cnt) {
        if (visited[row][col] > 0) {
            return 1;
        } else if (visited[row][col] == 0 || cnt > matrix.length * matrix[0].length) {
            return 0;
        }

        visited[row][col] = 0; // 우선 길이 없다고 표시

        char dir = matrix[row][col];
        int newRow = row;
        int newCol = col;
        switch (dir) {
            case 'U':
                newRow--;
                break;
            case 'D':
                newRow++;
                break;
            case 'R':
                newCol++;
                break;
            case 'L':
                newCol--;
                break;
        }

        if (newRow < 0 || newCol < 0 || newRow >= matrix.length || newCol >= matrix[0].length) {
            visited[row][col] = 1;
            return 1;
        }

        visited[row][col] = dfs(matrix, visited, newRow, newCol, cnt + 1);
        return visited[row][col];
    }
}
