package boj;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 최소비용구하기_1916 {

    static class City implements Comparable<City>{
        int id;
        int weight;

        public City(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(City o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numOfCities = Integer.parseInt(br.readLine());
        int numOfEdges = Integer.parseInt(br.readLine());
        List<City>[] adjList = new ArrayList[numOfCities+1];
        String[] s;
        for(int i=0; i<adjList.length; i++)
            adjList[i] = new ArrayList<>();
        for(int i=0; i<numOfEdges; i++) {
            s = br.readLine().split(" ");
            adjList[Integer.parseInt(s[0])].add(new City(Integer.parseInt(s[1]), Integer.parseInt(s[2])));
        }
        s = br.readLine().split(" ");
        int start = Integer.parseInt(s[0]);
        int end = Integer.parseInt(s[1]);
        int[] dist = new int[numOfCities+1];
        Arrays.fill(dist, 987654321);

        boolean[] visited = new boolean[numOfCities+1];
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(start, 0));
        while(!pq.isEmpty()) {
            City poll = pq.poll();
            if(visited[poll.id])
                continue;

            visited[poll.id] = true;
            for(int i=0; i<adjList[poll.id].size(); i++) {
                City next = adjList[poll.id].get(i);
                if (poll.weight + next.weight < dist[next.id]) {
                    dist[next.id] = poll.weight + next.weight;
                    pq.add(new City(next.id, dist[next.id]));
                }
            }
        }
        bw.write(dist[end] + "");
        bw.flush();
    }

}
