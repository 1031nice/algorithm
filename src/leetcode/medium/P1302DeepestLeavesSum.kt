package leetcode.medium

/**
 * Medium #BinaryTree
 */

var maxDepth = 0;
var sum = 0;

fun deepestLeavesSum(root: TreeNode?): Int {
    deepestLeavesSum(root, 0);
    return sum;
}

fun deepestLeavesSum(root: TreeNode?, depth: Int) {
    if (root == null) {
        return;
    } else if (root.left == null && root.right == null) {
        if (depth > maxDepth) {
            maxDepth = depth;
            sum = root.`val`
        } else if (depth == maxDepth) {
            sum += root.`val`
        }
    } else {
        deepestLeavesSum(root.left, depth + 1);
        deepestLeavesSum(root.right, depth + 1);
    }
}