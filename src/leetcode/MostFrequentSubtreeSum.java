package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MostFrequentSubtreeSum {
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
        System.out.println(Arrays.toString(new MostFrequentSubtreeSum().findFrequentTreeSum(new TreeNode(1))));
    }

    static int maxFreq;
    static ArrayList<Integer> maxSumList;
    static HashMap<Integer, Integer> sumToFreq;

    public int[] findFrequentTreeSum(TreeNode root) {
        maxFreq = -1000000001;
        maxSumList = new ArrayList<>();
        sumToFreq = new HashMap<>();

        getSumOfAllSubTree(root);

        int[] answer = new int[maxSumList.size()];
        int index = 0;
        for(int max : maxSumList) {
            answer[index++] = max;
        }
        return answer;
    }

    public int getSumOfAllSubTree(TreeNode root) {
        if(root == null) return 0;
        int left = getSumOfAllSubTree(root.left);
        int right = getSumOfAllSubTree(root.right);
        int sum = left + right + root.val;

        int index;
        int freq = sumToFreq.containsKey(sum) ? sumToFreq.get(sum) + 1 : 1;
        sumToFreq.put(sum, freq);

        if(freq > maxFreq) {
            maxFreq = freq;
            maxSumList.clear();
            maxSumList.add(sum);
        }
        else if(freq == maxFreq && !maxSumList.contains(sum)) {
            maxSumList.add(sum);
        }

        return sum;
    }
}
