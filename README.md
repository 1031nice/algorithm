## Best Practices for Bit Counting and Reconstruction
Related problem: [137. Single Number 2](bit/137_Single_Number_2.md)

### Extracting Bits from Numbers
- To get the value of each bit from an integer, consider using bitwise operations like right-shift and AND(&) instead of arithmetic operations like division or modulo.
- Why it matters: Arithmetic operations can't handle negative numbers correctly when extracting bits.

### Reconstructing Numbers from Bit Counts
- To reconstruct a number from bit counts, consider using bitwise left-shift and OR(|).
- Why it matters: Bitwise operations are fundamentally designed for bit-level manipulation, offering a simple and effective way to reconstruct numbers while correctly handling two's complement representation.