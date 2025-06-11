# 137. Single Number 2

## Description
- [Link](https://leetcode.com/problems/single-number-ii)
- Given an integer array `nums` where every element appears three times except for one, which appears exactly once. Find the single element and return it.
- Constraints
  - Each element in `nums` appears exactly three times except for one element which appears once.
  - Implement a solution with a linear runtime complexity and use only constant extra space.
  - -2^31 <= `nums[i]` <= 2^31 - 1

## Solution
### Key Idea
- Because every element appears an odd number of times, XOR operation cannot be used.
  - So focus on the fact that there is a difference in frequency.
    - Given only constant extra space, I should count **bits**(not a number). 

### Pseudo Code
```
singleNumber(nums):
    bits = new int[32]
    for num in nums:
        for i in 0 until 32:
            bits[i] += (num >> i) & 1
    
    ret = 0
    for i, bit in bits:
        if bits[i] % 3 != 0:
            ret |= (1 << i)
    return ret
```

### Time Complexity
- O(N) where N is the length of the input array `nums`.