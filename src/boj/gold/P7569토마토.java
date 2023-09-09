import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P7569토마토 {
    static class Point {
        int r;
        int c;
        int h;
        int d;

        public Point(int r, int c, int h, int d) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = reader.readLine().split(" ");
        int c = Integer.parseInt(split[0]);
        int r = Integer.parseInt(split[1]);
        int h = Integer.parseInt(split[2]);

        int[][][] matrix = new int[r][c][h];
        boolean[][][] visited = new boolean[r][c][h];
        Queue<Point> q = new LinkedList<>();
        int youngTomatoes = 0;

        for (int k=0; k<h; k++) {
            for (int i = 0; i < r; i++) {
                split = reader.readLine().split(" ");
                for (int j = 0; j < c; j++) {
                    int value = Integer.parseInt(split[j]);
                    matrix[i][j][k] = value;

                    if (value == 1) {
                        q.add(new Point(i, j, k, 0));
                    } else if (value == 0) {
                        youngTomatoes++;
                    }
                }
            }
        }

        int[] dirR = {1, -1, 0, 0, 0, 0};
        int[] dirC = {0, 0, 1, -1, 0, 0};
        int[] dirH = {0, 0, 0, 0, 1, -1};

        while(!q.isEmpty()) {
            Point point = q.poll();
            if (visited[point.r][point.c][point.h]) {
                continue;
            }

            visited[point.r][point.c][point.h] = true;
            if (matrix[point.r][point.c][point.h] == 0) {
                youngTomatoes--;
            }

            if (youngTomatoes == 0) {
                System.out.println(point.d);
                return;
            }

            for (int dir=0; dir<6; dir++) {
                int newR = point.r + dirR[dir];
                int newC = point.c + dirC[dir];
                int newH = point.h + dirH[dir];

                if (newR >= 0 && newR < r &&
                        newC >= 0 && newC < c &&
                        newH >= 0 && newH < h &&
                        !visited[newR][newC][newH]
                        && matrix[newR][newC][newH] == 0) {
                    q.add(new Point(newR,newC,newH,point.d + 1));
                }
            }

        }

        if (youngTomatoes != 0) {
            System.out.println(-1);
        }
    }
}
