# 200. Number of Islands
- This is a typical problem.

## Description
- [Link](https://leetcode.com/problems/number-of-islands)
- Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return the number of islands.
  - An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
  - You may assume all four edges of the grid are all surrounded by water.

## Solution
### Key Idea
- We can model the 2D grid as a graph.
  - Each cell `(i, j)` is a node.
  - An edge exists between adjacent cells (horizontally or vertically) if both are land (`'1'`).
- The graph may be disconnected graph(There can be multiple connected components).
  - We need to apply dfs/bfs multiple times.
  - The number of times dfs/bfs is initiated equals the number of islands.

### Pseudo Code
```
numIslands(grid):
    visited = new boolean[rows][cols]
    ret = 0
    for i in 0 until rows:
        for j in 0 until cols:
            if grid[i][j] == '1' && not visited:
                dfs(grid, visited, i, j)
                ret++
    return ret

dirRow = {-1, 0, 1, 0};
dirCol = {0, 1, 0, -1};

dfs(grid, visited, row, col):
    visited[row][col] = true
    for dir in 0 until 4:
        newRow = row + dirRow[dir]
        newCol = col + dirCol[dir]
        if (out of index || visited || grid[newRow][newCol] == '0':
            continue
        dfs(grid, visited, newRow, newCol)
```

### Time Complexity
- O(MN) where M is the length of rows, N is the length of columns.