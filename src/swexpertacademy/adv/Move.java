package swexpertacademy;

import java.util.Scanner;

public class Move {

    static class Point {
        int row;
        int col;
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public boolean outOfBounds(int rowSize, int colSize) {
            return !(row < rowSize && col < colSize && row >= 0 && col >= 0);
        }
        public boolean equals(Point other) {
            return this.row == other.row && this.col == other.col;
        }
    }

    static class Pipe {
        Point point1; // 왼쪽 또는 위에 있는 점
        Point point2; // 오른쪽 또는 아래에 있는 점
        int type; // 0 가로 1 세로 2 대각선
        public Pipe(Point point1, Point point2, int type) {
            this.point1 = point1;
            this.point2 = point2;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[][] house = new int[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                house[i][j] = scanner.nextInt();
            }
        }
        Pipe start = new Pipe(new Point(0, 0), new Point(0, 1), 0);
        System.out.println(solve(house, start));
    }

    private static int solve(int[][] house, Pipe pipe) {
        // 파이프가 집 밖을 나가는지 검사
        if(pipe.point1.outOfBounds(house.length, house.length) ||
                pipe.point2.outOfBounds(house.length, house.length))
            return 0;
        // 파이프가 이미 방문한 위치에 있다면 검사하지 않는다
        // 근데 또 검사한다고 해서 답이 달라지진 않는다 계산의 중복이 있느냐 없느냐 그 차이다
        // 항상 오른쪽 또는 아래로 이동하기 때문에 결국 끝에 도달하기 때문이다 무한으로 반복되지 않는다
        // 근데 다시 왔던 방향으로 갈 수 있다면 이미 방문했던 곳을 표시해서 다시 못가도록 해야한다
        // 또한 '아기상어' 문제에서처럼 이미 방문한 곳을 다시 방문하면 답이 틀릴 수 있는 문제도 있다
        // 그냥 그렇다고
        // 아 근데 여기는 visited 생각하기가 어렵네 그냥 단순히 house와 같은 배열을 만들어서 1로 표시하면
        // 그게 어떤 파이프에 의해 1이 되었는지 모르기 때문에 굳이 visited를 만들거면
        // Pipe 자체를 담아야겠네유 근데 Pipe가 많아지면 또 이걸 다 순회하는데 시간걸리니까 캐싱도 의미없겠슈
        // visited 신경쓰지 맙시다
        if(pipe.type == 2) {
            if(house[pipe.point2.row][pipe.point2.col] != 0 ||
                    house[pipe.point2.row-1][pipe.point2.col] != 0 ||
                    house[pipe.point2.row][pipe.point2.col-1] != 0) // 대각선은 두 번째 포인트와 그 위 그 왼쪽 총 세 지점을 확인해야지
            return 0;
        }
        else if(house[pipe.point2.row][pipe.point2.col] != 0) // 가로 또는 세로로 되어 있으면 두 번째 포인트 한 지점만 확인해도 된다
            return 0;
        // 파이프의 오른쪽 또는 아래의 점이 끝에 도달했다면 한 가지 경우를 찾았으므로 return 1
        if(pipe.point2.equals(new Point(house.length-1, house.length-1)))
            return 1;

        if(pipe.type == 0) { // 가로
            return solve(house, new Pipe(pipe.point2, new Point(pipe.point2.row, pipe.point2.col + 1), 0)) // 오른쪽으로 밀기
                    + solve(house, new Pipe(pipe.point2, new Point(pipe.point2.row + 1, pipe.point2.col + 1), 2)); // 대각선으로 밀기기
        }
        else if(pipe.type == 1) { // 세로
            return solve(house, new Pipe(pipe.point2, new Point(pipe.point2.row + 1, pipe.point2.col), 1)) // 아래로 밀기
                    + solve(house, new Pipe(pipe.point2, new Point(pipe.point2.row + 1, pipe.point2.col + 1), 2)); // 대각선으로 밀기기
        }
        else {
            return solve(house, new Pipe(pipe.point2, new Point(pipe.point2.row, pipe.point2.col + 1), 0)) // 오른쪽으로 밀기
                    + solve(house, new Pipe(pipe.point2, new Point(pipe.point2.row + 1, pipe.point2.col + 1), 2)) // 대각선으로 밀기기
                    + solve(house, new Pipe(pipe.point2, new Point(pipe.point2.row + 1, pipe.point2.col), 1)); // 아래로 밀기
        }
    }

}
