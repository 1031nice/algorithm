package boj.gold;

import java.util.*;

public class P14502연구소 {
    static int[] dirRow = {-1, 0, 1, 0};
    static int[] dirCol = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int[][] matrix = new int[row][col];
        List<Integer> virus = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = scanner.nextInt();
                if (matrix[i][j] == 2) {
                    virus.add(i);
                    virus.add(j);
                }
            }
        }

        int ret = 0;

        for (int i = 0; i < row * col; i++) {
            int wallRow1 = i / col;
            int wallCol1 = i % col;
            if (matrix[wallRow1][wallCol1] != 0) {
                continue;
            }

            matrix[wallRow1][wallCol1] = 1;
            for (int j = i + 1; j < row * col; j++) {
                int wallRow2 = j / col;
                int wallCol2 = j % col;
                if (matrix[wallRow2][wallCol2] != 0) {
                    continue;
                }

                matrix[wallRow2][wallCol2] = 1;
                for (int k = j + 1; k < row * col; k++) {
                    int wallRow3 = k / col;
                    int wallCol3 = k % col;
                    if (matrix[wallRow3][wallCol3] != 0) {
                        continue;
                    }

                    matrix[wallRow3][wallCol3] = 1;
                    ret = Math.max(ret, bfs(matrix, virus));
                    matrix[wallRow3][wallCol3] = 0;
                }
                matrix[wallRow2][wallCol2] = 0;
            }

            matrix[wallRow1][wallCol1] = 0;
        }

        System.out.println(ret);
    }

    private static int bfs(int[][] matrix, List<Integer> virus) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < virus.size(); i += 2) {
            q.add(virus.get(i));
            q.add(virus.get(i + 1));
        }

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        while (!q.isEmpty()) {
            int row = q.poll();
            int col = q.poll();

            if (visited[row][col]) {
                continue;
            }

            visited[row][col] = true;
            for (int dir = 0; dir < 4; dir++) {
                int newRow = row + dirRow[dir];
                int newCol = col + dirCol[dir];
                if (newRow >= matrix.length || newCol >= matrix[0].length || newRow < 0 || newCol < 0) {
                    continue;
                }

                if (!visited[newRow][newCol] && matrix[newRow][newCol] == 0) {
                    q.add(newRow);
                    q.add(newCol);
                    matrix[newRow][newCol] = 3;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    cnt++;
                } else if (matrix[i][j] == 3) {
                    matrix[i][j] = 0;
                }
            }
        }

        return cnt;
    }
}
