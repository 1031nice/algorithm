package boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2170선_긋기 {

    static class Range implements Comparable<Range> {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Range o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Range[] ranges = new Range[N];
        for (int i=0; i<N; i++) {
            String[] split = reader.readLine().split(" ");
            ranges[i] = new Range(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        Arrays.sort(ranges);

        int sum = 0;
        int start = ranges[0].start;
        int end = ranges[0].end;
        for (int i=1; i<ranges.length; i++) {
            Range now = ranges[i];

            if (now.start > end) { // start new one
                sum += end - start;
                start = ranges[i].start;
                end = ranges[i].end;
            } else if (now.end > end) {
                end = now.end;
            }
        }

        if (ranges[ranges.length - 1].end > end) {
            end = ranges[ranges.length - 1].end;
        }

        sum += end - start;
        System.out.println(sum);
    }

}
