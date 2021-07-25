package boj;

import java.util.Scanner;

public class 벌집 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int count = 1;
        if(number == 1) {
            System.out.println(1);
            return;
        }
        int now = 1;
        while(true) {
            now += 6 * count++;
            if(now >= number) {
                System.out.println(count);
                break;
            }
        }
    }

}
