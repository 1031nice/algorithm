package boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2470두_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] numbers = new int[N];
        String[] split = reader.readLine().split(" ");
        for (int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(split[i]);
        }

        Arrays.sort(numbers);

        int[] pair = func(numbers, 0, numbers.length-1);
        System.out.println(pair[0] + " " + pair[1]);
    }

    private static int[] func(int[] numbers, int start, int end) {
        if (start > end) {
            return null;
        } else if (end - start + 1 < 2) {
            return null;
        }

        int mid = start + (end - start) / 2;

        int toRight = start;
        int toLeft = end;
        int minSum = 2_000_000_000;
        int[] ret = new int[2];
        while (mid >= toRight && toLeft > mid) {
            int sum = numbers[toLeft] + numbers[toRight];
            if (Math.abs(minSum) > Math.abs(sum)) {
                minSum = sum;
                ret[0] = numbers[toRight];
                ret[1] = numbers[toLeft];
            }

            if (sum > 0) {
                toLeft--;
            } else {
                toRight++;
            }
        }

        int[] leftHalf = func(numbers, start, mid);
        if (leftHalf != null) {
            int leftMinSum = leftHalf[0] + leftHalf[1];
            if (Math.abs(minSum) > Math.abs(leftMinSum)) {
                minSum = leftMinSum;
                ret = leftHalf;
            }
        }

        int[] rightHalf = func(numbers, mid+1, end);
        if (rightHalf != null) {
            int rightMinSum = rightHalf[0] + rightHalf[1];
            if (Math.abs(minSum) > Math.abs(rightMinSum)) {
                minSum = rightMinSum;
                ret = rightHalf;
            }
        }

        return ret;
    }
}