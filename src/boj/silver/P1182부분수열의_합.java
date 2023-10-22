package boj.silver;

import java.util.Scanner;

public class P1182부분수열의_합 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int S = scanner.nextInt();

        int[] nums = new int[N];

        for (int i=0; i<N; i++) {
            nums[i] = scanner.nextInt();
        }

        System.out.println(func(nums, 0, S));
    }

    private static int func(int[] nums, int now, int target) {
        if (now == nums.length) {
            return 0;
        }

        int cnt = 0;

        // 현재 칸 선택 -> 원상복구
        target -= nums[now];
        if (target == 0) {
            cnt++;
        }
        cnt += func(nums, now + 1, target);
        target += nums[now];

        // 현재 칸 선택하지 않음
        cnt += func(nums, now +1, target);

        return cnt;
    }
}
