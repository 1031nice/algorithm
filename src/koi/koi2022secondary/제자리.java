package koi.koi2022secondary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 제자리 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(bufferedReader.readLine());
        String[] split = bufferedReader.readLine().split(" ");
        int start = 1;
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            int number = Integer.parseInt(split[i]);
            if (number == start) {
                start++;
            } else {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
