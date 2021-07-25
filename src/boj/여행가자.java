package boj;

import java.io.IOException;
import java.util.Scanner;

public class 여행가자 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int numOfNodes = scanner.nextInt();
        int travel = scanner.nextInt();;
        int[] parents = new int[numOfNodes];
        for(int i=0; i<parents.length; i++)
            parents[i] = -1;
        for(int i=0; i<parents.length; i++) {
            for (int j = 0; j < parents.length; j++) {
                int exist = scanner.nextInt();
                if(exist == 1) {
                    int p1 = find(parents, i);
                    int p2 = find(parents, j);
                    if (p1 == p2)
                        continue;
                    else if(parents[p1] < parents[p2]) {
                        parents[p1] += parents[p2];
                        parents[p2] = p1;
                    }
                    else {
                        parents[p2] += parents[p1];
                        parents[p1] = p2;
                    }
                }
            }
        }
        int[] travelPlan = new int[travel];
        for(int i=0; i<travel; i++) {
            travelPlan[i] = scanner.nextInt()-1;
        }
        for(int i=0; i<travel-1; i++) {
            if(find(parents, travelPlan[i]) != find(parents, travelPlan[i+1])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static int find(int[] parents, int node) {
        while(parents[node] > 0) {
            node = parents[node];
        }
        return node;
    }
}
