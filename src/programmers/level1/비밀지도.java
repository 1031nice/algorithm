package programmers.level1;

import java.util.Arrays;

public class 비밀지도 {

    public static void main(String[] args) {
        Solution9 solution9 = new Solution9();
        System.out.println(Arrays.toString(solution9.solution(5, new int[]{9,20,28,18,11}, new int[]{30,1,21,17,28})));
    }

}

class Solution9 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        int[] sum = new int[n];
        String[] answer = new String[n];

        for(int i=0; i<n; i++)
            sum[i] = arr1[i] | arr2[i];

        for(int i=0; i<n; i++) {
            String now = "";
            for(int j=n-1; j>=0; j--){
                if((1 << j & sum[i]) != 0)
                    now += "#";
                else
                    now += " ";
            }
            answer[i] = now;
        }
        return answer;
    }
}
