package boj.gold;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P3584가장_가까운_공통_조상 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(reader.readLine());

        for (int t = 0; t < TC; t++) {
            int N = Integer.parseInt(reader.readLine());
            List<Integer>[] adjList = new ArrayList[N + 1];
            for (int i = 0; i < adjList.length; i++) {
                adjList[i] = new ArrayList<>();
            }

            boolean[] visited = new boolean[N + 1];
            for (int i = 0; i < N - 1; i++) {
                String[] split = reader.readLine().split(" ");
                adjList[Integer.parseInt(split[0])].add(Integer.parseInt(split[1]));
                visited[Integer.parseInt(split[1])] = true;
                adjList[Integer.parseInt(split[1])].add(Integer.parseInt(split[0]));
            }

            int[] depth = new int[N + 1];
            int[] parent = new int[N + 1];

            int start = 1;
            for (int i = 1; i < visited.length; i++) {
                if (!visited[i]) {
                    start = i;
                    break;
                }
            }

            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            q.add(0); // depth

            visited = new boolean[N + 1];
            while (!q.isEmpty()) {
                Integer now = q.poll();
                Integer nowDepth = q.poll();
                if (visited[now]) {
                    continue;
                }

                visited[now] = true;
                for (int i = 0; i < adjList[now].size(); i++) {
                    Integer child = adjList[now].get(i);
                    if (!visited[child]) {
                        parent[child] = now;
                        depth[child] = nowDepth + 1;
                        q.add(child);
                        q.add(depth[child]);
                    }
                }
            }

            String[] split = reader.readLine().split(" ");
            int commonParent = lca(parent, depth, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            writer.write(commonParent + "\n");
            writer.flush();
        }
    }

    private static int lca(int[] parent, int[] depth, int n1, int n2) {
        // depth가 더 깊은 노드가 n1이도록 설정
        if (depth[n2] > depth[n1]) {
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }

        while (depth[n1] != depth[n2]) {
            n1 = parent[n1];
        }

        while (n1 != n2) {
            n1 = parent[n1];
            n2 = parent[n2];
        }

        return n1;
    }
}
