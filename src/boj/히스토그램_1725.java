package boj;

import java.io.*;

public class 히스토그램_1725 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] bars = new int[n];
        for(int i=0; i<n; i++) {
            bars[i] = Integer.parseInt(br.readLine());
        }
        bw.write(func(bars, 0, bars.length-1)+"\n");
        bw.flush();
    }

    private static int func(int[] bars, int start, int end) {
        if(start == end)
            return bars[start];

        int mid = (start + end) / 2;
        int max = Math.max(func(bars, start, mid), func(bars, mid+1, end));
        int minHeight = Math.min(bars[mid], bars[mid+1]);
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
            int nowWidth = minHeight * count;
            if(nowWidth > max) {
                max = nowWidth;
            }
        }
        while(l >= start) {
            count++;
            minHeight = Math.min(minHeight, bars[l]);
            int nowWidth = minHeight * count;
            if(nowWidth > max) {
                max = nowWidth;
            }
            l--;
        }
        while(r <= end) {
            count++;
            minHeight = Math.min(minHeight, bars[r]);
            int nowWidth = minHeight * count;
            if(nowWidth > max) {
                max = nowWidth;
            }
            r++;
        }

        return max;
    }

}
