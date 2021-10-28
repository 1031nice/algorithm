package leetcode;

public class ValidateBinarySearchTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(6);
        root.left.left.left = null;
        root.left.left.right = null;
        root.left.right.left = null;
        root.left.right.right = new TreeNode(3);
        System.out.println(new ValidateBinarySearchTree().isValidBST(root));
    }

    public int getMax(TreeNode root) {
        if(root.right == null) return root.val;
        return getMax(root.right);
    }

    public int getMin(TreeNode root) {
        if(root.left == null) return root.val;
        return getMin(root.left);
    }

    public boolean isValidBST(TreeNode root) {
        if(root.left == null && root.right == null) return true;

        else if(root.left == null) { // right only
            return getMin(root.right) > root.val && isValidBST(root.right);
        }

        else if(root.right == null) { // left only
            return getMax(root.left) < root.val && isValidBST(root.left);
        }

        else {
            return (getMax(root.left) < root.val && getMin(root.right) > root.val) &&
                    (isValidBST(root.left) && isValidBST(root.right));
        }
    }

}
