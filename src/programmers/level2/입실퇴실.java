package programmers.level2;

import java.util.Arrays;
import java.util.HashSet;

public class 입실퇴실 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 입실퇴실().solution(new int[]{1,3,2}, new int[]{1,2,3})));
        System.out.println(Arrays.toString(new 입실퇴실().solution(new int[]{1,4,2,3}, new int[]{2,1,3,4})));
    }

    public int[] solution(int[] enter, int[] leave) {
        HashSet<Integer> set = new HashSet<>();
        int[] answer = new int[enter.length];
        int indexOfEnter = 0;
        int indexOfLeave = 0;

        while(indexOfLeave < leave.length) {
            if(set.contains(leave[indexOfLeave])) {
                for(Integer e : set) {
                    if(e == leave[indexOfLeave])
                        answer[e-1] += set.size()-1;
                    else
                        answer[e-1]++;
                }
                set.remove(leave[indexOfLeave++]);
            }
            else {
                while(indexOfEnter < enter.length) {
                    int nowEnter = enter[indexOfEnter];
                    set.add(nowEnter);
                    indexOfEnter++;
                    if(nowEnter == leave[indexOfLeave])
                        break;
                }
            }
        }

        return answer;
    }
}
