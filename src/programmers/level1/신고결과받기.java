package programmers.level1;

import java.util.*;
import java.util.stream.Collectors;

public class 신고결과받기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 신고결과받기().solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2)));
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, HashSet<String>> map = new HashMap<>();
        for(String s : report) {
            String[] split = s.split(" ");
            String reporter = split[0];
            String reportee = split[1];
            if(map.containsKey(reportee)) {
                map.get(reportee).add(reporter);
            } else {
                HashSet<String> set = new HashSet<>();
                set.add(reporter);
                map.put(reportee, set);
            }
        }

        List<HashSet<String>> collect = map.entrySet().stream().filter(e -> e.getValue().size() >= k).map(Map.Entry::getValue).collect(Collectors.toList());
//        List<HashSet<String>> collect = map.values().stream().filter(strings -> strings.size() >= k).collect(Collectors.toList());

        HashMap<String, Integer> userToCount = new HashMap<>();
        for(HashSet<String> set : collect) {
            for(String s : set) {
                if(userToCount.containsKey(s)) {
                    userToCount.put(s, userToCount.get(s) + 1);
                } else {
                    userToCount.put(s, 1);
                }
            }
        }

        int[] answer = new int[id_list.length];
        for(int i=0; i<id_list.length; i++) {
            answer[i] = userToCount.getOrDefault(id_list[i], 0);
        }

        return answer;
    }

}
