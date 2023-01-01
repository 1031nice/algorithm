package apss;

/*
2
6 3
1 2 3 1 2 3
6 2
1 2 3 1 2 3
 */

import java.util.Scanner;

public class FESTIVAL {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfTestCases = scanner.nextInt();
        for(int i=0; i<numOfTestCases; i++) {
            // input
            int numOfDays = scanner.nextInt();
            int numOfTeams = scanner.nextInt();
            int[] costs = new int[numOfDays];
            for(int j=0; j<numOfDays; j++){
                costs[j] = scanner.nextInt();
            }

            // algorithm
            double min = Double.MAX_VALUE;
            for(int j=0; j<numOfDays-numOfTeams+1; j++){ // j: start point
                double sum = 0;
                int count = 0;
                for(int k=j; k<numOfDays; k++){ // k: end point && iterator
                    sum += costs[k];
                    count++;
                    if(count >= numOfTeams){
                        double avg = sum / count;
                        if(min > avg) min = avg;
                    }
                }
            }

            // output
            System.out.println("min: " + min);
        }
    }

}
