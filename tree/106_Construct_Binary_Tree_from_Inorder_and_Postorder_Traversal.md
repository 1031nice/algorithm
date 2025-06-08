# 106. Construct Binary Tree from Inorder and Postorder Traversal

## Description
- [Link](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal)
- Given two integer arrays `inorder` and `postorder` construct and return the binary tree.
  - `inorder` is the inorder traversal of a binary tree.
  - `postorder` is the postorder traversal of the same tree.
- Constraints
  - `inorder` and `postorder` consist of unique values.

## Solution
### Key Idea
- Inorder traversal order: `Left -> Root -> Right`
- Postorder traversal order: `Left -> Right -> Root`
- The root of the current tree can be found at the end of the `postorder` array.
- Once the root is identified, its position in the `inorder` array divides the array into its left and right subtrees.
- Recursively construct the left and right subtrees using the corresponding portions of the `inorder` and `postorder` arrays.

### Pseudo Code
```
buildTree(inS, inE, postS, postE):
    if inS > inE or postS > postE:
        return null

    rootValue = postorder[postE]
    rootIndex = find root index in inorder array
    leftSubtreeSize = rootIndex - inS

    newNode = new TreeNode(rootValue)
    newNode.left = buildTree(inS, rootIndex-1, postS, postS + leftSubtreeSize - 1)
    newNode.right = buildTree(rootIndex+1, inE, postS + leftSubtreeSize, postE - 1)
    return newNode
```

### Time Complexity
- O(N) where N is the number of nodes in the binary tree(Each node is visited once).