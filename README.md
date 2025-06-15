## Amortized analysis

I've long understood that time complexity isn't necessarily determined by the number of nested loops.
Even if an algorithm has nested for loops, its time complexity could be O(N).
What I didn't know until now was the specific term for this concept: Amortized Analysis.

Here's an example:
```
find_components(grid):
    component_count = 0 
    
    for r in 0 until rows:
        for c 0 until cols:
            if grid[r][c] == 1 and not visited:
                component_count += 1
                dfs(grid, visited, r, c)

    return component_count
```
Let's assume the grid is an N x N 2D array.

At first glance, it might seem like the nested for loops take O(N^2) time,
and each call to dfs (or bfs) could also explore the entire grid in the worst case, taking O(N^2) time.
This would misleadingly suggest a total time complexity of O(N^2) * O(N^2) = O(N^4).

However, the key insight here lies in how the visited array works.
The dfs (or bfs) function only explores cells that haven't been visited yet.
Thanks to this visit tracking, each cell in the grid is visited and processed by the graph traversal (DFS/BFS) logic at most once across all calls.

Ultimately, the algorithm's time complexity is O(N^2).

## Encoding Data into Bits

### Encoding Current and Next States into Integer Bits
- Related problem: [238. Game of Life](https://leetcode.com/problems/game-of-life)
- To apply some rules to every element simultaneously, we need to store both the current and next states.
- For O(1) space complexity, we can encode the states into an integer.

## Best Practices for Bit Counting and Reconstruction

### Extracting Bits from Numbers
- Related problem: [137. Single Number 2](bit/137_Single_Number_2.md)
- To get the value of each bit from an integer, consider using bitwise operations like right-shift and AND(&) instead of arithmetic operations like division or modulo.
- Why it matters: Arithmetic operations can't handle negative numbers correctly when extracting bits.

### Reconstructing Numbers from Bit Counts
- Related problem: [137. Single Number 2](bit/137_Single_Number_2.md)
- To reconstruct a number from bit counts, consider using bitwise left-shift and OR(|).
- Why it matters: Bitwise operations are fundamentally designed for bit-level manipulation, offering a simple and effective way to reconstruct numbers while correctly handling two's complement representation.