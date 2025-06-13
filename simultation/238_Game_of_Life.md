# 238. Game of Life

## Description
- [Link](https://leetcode.com/problems/game-of-life)
- Given the current state of the board for 'The Game of Life', update the board to reflect its next state.
  - The next state of the board is determined by applying the rules simultaneously to every cell in the current state of the `m x n` grid `board`.
  - Live: `1`
  - Dead: `0`
  - Each cell interacts with its eight neighbors(horizontal, vertical, diagonal)

## Solution
### Key Idea
- The term 'Simultaneously' means that the next state must be determined solely based on the cells' states in the current turn.
  - This implies that if we were to immediately update a cell's state, that would incorrectly influence the calculation of its neighbors' next states.
  - Therefore, we need a mechanism to store the next state without altering the current state during the calculation phase.
    - This can be achieved by using an auxiliary array (O(MN) space complexity).
    - Alternatively, for O(1) space complexity, we can encode both the current and next states within the bits of an integer. // TODO

### Pseudo Code
```
gameOfLife(board): // O(MN) space complexity
        backup = board // deep copy
        
        for i in 0 until rows:
            for j in 0 until cols:
                live = 0
                for d in 0 until 8:
                    newRow, newCol = each neighbor of cell (i, j)
                    if out of index:
                        continue
                    live += board[newRow][newCol]
                if board[i][j] == 1:
                    if live < 2 or live > 3:
                        backup[i][j] = 0
                else: 
                    if live == 3:
                        backup[i][j] = 1

      for i in 0 until rows:
            for j in 0 until cols:
                board[i][j] = backup[i][j]
```

### Time Complexity
- O(MN) where M is the length of rows, N is the length of columns.