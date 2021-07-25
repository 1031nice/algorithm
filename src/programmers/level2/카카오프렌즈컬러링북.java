package programmers.level2;

import java.util.Arrays;

public class 카카오프렌즈컬러링북 {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new 카카오프렌즈컬러링북().solution(6, 4, new int[][]{{1,1,1,0}, {1,2,2,0}, {1,0,0,1}, {0,0,0,1}, {0,0,0,3}, {0,0,0,3}}))
        );
    }

    public int[] solution(int m, int n, int[][] picture) {

        boolean[][] visited = new boolean[m][n];
        int max = 0;

        // dfs
        int count = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(picture[i][j] != 0 && !visited[i][j]) {
                    int temp = dfs(picture, visited, i, j, picture[i][j], 0);
                    max = Math.max(max, temp);
                    count++;
                }
            }
        }

        int[] answer = {count, max};
        return answer;
    }

    // 위 오른 아래 왼
    static int[] dirRow = {-1, 0, 1, 0};
    static int[] dirCol = {0, 1, 0, -1};

    private static int dfs(int[][] matrix, boolean[][] visited, int row, int col, int value, int count) {
        int ret = 0;
        if(row >= matrix.length || row < 0 || col >= matrix[0].length || col < 0)
            return ret;
        else if(visited[row][col] || matrix[row][col] == 0 || matrix[row][col] != value)
            return ret;

        ret += 1;
        visited[row][col] = true;

        for(int dir=0; dir<4; dir++)
            ret += dfs(matrix, visited, row+dirRow[dir], col+dirCol[dir], value, count+1);
        return ret;
    }
}
