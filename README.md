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