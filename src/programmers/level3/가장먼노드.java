package programmers.level3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 가장먼노드 {

    public static void main(String[] args) {
        new 가장먼노드().solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
    }

    public int solution(int n, int[][] edge) {
        ArrayList<Integer>[] adjList = new ArrayList[n];
        for(int i=0; i<n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<edge.length; i++) {
            adjList[edge[i][0]-1].add(edge[i][1]-1);
            adjList[edge[i][1]-1].add(edge[i][0]-1);
        }
        Queue<int[]> q = new LinkedList<>();
        int maxDist = -1;
        int count = 0;
        boolean[] visited = new boolean[n];
        q.add(new int[]{0, 0});
        while(!q.isEmpty()) {
            int[] poll = q.poll();
            if(visited[poll[0]])
                continue;
            visited[poll[0]] = true;
            if(poll[1] > maxDist) {
                maxDist = poll[1];
                count = 1;
            }
            else if(poll[1] == maxDist) {
                count++;
            }
            for(int i=0; i<adjList[poll[0]].size(); i++) {
                Integer next = adjList[poll[0]].get(i);
                if(!visited[next]) {
                    q.add(new int[]{next, poll[1] + 1});
                }
            }
        }
        return count;
    }
}
