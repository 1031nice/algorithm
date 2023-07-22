package boj.gold;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class P15681트리와_쿼리 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] split = reader.readLine().split(" ");

        int N = Integer.parseInt(split[0]);
        int R = Integer.parseInt(split[1]);
        int Q = Integer.parseInt(split[2]);

        List<Integer>[] adjList = new ArrayList[N + 1];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            split = reader.readLine().split(" ");
            int n1 = Integer.parseInt(split[0]);
            int n2 = Integer.parseInt(split[1]);
            adjList[n1].add(n2);
            adjList[n2].add(n1);
        }

        int[] cache = new int[N + 1];
        dfs(adjList, cache, new boolean[N + 1], R);

        for (int i = 0; i < Q; i++) {
            int root = Integer.parseInt(reader.readLine());
            writer.write(cache[root] + "\n");
        }

        writer.flush();
    }

    private static int dfs(List<Integer>[] adjList, int[] cache, boolean[] visited, int start) {
        int ret = 1;
        if (visited[start]) {
            return 0;
        }

        visited[start] = true;
        for (int i = 0; i < adjList[start].size(); i++) {
            if (!visited[adjList[start].get(i)]) {
                ret += dfs(adjList, cache, visited, adjList[start].get(i));
            }
        }

        cache[start] = ret;
        return ret;
    }

}
