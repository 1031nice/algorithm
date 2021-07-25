package swexpertacademy;

import java.util.Scanner;

public class Hiking {

    static int[] dirX = new int[] {0, 1, 0, -1}; // 12시부터 시계 방향
    static int[] dirY = new int[] {-1, 0, 1, 0};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for(int test=0; test<testCases; test++){
            int mapSize = scanner.nextInt();
            int depth = scanner.nextInt();
            int[][] map = new int[mapSize][mapSize];
            int[][] highest = new int[5][2];
            int count = 0;
            int max = -1;
            for(int i=0; i<mapSize; i++) {
                for(int j=0; j<mapSize; j++) {
                    int temp = scanner.nextInt();
                    map[i][j] = temp;
                    if(temp > max) {
                        count = 0;
                        max = temp;
                        highest[count][0] = i;
                        highest[count++][1] = j;
                    }
                    else if (temp == max) {
                        highest[count][0] = i;
                        highest[count++][1] = j;
                    }
                }
            }
            System.out.println("#" + (test+1) + " " + solve(map, highest, depth, count));
        }
    }

    private static int solve(int[][] map, int[][] highest, int depth, int count) {
        int max = 0;
        for(int i=0; i<count; i++){
            int temp = func(map, new boolean[map.length][map.length], highest[i][0], highest[i][1], depth, true);
            if(temp > max)
                max = temp;
        }
        return max;
    }

    private static int func(int[][] map, boolean[][] visited, int row, int col, int depth, boolean chance) {
        // 갈 수 없는 길이면
        if(row >= map.length || col >= map.length || row < 0 || col < 0)
            return 0; // 경로에 포함 X

        // 갈 수 있는 길
        visited[row][col] = true; // 갔다고 표시
        int current = map[row][col]; // 현재 위치의 높이
        int max = 0; // 현재 위치에서 갈 수 있는 가장 긴 경로를 담을 변수(현재 위치도 경로에 포함)

        // 네 개의 방향 탐색
        for(int i=0; i<4; i++){
            // 다음 위치 저장
            int nextRow = row + dirY[i];
            int nextCol = col + dirX[i];
            int path; // 각각의 방향에서 만들 수 있는 경로를 담을 변수

            // 다음 위치가 갈 수 없는 길이면
            if(nextRow >= map.length || nextCol >= map.length || nextRow < 0 || nextCol < 0 || visited[nextRow][nextCol]) {
                path = 1; // 현재 위치만 경로에 포함하기 위해 1만 리턴
                if(path > max)
                    max = path;
                continue;
            }

            // 다음 위치가 갈 수 있는 길이면
            int next = map[nextRow][nextCol]; // 다음 위치의 높이

            if(next >= current) { // 다음 위치가 현재 위치보다 같거나 높으면
                if(chance && (next - depth) < current) { // 근데 기회가 있고 && 최대 깊이 하에서 파냈을 때 current 보다 높이가 낮아진다면
                    map[nextRow][nextCol] = current - 1;
                    path = 1 + func(map, visited, nextRow, nextCol, depth, false); // 갈 수 있는 길(기회 사용)
                    // ***** 마지막까지 헤맸던 부분 *****
                    // 특정 방향으로 탐색할 때 산의 높이를 깎은 것이므로 다른 방향 갈 때(for문의 다음번 반복)는
                    // 해당 지역의 높이를 다시 원상복구 시켜줘야 한다
                    map[nextRow][nextCol] = next; // 원상복구
                }
                else // 기회가 없거나 최대한 파내도 current 높이보다 같거나 높다면 더 갈 수 없음
                    path = 1; // 현재 위치만 경로에 포함
            }
            else { // 현재 위치가 더 높다면
                path = 1 + func(map, visited, nextRow, nextCol, depth, chance); // 기회 사용 안하고 갈 수 있음
            }

            if(path > max)
                max = path;
        }
        // ***** 마지막까지 헤맸던 부분 *****
        // visited는 solve에서 넘겨준 것을 계속 쓰고 있으므로
        // 모든 경우를 정상적으로 다 시도해보려면 각각의 시도 후에 visited를 다시 초기화 시켜줘야함
        visited[row][col] = false;
        return max;
    }

}
