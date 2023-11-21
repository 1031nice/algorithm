package boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class P2573빙산 {

    static class Point {
        int row;
        int col;
        int life;
        int time;

        public Point(int row, int col, int life, int time) {
            this.row = row;
            this.col = col;
            this.life = life;
            this.time = time;
        }
    }

    static int[] dirRow = {-1, 0, 1, 0};
    static int[] dirCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] split = reader.readLine().split(" ");

        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);

        int[][] matrix = new int[N][M];

        Queue<Point> nowQueue = new LinkedList<>();
        for (int i=0; i<N; i++) {
            split = reader.readLine().split(" ");
            for (int j=0; j<M; j++) {
                int life = Integer.parseInt(split[j]);
                if (life != 0) {
                    matrix[i][j] = life;
                    nowQueue.add(new Point(i, j, life, 0));
                }
            }
        }

        int lastTime = 0;
        Queue<Point> beforeQueue = new LinkedList<>();
        boolean isIceRemoved = false;
        while (!nowQueue.isEmpty()) {
            Point now = nowQueue.poll();
            int size = 0;
            int startRow = 0;
            int startCol = 0;

            if (now.time > lastTime) { // 시간이 지나면 모든 빙산에 대해 일괄 적용
                lastTime = now.time;
                while (!beforeQueue.isEmpty()) {
                    Point point = beforeQueue.poll();
                    matrix[point.row][point.col] -= point.life;
                    if (matrix[point.row][point.col] > 0) {
                        size++;
                        startRow = point.row;
                        startCol = point.col;
                    } else {
                        isIceRemoved = true;
                    }
                }
            }

            if (isIceRemoved) {
                isIceRemoved = false;
                if (!isOneComponent(matrix, size, startRow, startCol)) {
                    System.out.println(now.time);
                    return;
                }
            }

            if (matrix[now.row][now.col] <= 0) {
                continue;
            }

            int cnt = 0;
            for (int i=0; i<4; i++) {
                int newRow = now.row + dirRow[i];
                int newCol = now.col + dirCol[i];
                if (newRow < 0 || newCol < 0 || newRow >= N || newCol >= M) {
                    continue;
                }

                if (matrix[newRow][newCol] <= 0) { // matrix를 지금 수정하면 현재 얼음이 녹는 경우 다음 얼음이 현재 얼음에 영향을 받을 수 있다
                    now.life--;
                    cnt++;
                }
            }
            now.time++;

            nowQueue.add(now);
            beforeQueue.add(new Point(now.row, now.col, cnt, 0));
        }

        System.out.println(0);
    }

    private static boolean isOneComponent(int[][] matrix, int size, int row, int col) {
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        q.add(row);
        q.add(col);
        while (!q.isEmpty()) {
            row = q.poll();
            col = q.poll();

            if (visited[row][col]) {
                continue;
            }

            cnt++;
            visited[row][col] = true;
            for (int dir=0; dir<4; dir++) {
                int newRow = row + dirRow[dir];
                int newCol = col + dirCol[dir];

                if (newRow < 0 || newCol < 0 || newRow >= matrix.length || newCol >= matrix[0].length || visited[newRow][newCol]) {
                    continue;
                }

                if (matrix[newRow][newCol] > 0) {
                    q.add(newRow);
                    q.add(newCol);
                }
            }
        }
        // 컴포넌트가 하나라면 그래프를 이루는 노드의 수와 bfs를 통해 방문한 노드의 수가 같다
        return cnt == size;
    }

}