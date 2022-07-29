package programmers.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class 뉴스클러스터링 {

    /**
     * 다중집합의 교집합과 합집합의 크기를 구하는 게 핵심
     * 다중집합의 교집합의 크기는 두 다중집합의 각 문자열 등장 빈도수 중 최솟값들을 더했을 때 구할 수 있고,
     * 다중집합의 합집합의 크기는 두 다중집합의 각 문자열 등장 빈도수 중 최댓값들을 더했을 때 구할 수 있다
     */
    public int solution(String str1, String str2) {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        tokenize(str1, list1);
        tokenize(str2, list2);
        Map<String, Integer> valueToCount1 = new HashMap<>();
        Map<String, Integer> valueToCount2 = new HashMap<>();
        list1.forEach(token -> valueToCount1.put(token, valueToCount1.get(token) == null ? 1 : valueToCount1.get(token) + 1));
        list2.forEach(token -> valueToCount2.put(token, valueToCount2.get(token) == null ? 1 : valueToCount2.get(token) + 1));

        int intersect = valueToCount1.entrySet()
                                     .stream()
                                     .filter(entry -> valueToCount2.containsKey(entry.getKey()))
                                     .mapToInt(entry -> Math.min(entry.getValue(), valueToCount2.get(entry.getKey())))
                                     .sum();

        int union = valueToCount1.entrySet()
                                 .stream()
                                 .mapToInt(entry -> valueToCount2.containsKey(entry.getKey())
                                     ? Math.max(entry.getValue(), valueToCount2.get(entry.getKey()))
                                     : entry.getValue())
                                 .sum();

        union += valueToCount2.entrySet()
                              .stream()
                              .filter(entry -> !valueToCount1.containsKey(entry.getKey()))
                              .mapToInt(Entry::getValue)
                              .sum();

        return list1.isEmpty() && list2.isEmpty() ? 65536 : 65536 * intersect / union;
    }

    public int solution2(String str1, String str2) {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        tokenize(str1, list1);
        tokenize(str2, list2);

        boolean[] visited2 = new boolean[list2.size()];

        int intersect = 0;
        for(int i=0; i<list1.size(); i++) {
            for(int j=0; j<list2.size(); j++) {
                if(!visited2[j]) {
                    if(list1.get(i).equals(list2.get(j))) {
                        intersect++;
                        visited2[j] = true;
                        break;
                    }
                }
            }
        }

        visited2 = new boolean[list2.size()];
        int union = 0;
        for(int i=0; i<list1.size(); i++) {
            union++;
            for(int j=0; j<list2.size(); j++) {
                if(!visited2[j]) {
                    if(list1.get(i).equals(list2.get(j))) {
                        visited2[j] = true;
                        break;
                    }
                }
            }
        }
        for(int i=0; i<list2.size(); i++)
            if(!visited2[i]) union++;

        return list1.isEmpty() && list2.isEmpty() ? 65536 : 65536 * intersect / union;
    }

    private void tokenize(String str, ArrayList<String> list) {
        for(int i=0; i<str.length()-1; i++) {
            String now = str.substring(i, i+2);
            if(Character.isAlphabetic(now.charAt(0)) && Character.isAlphabetic(now.charAt(1)))
                list.add(now.toLowerCase());
        }
    }

    public int solution_fail(String str1, String str2) {
        ArrayList<String> list1 = new ArrayList<>();
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> dup1 = new HashSet<>();
        ArrayList<String> list2 = new ArrayList<>();
        HashSet<String> set2 = new HashSet<>();
        HashSet<String> dup2 = new HashSet<>();
        for(int i=0; i<str1.length()-1; i++) {
            String now = str1.substring(i, i+2);
            if(set1.contains(now)) {
                dup1.add(now);
            }
            if(Character.isAlphabetic(now.charAt(0)) && Character.isAlphabetic(now.charAt(1))) {
                list1.add(now.toLowerCase());
                set1.add(now.toLowerCase());
            }
        }
        for(int i=0; i<str2.length()-1; i++) {
            String now = str2.substring(i, i+2);
            if(set2.contains(now)) {
                dup2.add(now);
            }
            if(Character.isAlphabetic(now.charAt(0)) && Character.isAlphabetic(now.charAt(1))) {
                list2.add(now.toLowerCase());
                set2.add(now.toLowerCase());
            }
        }
        int intersect = (int) list1.stream()
                .distinct()
                .filter(list2::contains)
                .count();
        ArrayList<String> unionList = new ArrayList<>();
        unionList.addAll(list1);
        unionList.addAll(list2);
        int union = (int) unionList.stream().distinct().count();

        for (String next : dup1) {
            int count1 = (int) list1.stream().filter(s -> s.equals(next)).count();
            int count2 = (int) list2.stream().filter(s -> s.equals(next)).count();
            if (list1.contains(next) && list2.contains(next)) {
                intersect += Math.min(count1, count2) - 1;
            }
            union += Math.max(count1, count2) - 1;
        }

        // 둘 모두 속하는 것은 앞에서 고려됐으므로 남은건 str2에서만 여러번 등장하는 토큰
        for (String next : dup2) {
            if(dup1.contains(next)) continue;
            int count2 = (int) list2.stream().filter(s -> s.equals(next)).count();
            union += count2 - 1;
        }

        return list1.isEmpty() && list2.isEmpty() ? 65536 : 65536 * intersect / union;
    }
}
