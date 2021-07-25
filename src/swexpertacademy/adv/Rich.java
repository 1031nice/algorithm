package swexpertacademy;

import java.util.Scanner;

// D2
public class Rich {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int test=0; test<testCases; test++) {
            int size = scanner.nextInt();
            int[] numbers = new int[size];
            for (int i=0; i<size; i++) {
                numbers[i] = scanner.nextInt();
            }
            System.out.println("#" + (test+1) + " " + sum(numbers, 0));
        }
    }

    public static long sum(int[] numbers, int index) {
        if (index >= numbers.length || index < 0)
            return 0;

        long buy = 0;
        int count = 0;
        // 값이 꺾일 때까지 계속 사
        while(index < numbers.length-1 && numbers[index+1] > numbers[index]) {
            buy += numbers[index++];
            ++count;
        }

        // 꺾인 뒤에서 최댓값을 찾아
        int max = 0;
        int maxIndex = 0;
        for(int i=index; i<numbers.length; i++) {
            if(numbers[i] > max) {
                max = numbers[i];
                maxIndex = i;
            }
        }

        // 꺾인 뒤에서의 최댓값이 지금의 최댓값보다 크다면 그 값 전까지 다 사고 그 값에 팔고 그 이후는 재귀호출
        if(max > numbers[index]) {
            for (int i=index; i<maxIndex; i++) {
                buy += numbers[i];
                ++count;
            }
            return (long)numbers[maxIndex] * count - buy + sum(numbers, maxIndex+1);
        }
        // 지금의 최댓값이 제일 크다면 지금 팔고 그 이후는 재귀호출
        else {
            return (long)numbers[index] * count - buy + sum(numbers, index+1);
        }

    }

}
