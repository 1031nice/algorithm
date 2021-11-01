package leetcode;

public class MaximumSubarray {
    public static void main(String[] args) {
        System.out.println(new MaximumSubarray().maxSubArray(new int[]{-6,-1,5,4,1,-8,6,7,-3,6,0,-6,-7,8,-8,-4,1}));
    }

    public int maxSubArray(int[] nums) {
        return divideAndConquer(nums, 0, nums.length-1);
    }

    public int divideAndConquer(int[] nums, int start, int end) {
        if(start == end) return nums[start];

        int mid = (start + end)/2;
        int left = divideAndConquer(nums, start, mid);
        int right= divideAndConquer(nums, mid + 1, end);

        int length = end - start + 1;
        int toLeft = mid-1;
        int toRight = mid+1;
        int middle = nums[mid];
        if (length % 2 == 0) {
            middle += nums[toRight++];
        }

        int leftSum = 0;
        int leftMax = -10001;
        while(toLeft >= start) {
            leftSum += nums[toLeft--];
            leftMax = Math.max(leftMax, leftSum);
        }
        int rightSum = 0;
        int rightMax = -10001;
        while(toRight <= end) {
            rightSum += nums[toRight++];
            rightMax = Math.max(rightMax, rightSum);
        }

        middle += Math.max(leftMax, 0);
        middle += Math.max(rightMax, 0);

        return Math.max(Math.max(left, right), middle);
    }
}
