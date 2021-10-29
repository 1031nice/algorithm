package leetcode;

public class SumRootToLeafNumbers {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    public int sumNumbers(TreeNode root, int val) {
        if(root == null) return 0;

        val = val * 10 + root.val;
        if(root.left == null && root.right == null) {
            return val;
        }

        int sum = 0;
        sum += sumNumbers(root.right, val);
        sum += sumNumbers(root.left, val);
        return sum;
    }
}
