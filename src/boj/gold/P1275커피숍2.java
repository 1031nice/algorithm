package boj.gold;

import java.io.*;

public class P1275커피숍2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        long[] tree = new long[4 * 100_000];
        long[] numbers = new long[100_000 + 1];

        String[] split = reader.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int Q = Integer.parseInt(split[1]);

        split = reader.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            numbers[i + 1] = Long.parseLong(split[i]);
            update(tree, i + 1, numbers[i + 1], 1, 1, 100_000);
        }

        for (int i = 0; i < Q; i++) {
            split = reader.readLine().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            if (x > y) {
                int temp = x;
                x = y;
                y = temp;
            }

            writer.write(query(tree, 1, x, y, 1, 100_000) + "\n");

            int a = Integer.parseInt(split[2]);
            long b = Integer.parseInt(split[3]); // 21억 - (-21억) => 정수범위 초과

            update(tree, a, b - numbers[a], 1, 1, 100_000);
            numbers[a] = b;
        }

        writer.flush();
    }

    private static long query(long[] tree, int root, int qStart, int qEnd, int start, int end) {
        if (qStart <= start && end <= qEnd) { // include
            return tree[root];
        } else if (end < qStart || qEnd < start) { // exclude
            return 0;
        }

        int mid = (start + end) / 2;
        return query(tree, root * 2, qStart, qEnd, start, mid) +
                query(tree, root * 2 + 1, qStart, qEnd, mid + 1, end);
    }

    private static void update(long[] tree, int index, long diff, int root, int start, int end) {
        if (start > index || index > end) {
            return;
        }

        tree[root] += diff;

        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(tree, index, diff, root * 2, start, mid);
        update(tree, index, diff, root * 2 + 1, mid + 1, end);
    }

}
