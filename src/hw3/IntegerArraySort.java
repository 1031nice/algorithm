package hw3;

import java.util.Scanner;

public class IntegerArraySort {

    static Scanner sc = new Scanner(System.in);

    public static void sortNumbers(int sortNumber, int sortTypeNumber) {
        System.out.println("\nThe number of numbers to be sorted");
        System.out.print("> ");
        int size = sc.nextInt();
        int[] nums = new int[size]; // 정렬할 숫자가 들어갈 배열

        // 숫자 입력받기
        System.out.println("\nThe numbers to be sorted");
        System.out.print("> ");
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }

        if (sortNumber == 1) { //Bubble
            if (sortTypeNumber == 1) //Ascend
            {
                bubbleSortAsc(nums);
                System.out.println("<Results>");
                printArray(nums);
            } else if (sortTypeNumber == 2) //Descend
            {
                bubbleSortDesc(nums);
                System.out.println("<Results>");
                printArray(nums);
            }
        } else if (sortNumber == 2) { //Merge
            if (sortTypeNumber == 1) {
                mergeSort(nums, size);
                System.out.println("<Results>");
                printArray(nums);
            } else if (sortTypeNumber == 2) {
                mergeSort(nums, size);
                System.out.println("<Results>");
                printArray(nums);
            }
        }
    }

    private static void bubbleSortAsc(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < (nums.length - i); j++) {
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    private static void bubbleSortDesc(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < (nums.length - i); j++) {
                if (nums[j - 1] < nums[j]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static int[] buff;    // 작업용 배열

    //--- a[left] ~ a[right]를 재귀적으로 병합정렬 ---//
    static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int i;
            int center = (left + right) / 2;
            int p = 0;
            int j = 0;
            int k = left;

            mergeSort(a, left, center);         // 전반부를 병합정렬
            mergeSort(a, center + 1, right);    // 후반부를 병합정렬

            for (i = left; i <= center; i++) {
                buff[p++] = a[i];
            }

            while (i <= right && j < p) {
                a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
            }

            while (j < p) {
                a[k++] = buff[j++];
            }
        }
    }

    //--- 병합 정렬 ---//
    static void mergeSort(int[] a, int n) {
        buff = new int[n];                    // 작업용 배열을 생성

        mergeSort(a, 0, n - 1);            // 배열 전체를 병합 정렬

        buff = null;                         // 작업용 배열을 해제
    }

    private static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}