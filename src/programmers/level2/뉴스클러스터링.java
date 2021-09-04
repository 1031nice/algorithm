package programmers.level2;

import java.util.ArrayList;

public class 뉴스클러스터링 {

    public static void main(String[] args) {
        System.out.println(new 뉴스클러스터링().solution("FRANCE", "french"));
        System.out.println(new 뉴스클러스터링().solution("handshake", "shake hands"));
        System.out.println(new 뉴스클러스터링().solution("aa1+aa2", "AAAA12"));
        System.out.println(new 뉴스클러스터링().solution("E=M*C^^2", "e=m*c^2"));
    }

    public int solution(String str1, String str2) {
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

//    public int solution2(String str1, String str2) {
//        ArrayList<String> list1 = new ArrayList<>();
//        HashSet<String> set1 = new HashSet<>();
//        HashSet<String> dup1 = new HashSet<>();
//        ArrayList<String> list2 = new ArrayList<>();
//        HashSet<String> set2 = new HashSet<>();
//        HashSet<String> dup2 = new HashSet<>();
//        for(int i=0; i<str1.length()-1; i++) {
//            String now = str1.substring(i, i+2);
//            if(set1.contains(now)) {
//                dup1.add(now);
//            }
//            if(Character.isAlphabetic(now.charAt(0)) && Character.isAlphabetic(now.charAt(1))) {
//                list1.add(now.toLowerCase());
//                set1.add(now.toLowerCase());
//            }
//        }
//        for(int i=0; i<str2.length()-1; i++) {
//            String now = str2.substring(i, i+2);
//            if(set2.contains(now)) {
//                dup2.add(now);
//            }
//            if(Character.isAlphabetic(now.charAt(0)) && Character.isAlphabetic(now.charAt(1))) {
//                list2.add(now.toLowerCase());
//                set2.add(now.toLowerCase());
//            }
//        }
//        int intersect = (int) list1.stream()
//                .distinct()
//                .filter(list2::contains)
//                .count();
//        ArrayList<String> unionList = new ArrayList<>();
//        unionList.addAll(list1);
//        unionList.addAll(list2);
//        int union = (int) unionList.stream().distinct().count();
//
//        for (String next : dup1) {
//            int count1 = (int) list1.stream().filter(s -> s.equals(next)).count();
//            int count2 = (int) list2.stream().filter(s -> s.equals(next)).count();
//            if (list1.contains(next) && list2.contains(next)) {
//                intersect += Math.min(count1, count2) - 1;
//            }
//            union += Math.max(count1, count2) - 1;
//        }
//
//        // 둘 모두 속하는 것은 앞에서 고려됐으므로 남은건 str2에서만 여러번 등장하는 토큰
//        for (String next : dup2) {
//            if(dup1.contains(next)) continue;
//            int count2 = (int) list2.stream().filter(s -> s.equals(next)).count();
//            union += count2 - 1;
//        }
//
//        return list1.isEmpty() && list2.isEmpty() ? 65536 : 65536 * intersect / union;
//    }
}
