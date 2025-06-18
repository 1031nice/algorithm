# 429. N-ary Tree Level Order Traversal
- This is a typical problem.

## Description
- [Link](https://leetcode.com/problems/n-ary-tree-level-order-traversal)
- Given an n-ary tree, return the level order traversal of its nodes' values.

## Solution
### Key Idea
- Tree level order traversal = Tree BFS 

### Pseudo Code
```
List<List<Integer>> levelOrder(Node root):
    List<List<Integer>> ret = new
    Queue<Node> q = new
    q.add(root)

    while q is not empty:
        levelSize = q.size()
        List<Integer> currentLevel = new

        for i in 0 until levelSize:
            now = q.poll()
            currentLevel.add(now.val)

            for (Node child : now.children):
                q.add(child)
        ret.add(currentLevel)
    return ret
```

### Time Complexity
- O(N) where N is the number of nodes in the tree.