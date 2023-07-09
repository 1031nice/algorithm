package boj.platinum;

import java.io.*;

public class P2243사탕상자 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] tree = new int[4 * 1_000_000][2];

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String[] split = reader.readLine().split(" ");
            int A = Integer.parseInt(split[0]);
            if (A == 1) {
                int rank = Integer.parseInt(split[1]);
                writer.write(query(tree, rank, 1, 1, 1_000_000) + "\n");
            } else {
                int flavor = Integer.parseInt(split[1]);
                int diff = Integer.parseInt(split[2]);
                update(tree, flavor, diff, 1, 1, 1_000_000);
            }
        }

        writer.flush();
    }

    private static int query(int[][] tree, int rank, int root, int start, int end) {
        int rootValue = tree[root][0];

        if (rootValue < rank) {
            return 0;
        }
        tree[root][0]--;

        if (start == end) { // leaf node
            return tree[root][1];
        }

        int mid = (start + end) / 2;
        int left = query(tree, rank, root * 2, start, mid);
        if (left != 0) { // exists on left side
            return left;
        } else { // exists on right side
            return query(tree, rank - tree[root * 2][0], root * 2 + 1, mid + 1, end);
        }
    }

    private static void update(int[][] tree, int flavor, int diff, int root, int start, int end) {
        if (start > flavor || flavor > end) {
            return;
        }

        tree[root][0] += diff;
        if (start == end) { // leaf node
            tree[root][1] = flavor;
            return;
        }

        int mid = (start + end) / 2;
        update(tree, flavor, diff, root * 2, start, mid);
        update(tree, flavor, diff, root * 2 + 1, mid + 1, end);
    }

}
