package boj;

import java.io.*;
import java.util.*;

public class 최단경로 {

    static class Vertex {
        int id;
        int weight;

        public Vertex(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    static class MyComparator implements Comparator<Vertex> {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            return o1.weight - o2.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = bufferedReader.readLine().split(" ");
        int vertex = Integer.parseInt(s[0]);
        int edge = Integer.parseInt(s[1]);
        int start = Integer.parseInt(bufferedReader.readLine()) - 1;
        List<List<int[]>> adjList = new ArrayList<>();
        for(int i=0; i<vertex; i++)
            adjList.add(new ArrayList<>());

        int[] d = new int[vertex];
        d[start] = 0;
        for(int i=0; i<vertex;i ++) {
            if(i == start)
                continue;
            d[i] = Integer.MAX_VALUE;
        }
        for(int i=0; i<edge; i++) {
            s = bufferedReader.readLine().split(" ");
            adjList.get(Integer.parseInt(s[0]) - 1).add(new int[]{Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2])});
        }
        PriorityQueue<Vertex> q = new PriorityQueue<>(new MyComparator());
        q.add(new Vertex(start, 0));
        while(!q.isEmpty()) {
            Vertex poll = q.poll();
            for(int i=0; i<adjList.get(poll.id).size(); i++) {
                int id = adjList.get(poll.id).get(i)[0];
                int weight = adjList.get(poll.id).get(i)[1];
                if(weight + d[poll.id] < d[id]) {
                    d[id] = weight + d[poll.id];
                    q.add(new Vertex(id, d[id])); // 왜 누적을 넣어주는거지 왜 누적을 안넣어줬을 때도 많은 테스트를 통과한거지
                }
            }
        }
        for(int i=0; i<vertex; i++)
            bufferedWriter.write(d[i] == Integer.MAX_VALUE ? "INF\n" : d[i] + "\n");
        bufferedWriter.flush();
    }

}
