package koi.koi2022primary;

import java.util.Arrays;
import java.util.Scanner;

public class 빵 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Pair[] pairs = new Pair[N];
        for (int i = 0; i < N; i++) {
            pairs[i] = new Pair(scanner.nextInt(), scanner.nextInt());
        }
        Arrays.sort(pairs);
        for (Pair pair : pairs) {
            if (pair.time >= pair.dist) {
                System.out.println(pair.time);
                return;
            }
        }
        System.out.println(-1);
    }
}

class Pair implements Comparable<Pair> {
    int dist;
    int time;

    public Pair(int dist, int time) {
        this.dist = dist;
        this.time = time;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.time == o.time) {
            return this.dist - o.dist;
        }
        return this.time - o.time;
    }
}