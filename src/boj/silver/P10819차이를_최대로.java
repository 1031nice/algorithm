package boj.silver;

import java.util.Scanner;

public class P10819차이를_최대로 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println(select(numbers, new boolean[N], new int[N], 0));
    }

    private static int select(int[] numbers, boolean[] visited, int[] newNumbers, int index) {
        int ret = 0;

        if (index == numbers.length) {
            return calculate(newNumbers);
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            newNumbers[index] = numbers[i];
            ret = Math.max(ret, select(numbers, visited, newNumbers, index + 1));
            visited[i] = false;
        }

        return ret;
    }

    public static int calculate(int[] numbers) {
        int sum = 0;

        for (int i = 0; i < numbers.length - 1; i++) {
            sum += Math.abs(numbers[i] - numbers[i + 1]);
        }

        return sum;
    }

}
