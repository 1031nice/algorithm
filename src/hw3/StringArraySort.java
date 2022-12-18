package hw3;

import java.util.Scanner;

public class StringArraySort {

    static Scanner sc = new Scanner(System.in);

    public static void sortStrings(int sortNumber, int sortTypeNumber) {
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

        if (sortNumber == 1) { //Bubble
            if (sortTypeNumber == 1) { //Ascend
                bubbleSortAsc(words);
                System.out.println("<Results>");
                printArray(words);
            } else if (sortTypeNumber == 2) { //Descend
                bubbleSortDesc(words);
                System.out.println("<Results>");
                printArray(words);
            }
        } else if (sortNumber == 2) { //Merge
            if (sortTypeNumber == 1) {
                mergeSort(words, size, true);
                System.out.println("<Results>");
                printArray(words);
            } else if (sortTypeNumber == 2) {
                mergeSort(words, size, false);
                System.out.println("<Results>");
                printArray(words);
            }
        }
    }

    static String[] buff;    // 작업용 배열

    //--- a[left] ~ a[right]를 재귀적으로 병합정렬 ---//
    static void mergeSortAsc(String[] a, int left, int right) {
        if (left < right) {
            int i;
            int center = (left + right) / 2;
            int p = 0;
            int j = 0;
            int k = left;

            mergeSortAsc(a, left, center);         // 전반부를 병합정렬
            mergeSortAsc(a, center + 1, right);    // 후반부를 병합정렬

            for (i = left; i <= center; i++) {
                buff[p++] = a[i];
            }

            while (i <= right && j < p) {
                a[k++] = (buff[j].compareTo(a[i]) <= 0) ? buff[j++] : a[i++];
            }

            while (j < p) {
                a[k++] = buff[j++];
            }
        }
    }

    static void mergeSortDesc(String[] a, int left, int right) {
        if (left < right) {
            int i;
            int center = (left + right) / 2;
            int p = 0;
            int j = 0;
            int k = left;

            mergeSortDesc(a, left, center);         // 전반부를 병합정렬
            mergeSortDesc(a, center + 1, right);    // 후반부를 병합정렬

            for (i = left; i <= center; i++) {
                buff[p++] = a[i];
            }

            while (i <= right && j < p) {
                a[k++] = (buff[j].compareTo(a[i]) > 0) ? buff[j++] : a[i++];
            }

            while (j < p) {
                a[k++] = buff[j++];
            }
        }
    }

    //--- 병합 정렬 ---//
    static void mergeSort(String[] a, int n, boolean isAsc) {
        buff = new String[n];                    // 작업용 배열을 생성

        if (isAsc) {
            mergeSortAsc(a, 0, n - 1);            // 배열 전체를 병합 정렬
        } else {
            mergeSortDesc(a, 0, n - 1);
        }

        buff = null;                         // 작업용 배열을 해제
    }

    private static void bubbleSortAsc(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].compareTo(words[j]) > 0) //words[i] > words[j]
                {
                    swap(words, i, j);
                }
            }
        }
    }

    private static void bubbleSortDesc(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].compareTo(words[j]) < 0) //words[i] < words[j]
                {
                    swap(words, i, j);
                }
            }
        }
    }

    private static void swap(String[] strings, int i, int j) {
        String temp = strings[i];
        strings[i] = strings[j];
        strings[j] = temp;
    }

    private static void printArray(String[] strings) {
        for (String string : strings) {
            System.out.print(string + " ");
        }
        System.out.println();
    }

}
