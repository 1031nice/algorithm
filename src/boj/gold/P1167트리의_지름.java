package boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class P1167트리의_지름 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(reader.readLine());
        List<Integer>[] adjList = new ArrayList[V + 1];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            String[] split = reader.readLine().split(" ");
            int node = Integer.parseInt(split[0]);
            for (int j = 1; j < split.length; j += 2) {
                if (split[j].equals("-1")) {
                    break;
                }
                int otherNode = Integer.parseInt(split[j]);
                int weight = Integer.parseInt(split[j + 1]);
                adjList[node].add(otherNode);
                adjList[node].add(weight);
            }
        }

        System.out.println(func(adjList, new boolean[V + 1], 1));
    }

    // 트리의 지름 구하기
    private static int func(List<Integer>[] adjList, boolean[] visited, int root) {
        int maxLength = 0;
        if (visited[root]) {
            return maxLength;
        }

        visited[root] = true;
        PriorityQueue<Integer> childrenLengths = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < adjList[root].size(); i += 2) {
            int child = adjList[root].get(i);
            int weight = adjList[root].get(i + 1);

            // 루트를 포함하는 거리 중 가장 긴 경로를 찾는다
            childrenLengths.add(weight + getLength(adjList, visited, child));

            // 루트를 포함하지 않는(서브 트리의) 지름 중 가장 긴 것
            maxLength = Math.max(maxLength, func(adjList, visited, child));
        }

        // 트리의 지름은 '서브 트리의 지름 중 가장 긴 것'과
        // '루트를 포함하는 지름 중 가장 긴 것' 중에 더 큰 값
        if (childrenLengths.isEmpty()) {
            return maxLength;
        } else if (childrenLengths.size() == 1) {
            return Math.max(maxLength, childrenLengths.poll());
        } else { // 둘 이상의 자식인 경우 루트를 포함한 두 자식까지의 경로
            return Math.max(maxLength, childrenLengths.poll() + childrenLengths.poll());
        }
    }

    // root 포함 최대 경로 구하기
    private static int getLength(List<Integer>[] adjList, boolean[] visited, int root) {
        int length = 0;
        if (visited[root]) {
            return length;
        }

        visited[root] = true;
        for (int i = 0; i < adjList[root].size(); i += 2) {
            int child = adjList[root].get(i);
            int weight = adjList[root].get(i + 1);
            if (visited[child]) {
                continue;
            }
            length = Math.max(length, weight + getLength(adjList, visited, child));
        }

        visited[root] = false;
        return length;
    }
}
