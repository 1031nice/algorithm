package boj;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 특정한최단경로_1504 {

    static class Node implements Comparable<Node> {
        int id;
        int weight;

        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        int nNodes = Integer.parseInt(s[0]);
        int nEdges = Integer.parseInt(s[1]);
        ArrayList<Node>[] adjList = new ArrayList[nNodes+1];
        for(int i=1; i<nNodes+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for(int i=0; i<nEdges; i++) {
            s = br.readLine().split(" ");
            adjList[Integer.parseInt(s[0])].add(new Node(Integer.parseInt(s[1]), Integer.parseInt(s[2])));
            adjList[Integer.parseInt(s[1])].add(new Node(Integer.parseInt(s[0]), Integer.parseInt(s[2])));
        }
        s = br.readLine().split(" ");
        int n1 = Integer.parseInt(s[0]);
        int n2 = Integer.parseInt(s[1]);

        int[] distS = new int[nNodes+1];
        int[] distN1 = new int[nNodes+1];
        int[] distN2 = new int[nNodes+1];
        Arrays.fill(distS, 987654321);
        distS[1] = 0;
        Arrays.fill(distN1, 987654321);
        distN1[n1] = 0;
        Arrays.fill(distN2, 987654321);
        distN2[n2] = 0;
        dijk(adjList, distS, 1);
        dijk(adjList, distN1, n1);
        dijk(adjList, distN2, n2);

        int d1, d2;
        if(distS[n1] == 987654321 || distN1[n2] == 987654321 || distN2[nNodes] == 987654321)
            d1 = 987654321; // s -> n1 -> n2 -> e
        else
            d1 = distS[n1] + distN1[n2] + distN2[nNodes];
        if(distS[n2] == 987654321 || distN2[n1] == 987654321 || distN1[nNodes] == 987654321)
            d2 = 987654321; // s -> n1 -> n2 -> e
        else
            d2 = distS[n2] + distN2[n1] + distN1[nNodes];

        int d = d1 + d2 == 987654321 * 2 ? -1 : Math.min(d1, d2);

        bw.write(d+"\n");
        bw.flush();
    }

    private static void dijk(ArrayList<Node>[] adjList, int[] dist, int start) {
        boolean[] visited = new boolean[dist.length];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        while(!pq.isEmpty()) {
            Node poll = pq.poll();
            if(visited[poll.id])
                continue;

            visited[poll.id] = true;
            for(int i=0; i<adjList[poll.id].size(); i++) {
                Node next = adjList[poll.id].get(i);
                if(poll.weight + next.weight < dist[next.id]) {
                    dist[next.id] = poll.weight + next.weight;
                    pq.add(new Node(next.id, dist[next.id]));
                }
            }
        }
    }

}
