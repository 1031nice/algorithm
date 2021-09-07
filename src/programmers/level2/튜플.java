package programmers.level2;

import java.util.*;

public class 튜플 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 튜플().solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
        System.out.println(Arrays.toString(new 튜플().solution("{{20,111},{111}}")));
    }

    public int[] solution(String s) {
        s = s.substring(1, s.length()-1);

        ArrayList<String> list = new ArrayList<>();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while(index < s.length()) {
            if(s.charAt(index) == '{') {
                index++;
                while(s.charAt(index) != '}') {
                    sb.append(s.charAt(index));
                    index++;
                }
                list.add(sb.toString());
                sb.setLength(0);
            }
            index++;
        }

        list.sort(Comparator.comparingInt(o -> o.split(",").length)); // 토큰의 수에 대해 오름차순

        int[] answer = new int[list.get(list.size()-1).split(",").length];
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<list.size(); i++) {
            String[] split = list.get(i).split(",");
            for(int j=0; j<split.length; j++) {
                if(!set.contains(split[j])) {
                    set.add(split[j]);
                    answer[i] = Integer.parseInt(split[j]);
                }
            }
        }
        return answer;
    }

    // 여러자리 숫자를 고려하지 않은 풀이
    /*public int[] solution2(String s) {
        s = s.substring(1, s.length()-1);
        s = s.replaceAll("\\{", "");
        s = s.replaceAll(",", "");
        String[] split = s.split("}");
        Arrays.sort(split, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        int[] answer = new int[split[split.length-1].length()];
        boolean[] visited = new boolean[10];
        for(int i=0; i<split.length; i++) {
            for(int c=0; c<split[i].length(); c++) {
                if(!visited[split[i].charAt(c) - '0']) {
                    visited[split[i].charAt(c) - '0'] = true;
                    answer[i] = split[i].charAt(c) - '0';
                }
            }
        }
        return answer;
    }*/
}
