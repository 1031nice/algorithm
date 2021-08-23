package contest.ICPCPreliminary2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class I {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numOfTeams = scanner.nextInt();

        ArrayList<Integer> w = new ArrayList<>();

        // input
        for(int i=0; i<numOfTeams*2; i++){
            w.add(scanner.nextInt());
        }

        Collections.sort(w);

        int min = Integer.MAX_VALUE;
        int temp = 0;

        for(int i=0; i<numOfTeams; i++){
            temp = w.get(i) + w.get(numOfTeams*2-i-1);
            if(min > temp)
                min = temp;
        }

        System.out.println(min);

    }

}
