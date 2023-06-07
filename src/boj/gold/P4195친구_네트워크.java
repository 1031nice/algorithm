package boj.gold;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P4195친구_네트워크 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(bufferedReader.readLine());
        for (int t = 0; t < T; t++) {
            int index = 0;
            int F = Integer.parseInt(bufferedReader.readLine());
            List<Integer> parents = new ArrayList<>();
            Map<String, Integer> nameToNumber = new HashMap<>();
            for (int i = 0; i < F; i++) {
                String[] names = bufferedReader.readLine().split(" ");
                String name1 = names[0];
                String name2 = names[1];
                if (!nameToNumber.containsKey(name1)) {
                    parents.add(-1);
                    nameToNumber.put(name1, index++);
                }
                if (!nameToNumber.containsKey(name2)) {
                    parents.add(-1);
                    nameToNumber.put(name2, index++);
                }

                int ret = union(parents, nameToNumber.get(name1), nameToNumber.get(name2));
                if (ret < 0) {
                    bufferedWriter.write(-ret + "\n");
                }
            }
        }

        bufferedWriter.flush();
    }

    static int find(List<Integer> parents, int node) {
        if (parents.get(node) < 0) {
            return node;
        }
        parents.set(node, find(parents, parents.get(node)));
        return parents.get(node);
    }

    static int union(List<Integer> parents, int node1, int node2) {
        int p1 = find(parents, node1);
        int p2 = find(parents, node2);

        if (p1 == p2) {
            return parents.get(p1);
        }

        if (parents.get(p1) < parents.get(p2)) {
            parents.set(p1, parents.get(p1) + parents.get(p2));
            parents.set(p2, p1);
            return parents.get(p1);
        } else {
            parents.set(p2, parents.get(p2) + parents.get(p1));
            parents.set(p1, p2);
            return parents.get(p2);
        }
    }
}
