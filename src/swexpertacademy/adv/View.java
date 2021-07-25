package swexpertacademy;

import java.util.Scanner;

public class View {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int test=0; test<10; test++) {
            int numOfBuildings = scanner.nextInt();
            int[] buildings = new int[numOfBuildings];
            for(int i=0; i<buildings.length; i++) {
                buildings[i] = scanner.nextInt();
            }

            int sum = 0;

            for(int i=2; i<buildings.length-2; i++) {
                if(buildings[i+1] < buildings[i]) { // 꺾이면
                    int left = Math.max(buildings[i-1], buildings[i-2]);
                    int right = Math.max(buildings[i+1], buildings[i+2]);
                    int max = Math.max(left, right);
                    int diff = buildings[i] - max;
                    if(diff > 0)
                        sum += diff;
                }
            }

            System.out.println("#" + (test+1) + " " + sum);
        }


    }

}
