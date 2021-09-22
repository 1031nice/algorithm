package programmers.level2;

import java.util.PriorityQueue;

public class 배달 {
    public static void main(String[] args) {
        System.out.println(new 배달().solution(6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4));
    }

    static class Node implements Comparable<Node> {
        int id;
        int weight;

        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    // dijk
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[][] matrix = new int[N][N];
        for(int i=0; i<road.length; i++) {
            int a = road[i][0] - 1;
            int b = road[i][1] - 1;
            int weight = road[i][2];
            matrix[a][b] = matrix[a][b] == 0 ? weight : Math.min(matrix[a][b], weight);
            matrix[b][a] = matrix[b][a] == 0 ? weight : Math.min(matrix[b][a], weight);
        }

        int[] dist = new int[N];
        boolean[] visited = new boolean[N];
        for(int i=0; i<dist.length; i++)
            dist[i] = Integer.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        dist[0] = 0;
        while(!pq.isEmpty()) {
            Node poll = pq.poll();
            if(visited[poll.id])
                continue;
            visited[poll.id] = true;
            for(int i=0; i<matrix[poll.id].length; i++) {
                if(matrix[poll.id][i] != 0 && !visited[i]) {
                    int newWeight = dist[poll.id] + matrix[poll.id][i];
                    if(dist[i] > newWeight) {
                        dist[i] = newWeight;
                        pq.add(new Node(i, dist[i]));
                    }
                }
            }
        }

        for (int j : dist) {
            if (j <= K)
                answer++;
        }

        return answer;
    }

    // dfs 시간 초과
    // node 50개, edge 2000개
    // 하나의 node에서 다른 모든 node까지의 최단 경로를 찾으므로 최악의 경우 100,000에
    // 경로를 찾는 과정이 dfs이고 두 node 사이에 여러 edge가 존재할 수 있으므로
    // (A -> B까지 a개 B -> C까지 b개라면 A->C는 a+b가 아니라 a*b 가지)
    // 최악의 경우 100,000 보다 훨씬 큰 수
    public int solution2(int N, int[][] road, int K) {
        int[][] matrix = new int[N][N];
        for(int i=0; i<road.length; i++) {
            int a = road[i][0] - 1;
            int b = road[i][1] - 1;
            int weight = road[i][2];
            matrix[a][b] = matrix[a][b] == 0 ? weight : Math.min(matrix[a][b], weight);
            matrix[b][a] = matrix[b][a] == 0 ? weight : Math.min(matrix[b][a], weight);
        }
        int answer = 0;
        for(int i=1; i<N; i++) {
            boolean[] visited = new boolean[N];
            if(dfs(matrix, visited, 0, i) <= K) answer++;
        }
        return answer + 1;
    }

    int dfs(int[][] matrix, boolean[] visited, int start, int end) {
        if(start == end) {
            return 0;
        }
        visited[start] = true;
        int ret = 500001;
        for(int i=0; i<matrix[start].length; i++) {
            if(matrix[start][i] != 0 && !visited[i]) {
                int temp = matrix[start][i] + dfs(matrix, visited, i, end);
                ret = Math.min(temp, ret);
            }
        }
        visited[start] = false;
        return ret;
    }
}
