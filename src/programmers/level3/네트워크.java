package programmers.level3;

public class 네트워크 {

    public static void main(String[] args) {
        System.out.println(new 네트워크().solution(3, new int[][] {{1,1,0}, {1,1,0}, {0,0,1}}));
    }

    // union-find 풀이
    public int solution(int n, int[][] computers) {
        int[] parents = new int[n+1];
        for(int i=1; i<=n; i++) {
            parents[i] = -1;
        }
        for(int i=0; i<computers.length; i++) {
            for(int j=0; j<computers[i].length; j++) {
                if(i == j)
                    continue;
                if(computers[i][j] == 1)
                    union(parents, i+1, j+1);
            }
        }

        int answer = 0;
        for(int i=1; i<=n; i++) {
            if(parents[i] < 0)
                answer++;
        }
        return answer;
    }

    public void union(int[] parents, int i, int j) {
        int p_i = find(parents, i);
        int p_j = find(parents, j);
        if(p_i != p_j) {
            if(Math.abs(parents[p_i]) < Math.abs(parents[p_j])) {
                parents[p_j] += parents[p_i];
                parents[p_i] = p_j;
            }
            else {
                parents[p_i] += parents[p_j];
                parents[p_j] = p_i;
            }
        }
    }

    public int find(int[] parents, int i) {
        if(parents[i] < 0)
            return i;
        return parents[i] = find(parents, parents[i]);
    }

    // DFS 풀이
    public int solution2(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                func(computers, visited, i);
                answer++;
            }
        }
        return answer;
    }

    public void func(int[][] computers, boolean[] visited, int next) {
        if(visited[next])
            return;
        visited[next] = true;
        for(int i=0; i<computers[next].length; i++) {
            if(i != next && computers[next][i] != 0) {
                func(computers, visited, i);
            }
        }
    }
}
