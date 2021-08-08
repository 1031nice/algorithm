package boj;

import java.io.*;

public class 히스토그램에서가장큰직사각형_6549 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line;
        while((line = br.readLine()).length() != 1) {
            String[] s = line.split(" ");
            int n = Integer.parseInt(s[0]);
            int[] bars = new int[n];
            for(int i=0; i<n; i++) {
                bars[i] = Integer.parseInt(s[i+1]);
            }
            bw.write(func(bars, 0, bars.length-1)+"\n");
        }
        bw.flush();
    }

    private static long func(int[] bars, int start, int end) {
        if(start == end)
            return bars[start];

        int mid = (start + end) / 2;
        long max = Math.max(func(bars, start, mid), func(bars, mid+1, end));
        long minHeight = Math.min(bars[mid], bars[mid+1]);
        max = Math.max(minHeight * 2, max);

        int count = 2;
        int l = mid-1;
        int r = mid+2;
        while(l >= start && r <= end) {
            if(bars[l] > bars[r]) {
                minHeight = Math.min(minHeight, bars[l]);
                l--;
            }
            else {
                minHeight = Math.min(minHeight, bars[r]);
                r++;
            }
            count++;
            long nowWidth = minHeight * count;
            if(nowWidth > max) {
                max = nowWidth;
            }
        }
        while(l >= start) {
            count++;
            minHeight = Math.min(minHeight, bars[l]);
            long nowWidth = minHeight * count;
            if(nowWidth > max) {
                max = nowWidth;
            }
            l--;
        }
        while(r <= end) {
            count++;
            minHeight = Math.min(minHeight, bars[r]);
            long nowWidth = minHeight * count;
            if(nowWidth > max) {
                max = nowWidth;
            }
            r++;
        }

        return max;
    }

}
