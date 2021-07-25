package boj;

import java.io.*;

// 값의 변동이 없으므로 누적합을 저장하는 배열만 있으면 문제를 풀 수 있다

public class 구간합구하기4_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = br.readLine().split(" ");
        int size = Integer.parseInt(s[0]);
        int[] accArr = new int[size+1];
        int numOfQuery = Integer.parseInt(s[1]);
        s = br.readLine().split(" ");
        for(int i=1; i<=size; i++) {
            if(i == 1)
                accArr[i] = Integer.parseInt(s[i-1]);
            else
                accArr[i] = accArr[i-1] + Integer.parseInt(s[i-1]);
        }
        for(int i=0; i<numOfQuery; i++) {
            s = br.readLine().split(" ");
            int result = accArr[Integer.parseInt(s[1])] - accArr[Integer.parseInt(s[0]) - 1];
            bw.write(result + "\n");
        }
        bw.flush();
    }
}
