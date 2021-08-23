package contest.ICPCPreliminary2020;

import java.util.Scanner;

/*
 다이나믹 프로그래밍이라고 생각해서
 이미 계산된 값을 자꾸 계산에 이용하려고 하다보니까 무한루프를 벗어나지 못했다.
 지금은 계산된 값을 계산에 이용하기 보다는 무한루프에 빠지지 않고, 쓸데없는 재귀를 돌지 않도록,
 확인하는 용도로 쓰고 있다. 이게 답인지 잘 모르겠지만 맞을 것 같다.
 그러니까 기존의 값을 꼭 계산에 항상 이용해야만 다이나믹프로그래밍이라는 고정관념을 버려야 한다.
 계산이 아니더라도 이미 계산된 값을 이용하면 그게 다이나믹프로그래밍 아닐까?
 */
public class G {

    static int [][] cache;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int [][] arr = new int [row][col];
        cache = new int [row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                cache[i][j] = -1;
            }
        }

        // input
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                arr[i][j] = scanner.nextInt();
            }
        }

        int min = func(arr, 0, 0, 0);
        System.out.println(min);

    }

    public static int func(int [][] arr, int row, int col, int cost){

        // base case1: index out of bounds
        if(row >= arr.length || col >= arr[0].length || row < 0 || col < 0)
            return -1;

        // base case2: 갈 수 없는 길
        else if(arr[row][col] == -1)
            return -1;

        // base case3: 이미 와본 길
        else if(cache[row][col] != -1 && cache[row][col] <= cost + arr[row][col]) // 더 빠르게 할 수 없으면
            return -1; // 가지마

        // base case4: 도착
        else if(row == arr.length-1 && col == arr[0].length-1)
            return cost + arr[row][col];

        // recursive case
        else {
            cache[row][col] = cost + arr[row][col];
            int min = 0;
            int down = func(arr, row + 1, col, cost + arr[row][col]);
            int right = func(arr, row, col + 1, cost + arr[row][col]);
            int up = func(arr, row - 1, col, cost + arr[row][col]);
            int left = func(arr, row, col - 1, cost + arr[row][col]);

            if (down == -1 && right == -1 && up == -1 && left == -1) // 갈 길이 없으면 -1을 리턴
                return -1;
            else if (down != -1 && right != -1 && up != -1 && left != -1) // 둘 모두 -1이 아니면 경로가 있는거니까 최솟값
                return Math.min(Math.min(Math.min(down, right), up), left);
            else { // -1인 경우 min에서 걸리지 않도록 모두 아주 큰 값을 할당
                if (down < 0)
                    down = Integer.MAX_VALUE;
                if (right < 0)
                    right = Integer.MAX_VALUE;
                if (up < 0)
                    up = Integer.MAX_VALUE;
                if (left < 0)
                    left = Integer.MAX_VALUE;
                min = Math.min(Math.min(Math.min(down, right), up), left);
                return min;
            }
        }
    }

}
