package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class 압축 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 압축().solution("KAKAO")));
        System.out.println(Arrays.toString(new 압축().solution("TOBEORNOTTOBEORTOBEORNOT")));
        System.out.println(Arrays.toString(new 압축().solution("A")));
    }

    public int[] solution(String msg) {
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<Integer> answer = new ArrayList<>();
        IntStream.range(0, 26).forEach(i -> { map.put(String.valueOf((char)('A'+i)), i+1); });
        int index = 27;
        int last = 0;
        for(int i=0; i<msg.length(); i++) {
            for(int j=i+1; j<=msg.length(); j++) {
                if(!map.containsKey(msg.substring(i, j))) {
                    answer.add(map.get(msg.substring(i, j-1)));
                    map.put(msg.substring(i, j), index++);
                    i += j-1 - i - 1;
                    last = i+1;
                    break;
                }
            }
        }
        String substring = msg.substring(last, msg.length());
        if(!substring.isEmpty())
            answer.add(map.get(substring));
        int[] ret = new int[answer.size()];
        for(int i=0; i<ret.length; i++)
            ret[i] = answer.get(i);
        return ret;
    }

}
