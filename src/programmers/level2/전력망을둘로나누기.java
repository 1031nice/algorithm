package programmers.level2;

public class 전력망을둘로나누기 {
    public int solution(int n, int[][] wires) {
        int[][] matrix = new int[n][n];
        int min = 987654321;
        for(int i=0; i<wires.length; i++) {
            matrix[wires[i][0] - 1][wires[i][1] - 1] = 1;
            matrix[wires[i][1] - 1][wires[i][0] - 1] = 1;
        }
        for(int i=0; i<wires.length; i++) {
            int node1 = wires[i][0] - 1;
            int node2 = wires[i][1] - 1;
            // remove edge from graph
            matrix[node1][node2] = 0;
            matrix[node2][node1] = 0;
            boolean[] visited = new boolean[n];
            int diff = dfs(matrix, visited, 0);
            for(int j=1; j<n; j++) {
                if(!visited[j]) {
                    diff = Math.abs(diff - dfs(matrix, visited, j));
                    break;
                }
            }
            min = min < diff ? min : diff;
            // add edge to graph
            matrix[node1][node2] = 1;
            matrix[node2][node1] = 1;
        }
        return min;
    }

    public int dfs(int[][] matrix, boolean[] visited, int start) {
        int ret = 0;
        if(visited[start])
            return ret;
        visited[start] = true;
        ret++;
        for(int i=0; i<matrix[start].length; i++) {
            if(matrix[start][i] != 0 && !visited[i]) {
                ret += dfs(matrix, visited, i);
            }
        }
        return ret;
    }
}
