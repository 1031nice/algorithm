package boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P2533사회망_서비스 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        List<Integer>[] adjList = new List[N + 1];
        boolean[] earlyAdopter = new boolean[N + 1];
        boolean[] visited = new boolean[N + 1];
        int[][] cache = new int[1000001][2];
        for (int i=0; i<adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i=0; i<N-1; i++) {
            String[] split = reader.readLine().split(" ");

            int n1 = Integer.parseInt(split[0]);
            int n2 = Integer.parseInt(split[1]);

            adjList[n1].add(n2);
            adjList[n2].add(n1);
        }

        System.out.println(func(adjList, cache, visited, earlyAdopter, 1, true));
    }

    private static int func(List<Integer>[] adjList, int[][] cache, boolean[] visited, boolean[] earlyAdopter, int start, boolean isParentEarlyAdopter) {
        int cacheIndex = isParentEarlyAdopter ? 1 : 0;
        if (cache[start][cacheIndex] != 0) {
            return cache[start][cacheIndex];
        }

        int ret1 = 1_000_000;
        visited[start] = true;

        if (isParentEarlyAdopter) {
            // 내가 early adopter가 아닐 때
            ret1 = 0;
            for (int i=0; i<adjList[start].size(); i++) {
                if (!visited[adjList[start].get(i)]) {
                    ret1 += func(adjList, cache, visited, earlyAdopter, adjList[start].get(i), false);
                }
            }
        }

        // 내가 early adopter일 때
        int ret2 = 1;
        earlyAdopter[start] = true;
        for (int i=0; i<adjList[start].size(); i++) {
            if (!visited[adjList[start].get(i)]) {
                ret2 += func(adjList, cache, visited, earlyAdopter, adjList[start].get(i), true);
            }
        }

        visited[start] = false;
        int ret = Math.min(ret1, ret2);
        cache[start][cacheIndex] = ret;
        return ret;
    }
}
