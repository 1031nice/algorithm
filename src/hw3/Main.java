package hw3;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();

            int menuNumber = sc.nextInt(); //a는 정렬이냐 종료이냐
            if (menuNumber == 3) {
                break;
            }

            System.out.println("\nSelect a sorting algorithm");
            System.out.println("1. Bubble sort\n2. Merge sort"); //Bubble인지 Merge인지 선택
            System.out.print("> ");
            int sortNumber = sc.nextInt();

            System.out.println("\n1. Ascending order\n2. Descending order"); //오름차순인지 내림차순인지 선택
            System.out.print("> ");
            int sortTypeNumber = sc.nextInt(); //오름이냐 내림이냐

            if (menuNumber == 1) { // 숫자 정렬
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
                IntegerArraySort.sortNumbers(nums, sortNumber, sortTypeNumber);
            } else if (menuNumber == 2) { // 문자열 정렬
                System.out.println("\nThe number of words to be sorted");
                System.out.print("> ");
                int size = sc.nextInt();
                String[] words = new String[size]; // 정렬할 문자열이 들어갈 배열

                // 문자열 입력받기
                System.out.println("\nThe numbers to be sorted");
                System.out.print("> ");
                for (int i = 0; i < size; i++) {
                    words[i] = sc.next();
                }
                StringArraySort.sortStrings(words, sortNumber, sortTypeNumber);
            }

        }
    }

    private static void printMenu() {
        System.out.println("[ ID : 2010248 ]");
        System.out.println("[ Name : Seoyeon Kim ]\n");
        System.out.println("1. Sort numbers");
        System.out.println("2. Sort words");
        System.out.println("3. Quit");
        System.out.print("> ");
    }

}

