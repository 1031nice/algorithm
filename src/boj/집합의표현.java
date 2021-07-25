package boj;

import java.io.*;
import java.util.Scanner;

// union-find
// 100%까지 뜨고 틀리는데 왜그런지 모르겠음
public class 집합의표현 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int numOfNodes = scanner.nextInt();
        int[] parents = new int[numOfNodes+1];
        for(int i=0; i<parents.length; i++)
            parents[i] = -1;
        int numOfOperations = scanner.nextInt();
        for(int i=0; i<numOfOperations; i++) {
            int op = scanner.nextInt();
            int p1 = find(parents, scanner.nextInt());
            int p2 = find(parents, scanner.nextInt());
            // check
            if(op == 1)
                System.out.println(p1 == p2 ? "YES" : "NO");

            // union
            else {
                if(p1 == p2)
                    continue;
                // 크기는 음수이므로 작은 게 더 크다
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

    private static int find(int[] parents, int node) {
        while(parents[node] > 0) {
            node = parents[node];
        }
        return node;
    }
}
