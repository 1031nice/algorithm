package leetcode;

import java.util.HashMap;
import java.util.HashSet;

public class MakingALargeIsland {

    int nextId = 1;
    int[] dirRow = {-1, 0, 1, 0};
    int[] dirCol = {0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        int max = -1;

        HashMap<Integer, Integer> groupIdToSize = new HashMap<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        // calc every group's size
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    int groupSize = visit(grid, visited, i, j);
                    if(groupSize > max) max = groupSize; // default max
                    groupIdToSize.put(nextId++, groupSize);
                }
            }
        }

        // check 4 direction for each 0
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                if(grid[i][j] == 0) {
                    HashSet<Integer> visitedSet = new HashSet<>();
                    int sum = 1;
                    for(int dir=0; dir<4; dir++) {
                        int newRow = i + dirRow[dir];
                        int newCol = j + dirCol[dir];
                        if(isIndexOk(grid.length, newRow, newCol) &&
                                grid[newRow][newCol] != 0 &&
                                !visitedSet.contains(grid[newRow][newCol])) {
                            sum += groupIdToSize.get(grid[newRow][newCol]);
                            visitedSet.add(grid[newRow][newCol]);
                        }
                    }
                    if(sum > max) max = sum;
                }
            }
        }

        return max;
    }

    public int visit(int[][] grid, boolean[][] visited, int row, int col) {
        if(!isIndexOk(grid.length, row, col)) return 0;
        else if(visited[row][col]) return 0;
        else if(grid[row][col] == 0) return 0;

        int ret = 1;
        visited[row][col] = true;
        grid[row][col] = nextId;
        for(int dir=0; dir<4; dir++) {
            int newRow = row + dirRow[dir];
            int newCol = col + dirCol[dir];
            ret += visit(grid, visited, newRow, newCol);
        }
        return ret;
    }

    public boolean isIndexOk(int size, int row, int col) {
        return row >= 0 && col >=0 && row < size && col < size;
    }

}
