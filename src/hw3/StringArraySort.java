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
            } else if (sortTypeNumber == 2) { //Descend
                bubbleSortDesc(words);
            }
        } else if (sortNumber == 2) { //Merge
            if (sortTypeNumber == 1) {
                mergeSortAsc(words);
            } else if (sortTypeNumber == 2) {
                mergeSortDesc(words);
            }
        }
    }

    private static void mergeSortDesc(String[] words) {

    }

    private static void mergeSortAsc(String[] words) {

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

}
