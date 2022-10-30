package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
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

public class P872LeafSimilarTrees {
    int count = 0;

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafValues = new ArrayList<>();
        getLeafValues(root1, leafValues);
        return compareLeafValues(root2, leafValues) && count == leafValues.size();
    }

    void getLeafValues(TreeNode root, List<Integer> leafValues) {
        if (root == null) {
            return;
        } else if (root.left == null && root.right == null) {
            leafValues.add(root.val);
            return;
        }

        getLeafValues(root.left, leafValues);
        getLeafValues(root.right, leafValues);
    }

    boolean compareLeafValues(TreeNode root, List<Integer> leafValues) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null) {
            if (count >= leafValues.size()) {
                return false;
            }
            return leafValues.get(this.count++) == root.val;
        }

        return compareLeafValues(root.left, leafValues) &&
                compareLeafValues(root.right, leafValues);
    }
}
