# 61. Rotate List

## Description
- [Link](https://leetcode.com/problems/rotate-list)
- Given the head of a linked list, rotate the list to the right by k places.
- Constraints
  - The number of nodes in the list is in the range [0, 500] 
  - 0 <= `k` <= 2 * 10^9

## Solution
### Key Idea
- `k` can be very large -> use modulo. 
- No need to rotate one by one.
  - Need three pointers.
    ```
                          X (k = 3)
     asis   A O O O O O O B O C (length = 10)
     tobe   B O C A O O O O O O
    ```

### Pseudo Code
```
rotateRight(head, k): // head points to A
    if head is null:
        return null
    
    length = get the linked list's length
    tail = get the linked list's tail node // 'tail' points to C
    k = k % length
    if length == 1 || k == 0:
        return head
    
    nodesToMove = length - k
    newHead = the node at 'nodesToMove' position from the 'head' // 'newHead' points to B
    prev = the node immediately preceding 'newHead' 
    if prev is not null:
        prev.next = null
    
    tail.next = head // C -> A
    return newHead
```

### Time Complexity
- O(N) where N is the number of nodes in the linked list.