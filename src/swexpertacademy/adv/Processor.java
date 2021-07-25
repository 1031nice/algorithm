package swexpertacademy;

import java.util.Scanner;

class Result {
    int numOfCores;
    int cost;
    public Result(int numOfCores, int cost) {
        this.numOfCores = numOfCores;
        this.cost = cost;
    }
    // 크기 비교가 아니라 우선순위 비교
    int value(Result other) {
        if(this.numOfCores > other.numOfCores) // 코어가 많으면 무조건 우선
            return 1;
        else if(this.numOfCores < other.numOfCores)
            return -1;
        else { // 코어가 같을 때
            return Integer.compare(other.cost, this.cost); // 비용이 작으면 우선
        }
    }
}

public class Processor {
    static int[] dirX = {0, 1, 0, -1}; // 위부터 시계방향
    static int[] dirY = {-1, 0, 1, 0};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();
        int[][] core = new int[12][2];
        int coreIndex = 0;
        for(int i=0; i<testCase; i++){
            int boardSize = scanner.nextInt();
            int[][] board = new int [boardSize][boardSize];
            for(int j=0; j<boardSize; j++) {
                for(int k=0; k<boardSize; k++) {
                    if((board[j][k] = scanner.nextInt()) > 0) {
                        if(j == 0 || k == 0 || j == boardSize-1 || k == boardSize-1)
                            continue;
                        core[coreIndex][0] = j;
                        core[coreIndex][1] = k;
                        coreIndex++;
                    }
                }
            }
            System.out.println("#" + (i + 1) + " " + func(board, core, 0, coreIndex).cost);
            coreIndex = 0;
        }
    }

    private static Result func(int[][] board, int[][] core, int coreIndex, int coreSize) {
        Result min = new Result(0, 0);
        Result temp;
        if (coreIndex >= coreSize)
            return min;
        int row = core[coreIndex][0];
        int col = core[coreIndex][1];
        for (int dir = 0; dir < 4; dir++) {
            int cost;
            if ((cost = connect(board, row, col, dir)) >= 0) {
                temp = func(board, core, coreIndex + 1, coreSize);
                temp.cost = temp.cost + cost;
                temp.numOfCores = temp.numOfCores + 1 ;
                unconnect(board, row, col, dir, cost);
                if (min.value(temp) < 0)
                    min = temp;
            }
        }
        temp = func(board, core, coreIndex + 1, coreSize);
        if (min.value(temp) < 0)
            min = temp;
        return min;
    }

    private static void unconnect(int[][] board, int row, int col, int dir, int cost) {
        int moveY = dirY[dir];
        int moveX = dirX[dir];
        // unconnect는 connect 뒤에 진행되므로 별도 검사 없이 다시 0으로 바꿔주면 된다
        for(int i=0; i<cost; i++) {
            row += moveY;
            col += moveX;
            board[row][col] = 0;
        }
    }

    // 그 방향으로 전선 놓을 수 있으면 그 방향으로 전선 -1로 깔고 true
    // 전선 놓을 수 없으면 false 리턴
    private static int connect(int[][] board, int row, int col, int dir) {
        int cost = 0;
        int moveY = dirY[dir];
        int moveX = dirX[dir];
        int y = row + moveY;
        int x = col + moveX;
        // 해당 방향으로 전선을 연결할 수 있는지 확인
        while(!(y < 0 || x < 0 || y >= board.length || x >= board.length)) {
            if(board[y][x] != 0)
                return -1;
            y += moveY;
            x += moveX;
            ++cost;
        }

        // 여기까지 도착했다는 것은 연결 가능하다는 뜻, 검사 필요 없이 진행
        for(int i=0; i<cost; i++) {
            row += moveY;
            col += moveX;
            board[row][col] = -12;
        }
        return cost;
    }

}
