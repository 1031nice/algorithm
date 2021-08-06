package programmers.level2;

import java.util.*;

public class 게임맵최단거리 {

    public static void main(String[] args) {
        System.out.println(new Solution2().solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));
    }

}

class Solution2 {

    int[] dirRow = {-1, 0, 1, 0};
    int[] dirCol = {0, 1, 0, -1};

    public int solution(int[][] maps) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        q.add(new int[]{0,0,1});
        while(!q.isEmpty()) {
            int[] poll = q.poll();
            if(visited[poll[0]][poll[1]])
                continue;
            else if(poll[0] == maps.length-1 && poll[1] == maps[0].length-1)
                return poll[2];
            visited[poll[0]][poll[1]] = true;
            for(int dir=0; dir<4; dir++) {
                int newRow = poll[0] + dirRow[dir];
                int newCol = poll[1] + dirCol[dir];
                if(newRow >= maps.length || newCol >= maps[0].length ||
                        newRow < 0 || newCol < 0)
                    continue;
                if(maps[newRow][newCol] == 1)
                    q.add(new int[] {newRow, newCol, poll[2] + 1});
            }
        }
        return -1;
    }
}