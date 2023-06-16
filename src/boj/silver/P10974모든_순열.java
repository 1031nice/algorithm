package boj.silver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class P10974모든_순열 {

    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] numbers = new int[N];

        select(numbers, new boolean[N], 0);

        bufferedWriter.flush();
    }

    private static void select(int[] numbers, boolean[] visited, int index) throws IOException {
        if (index == numbers.length) {
            printArray(numbers);
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            numbers[index] = i + 1;
            select(numbers, visited, index + 1);
            visited[i] = false;
        }
    }

    private static void printArray(int[] numbers) throws IOException {
        for (int i = 0; i < numbers.length; i++) {
            bufferedWriter.write(numbers[i] + " ");
        }
        bufferedWriter.write("\n");
    }

}
