package contest.ICPCPreliminary2020;

import java.util.Scanner;

public class E {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfDot = scanner.nextInt();
        int numOfLine = scanner.nextInt();

        int [] set = new int [numOfDot];
        int [] remember = new int [numOfLine*2];
        for(int i=0; i<set.length; i++){
            set[i] = i;
        }

        int min, max, node1, node2;
        int index = 0;

        for(int i=0; i<numOfLine; i++){
            node1 = scanner.nextInt();
            node2 = scanner.nextInt();
            remember[index++] = node1;
            remember[index++] = node2;
            if(set[node1] == set[node2]) {
                System.out.println(i + 1);
                return;
            }
            max = Math.max(set[node1], set[node2]);
            min = Math.min(set[node1], set[node2]);
            set[node1] = max;
            set[node2] = max;
            for(int j=0; j<i * 2; j++){
                if(set[remember[j]] == min) {
                    set[remember[j]] = max;
                }
            }
        }
        System.out.println(0);
    }

}
