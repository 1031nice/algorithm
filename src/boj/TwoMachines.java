package boj;

import java.util.Scanner;

public class TwoMachines {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tasks = scanner.nextInt();
        int before1 = 0, before2 = 0;
        int total1 = 0, total2 = 0;
        for(int i=0; i<tasks; i++) {
            int t1 = scanner.nextInt();
            int t2 = scanner.nextInt();
            if(before1 + t1 < before2 + t2) {
                total1 += t1;
                before1 = t1;
                before2 -= t1;
                if(before2 < 0) before2 = 0;
            }
            else {
                total2 += t2;
                before2 = t2;
                before1 -= t2;
                if(before1 < 0) before1 = 0;
            }
        }
        System.out.println(Math.max(total1, total2));
    }

}
