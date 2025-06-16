# 155. Min Stack

## Description
- [Link](https://leetcode.com/problems/min-stack)
- Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
- Constraints
  - You must implement a solution with O(1) time complexity for each function.

## Solution
### Key Idea
- O(1) time complexity implies that we need to track every minimum value.
- Additions and deletions occur in a stack following a LIFO mechanism.
  - Let's observe how the minimum value changes as elements are added to and deleted from the stack, using a concrete example.

### Pseudo Code
```
class MinStack:
    Stack minStack = new Stack()
    Stack stack = new Stack()
    
    push(val) {
        if minStack is empty:
            stack.push(val)
            minStack.push(val)
            return

        stack.push(val)
        minStack.push(min(minStack.peek(), val))
    
    // will always be called on non-empty stacks
    pop():
        stack.pop()
        minStack.pop()
    
    // will always be called on non-empty stacks
    top():
        return stack.peek()
    
    // will always be called on non-empty stacks
    getMin():
        return minStack.peek()
```

### Time Complexity
- O(1)