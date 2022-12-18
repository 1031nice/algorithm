package hw3;

import java.util.Scanner;

public class StringArraySort {

    static Scanner sc = new Scanner(System.in);

    public int compareStrings(String word1, String word2) {
        for (int i = 0; i < Math.min(word1.length(), word2.length()); i++) {
            if ((int) word1.charAt(i) != (int) word2.charAt(i))//comparing unicode values
            {
                return (int) word1.charAt(i) - (int) word2.charAt(i);
            }
        }

        if (word1.length() != word2.length())//smaller word is occurs at the beginning of the larger word
        {
            return word1.length() - word2.length();
        } else {
            return 0;
        }
    }

    public StringArraySort(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (compareStrings(words[i], words[j]) > 0)//words[i] is greater than words[j]
                {
                    String temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }

        return;
    }

    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;
        count = sc.nextInt();

        System.out.println("The number of words to be sorted");
        System.out.print("> ");

        String[] words = new String[count];
        int size = words.length;

        //2.입력받기
        Scanner put = new Scanner(System.in);

        //3.입력된 이름을 배열에 순서에 맞게 저장
        System.out.print(count + "개의 단어를 입력하세요 : ");
        for (int i = 0; i < size; i++) {
            words[i] = put.next();
        }

        //4.화면에 입력된 이름을 표시한다.
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + "번째 : " + words[i] + " / ");

            //String[] arrToSort = {"apple", "oranges", "bananas", "Strawberry", "Blueberry"};

            for (int j = 0; j < count; j++) {
                // arrToSort[i] = sc.nextStr();
            }

//            String[] sortedArr = StringArraySort(words);

//            for (int k = 0; k < sortedArr.length; k++) {
//                System.out.print(sortedArr[i] + " ");
//            }
        }

        put.close();
        sc.close();

    }

    private String[] stringArraySort(String[] words) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void sortStrings(int sortNumber, int sortTypeNumber) {
        System.out.println("\nThe number of words to be sorted");
        System.out.print("> ");
        int size = sc.nextInt();
        String[] strings = new String[size]; // 정렬할 문자열이 들어갈 배열

        // 문자열 입력받기
        System.out.println("\nThe numbers to be sorted");
        System.out.print("> ");
        for (int i = 0; i < size; i++) {
            strings[i] = sc.next();
        }

        if (sortNumber == 1) { //Bubble
            if (sortTypeNumber == 1) //Ascend
            {
            } else if (sortTypeNumber == 2) //Descend
            {
            }
        } else if (sortNumber == 2) { //Merge
            if (sortTypeNumber == 1) {

            } else if (sortTypeNumber == 2) {

            }
        }
    }

}
