package leetcode;

import java.util.HashMap;
import java.util.Map;

/*
1577. Number of Ways Where Square of Number Is Equal to Product of Two Numbers - medium
 */

// 배열 1의 모든 원소에 제곱 -> O(N)
// 배열 2의 두 원소의 곱 -> O(M^2)
// 위의 두 결과 비교 -> O(N) (두 원소의 곱을 hash map에 저장할 경우)

public class P1577 {

    public static void main(String[] args) {
        System.out.println(new P1577().numTriplets(new int[]{1,1}, new int[]{1,1,1}));
    }

    public int numTriplets(int[] nums1, int[] nums2) {
        return triplet(nums1, nums2) + triplet(nums2, nums1);
    }

    public int triplet(int[] sqrtArr, int[] mulArr) {
        int ret = 0;

        Map<Long, Integer> mulToCount = new HashMap<>();
        for(int i=0; i<mulArr.length; i++) {
            for(int j=i+1; j<mulArr.length; j++) {
                long mul = (long)mulArr[i] * mulArr[j];
                mulToCount.put(mul, mulToCount.containsKey(mul) ? mulToCount.get(mul) + 1 : 1);
            }
        }

        for(int n : sqrtArr) {
            long sqrt = (long)n * n;
            if(mulToCount.containsKey(sqrt)) {
                ret += mulToCount.get(sqrt);
            }
        }

        return ret;
    }

}
