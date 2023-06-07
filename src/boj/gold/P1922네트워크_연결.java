package boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P1922네트워크_연결 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        int M = Integer.parseInt(bufferedReader.readLine());

        PriorityQueue<Edge> edges = new PriorityQueue<>();
        int[] parents = new int[N];
        Arrays.fill(parents, -1);

        for (int i = 0; i < M; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            edges.add(new Edge(Integer.parseInt(split[0]) - 1, Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2])));
        }

        int cost = 0;
        int count = 0;

        while (!edges.isEmpty() && count < N) {
            Edge edge = edges.poll();
            if (union(parents, edge.src, edge.dest)) {
                count++;
                cost += edge.weight;
            }
        }

        System.out.println(cost);
    }

    static int find(int[] parents, int node) {
        if (parents[node] < 0) {
            return node;
        }
        return parents[node] = find(parents, parents[node]);
    }

    static boolean union(int[] parents, int node1, int node2) {
        int p1 = find(parents, node1);
        int p2 = find(parents, node2);

        if (p1 == p2) {
            return false;
        }

        if (p1 < p2) {
            parents[p1] += parents[p2];
            parents[p2] = p1;
        } else {
            parents[p2] += parents[p1];
            parents[p1] = p2;
        }

        return true;
    }

    static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

}
