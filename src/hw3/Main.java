package hw3;

import java.util.Scanner;

public class Main extends MergeSort {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();

            int menuNumber = sc.nextInt(); //a는 정렬이냐 종료이냐

            System.out.println("\nSelect a sorting algorithm");
            System.out.println("1. Bubble sort\n2. Merge sort"); //Bubble인지 Merge인지 선택
            System.out.print("> ");
            int sortNumber = sc.nextInt();

            System.out.println("\n1. Ascending order\n2. Descending order"); //오름차순인지 내림차순인지 선택
            System.out.print("> ");
            int sortTypeNumber = sc.nextInt(); //오름이냐 내림이냐

            if (menuNumber == 1) { // 숫자 정렬
                sortNumbers(sortNumber, sortTypeNumber);
            } else if (menuNumber == 2) { // 문자열 정렬
                sortStrings(sortNumber, sortTypeNumber);
            } else if (menuNumber == 3) {
                break;
            }

        }
    }

    private static void sortStrings(int sortNumber, int sortTypeNumber) {

    }

    private static void sortNumbers(int sortNumber, int sortTypeNumber) {
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
                System.out.println("Number Bubble Ascend");
                bubbleSortAsc(nums);
                System.out.println("<Results>");
                printArray(nums);
            } else if (sortTypeNumber == 2) //Descend
            {
                System.out.println("Number Bubble Descend");
                bubbleSortDesc(nums);
                System.out.println("<Results>");
                printArray(nums);
            }
        } else if (sortNumber == 2) { //Merge
            if (sortTypeNumber == 1) {
                System.out.println("Number Merge Ascend");
                mergeSort(nums, size);
                System.out.println("<Results>");
                printArray(nums);
            } else if (sortTypeNumber == 2) {
                System.out.println("Number Merge Descend");
                mergeSort(nums, size);
                System.out.println("<Results>");
                printArray(nums);
            }
        }
    }

    private static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void printMenu() {
        System.out.println("[ ID : 2010248 ]");
        System.out.println("[ Name : Seoyeon Kim ]\n");
        System.out.println("1. Sort numbers");
        System.out.println("2. Sort words");
        System.out.println("3. Quit");
        System.out.print("> ");
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
}

