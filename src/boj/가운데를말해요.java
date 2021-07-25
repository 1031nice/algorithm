package boj;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 가운데를말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
        int nums = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < nums; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());

            // 두 큐의 크기는 최대 1 차이가 나도록 유지해야 한다
            // maxQ가 1 크도록 설정
            if (minQ.size() == maxQ.size())
                maxQ.add(num);
            else
                minQ.add(num);
            // 만약 maxQ의 최댓값과 minQ의 최솟값이 차이가 난다면
            if (!minQ.isEmpty() && minQ.peek() < maxQ.peek()) {
                // swap
                maxQ.add(minQ.poll());
                minQ.add(maxQ.poll());
            }
            bufferedWriter.write(String.valueOf(maxQ.peek()));
            bufferedWriter.write("\n");
        }
        bufferedWriter.flush();
    }
}