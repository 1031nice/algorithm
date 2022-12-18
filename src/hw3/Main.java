package hw3;

import java.util.Scanner;

public class Main extends MergeSort {

    public static void main(String[] args) {
        while (true) {
            System.out.println("[ ID : 2010248 ]");
            System.out.println("[ Name : Seoyeon Kim ]\n");

            System.out.println("1. Sort numbers");
            System.out.println("2. Sort words");
            System.out.println("3. Quit");
            System.out.print("> ");

            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt(); //a는 정렬이냐 종료이냐
            //System.out.println(a); //a에 숫자가 저장되어 있는 것

            int[] num; // 정렬할 숫자가 들어갈 배열

            if (a == 1) //
            {
                System.out.println("\nSelect a sorting algorithm");
                System.out.println("1. Bubble sort\n2. Merge sort"); //Bubble인지 Merge인지 선택합시다.
                System.out.print("> ");

                Scanner sc2 = new Scanner(System.in);
                int b = sc2.nextInt();
                //System.out.println(b); //Bubble이냐 Merge냐

                System.out.println("\n1. Ascending order\n2. Descending order"); //Bubble인지 Merge인지 선택합시다.
                System.out.print("> ");

                Scanner sc3 = new Scanner(System.in);
                int c = sc3.nextInt(); //오름이냐 내림이냐
                //System.out.println(c);

                if (b == 1) { //Bubble

                    int count = 0;                    // 입력받을 숫자의 개수
                    int numMax = 0;                    // 최대값
                    int search;                        // 검색할 숫자
                    Boolean searchCheck = false;    // 검색한 숫자 체크

                    Scanner scanner = new Scanner(System.in); // 입력받을 숫자 개수 입력받기
                    System.out.println("\nThe number of numbers to be sorted");
                    System.out.print("> ");
                    count = scanner.nextInt();
                    num = new int[count];

                    // 숫자 입력받기
                    System.out.println("\nThe numbers to be sorted");
                    System.out.print("> ");
                    for (int i = 0; i < count; i++) {
                        num[i] = scanner.nextInt();
                    }

                    if (c == 1) //Ascend
                    {
                        System.out.println("Number Bubble Ascend");

                        //여기가 진짜 버블 오름 정렬
                        int n = num.length;
                        int temp = 0;
                        for (int i = 0; i < n; i++) {
                            for (int j = 1; j < (n - i); j++) {
                                if (num[j - 1] > num[j]) {
                                    //swap elements
                                    temp = num[j - 1];
                                    num[j - 1] = num[j];
                                    num[j] = temp;
                                }
                            }
                        }
                        //정렬 후 출력
                        System.out.print("\n<Results>\n");
                        System.out.print("정렬 후 Array는 ");
                        for (int k = 0; k < num.length; k++) {
                            System.out.print(num[k] + " ");
                        }
                        System.out.println("입니다.\n");
                    } else if (c == 2) //Descend
                    {
                        // System.out.println("Number Bubble Descend");

                        int n = num.length;
                        int temp = 0;
                        for (int i = 0; i < n; i++) {
                            for (int j = 1; j < (n - i); j++) {
                                if (num[j - 1] > num[j]) {
                                    //swap elements
                                    temp = num[j - 1];
                                    num[j - 1] = num[j];
                                    num[j] = temp;
                                }
                            }
                        }
                        //정렬 후 출력
                        System.out.print("\n<Results>\n");
                        System.out.print("정렬 후 Array는 ");
                        for (int k = num.length - 1; k >= 0; k--) {

                            System.out.print(num[k] + " ");

                        }
                        System.out.println("입니다.\n");

                    }
                } else if (b == 2) { //Merge

                    int count = 0;                    // 입력받을 숫자의 개수
                    int numMax = 0;                    // 최대값
                    int search;                        // 검색할 숫자
                    Boolean searchCheck = false;    // 검색한 숫자 체크

                    Scanner scanner = new Scanner(System.in); // 입력받을 숫자 개수 입력받기
                    System.out.println("\nThe number of numbers to be sorted");
                    count = scanner.nextInt();
                    num = new int[count];

                    // 숫자 입력받기
                    System.out.print(count + "\nnumbers to be sorted\n> ");
                    for (int i = 0; i < count; i++) {
                        num[i] = scanner.nextInt();
                    }

                    if (c == 1) {
                        //System.out.println("Number Merge Ascend");

                        int left = 0;
                        int right = count - 1;

                        mergeSort(num, count);

                        //정렬 후 출력
                        System.out.print("\n<Results>\n");
                        System.out.print("정렬 후 Array는 ");
                        for (int j = 0; j < num.length; j++) {
                            System.out.print(num[j] + " ");
                        }
                        System.out.println("입니다.\n");

                    } else if (c == 2) {
                        //System.out.println("Number Merge Descend");

                        int left = 0;
                        int right = count - 1;

                        mergeSort(num, count);

                        //정렬 후 출력
                        System.out.print("\n<Results>\n");
                        System.out.print("정렬 후 Array는 ");
                        for (int j = num.length - 1; j >= 0; j--) {
                            System.out.print(num[j] + " ");
                        }
                        System.out.println("입니다.\n");

                    }

                }
            } else if (a == 2) {
                System.out.println("\nSelect a sorting algorithm");
                System.out.println("1.Bubble Sort\n2.Merge Sort");
                System.out.print("> ");

                Scanner sc2 = new Scanner(System.in);
                int b = sc2.nextInt(); //Bubble이냐 Merge냐

                if (b == 1) {
                    System.out.println("\nSelect the sort order");

                    System.out.println("1. Ascending order\n2. Descending order"); //Bubble인지 Merge인지 선택합시다.
                    System.out.print("> ");

                    Scanner sc3 = new Scanner(System.in);
                    int c = sc3.nextInt(); //오름이냐 내림이냐
                    //System.out.println(c);

                    if (c == 1) {
                        System.out.println("(Words Bubble Ascend) ");
                        System.out.println("The words to be sorted\n");

                    } else if (c == 2) {
                        System.out.println("(Words Bubble Descend) ");
                        System.out.println("The words to be sorted\n");
                    }
                }
            }

        }
    }
}

