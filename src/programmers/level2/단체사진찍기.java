package programmers.level2;

public class 단체사진찍기 {
    public static void main(String[] args) {
        System.out.println(Solution.solution(2, new String[]{"N~F=0", "R~T>2"}));
    }

    static class Solution {
        public static int solution(int n, String[] data) {
            String friends = "ACFJMNRT";
            boolean[] visited = new boolean[friends.length()];
            return func(data, visited, friends, "", 0);
        }

        public static int func(String[] data, boolean[] visited,
                        String friends, String combi, int select) {
            int answer = 0;
            if(select == friends.length()) {
                if(check(data, combi))
                    return 1;
            }
            for(int i=0; i<friends.length(); i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    answer += func(data, visited, friends,
                            combi+friends.charAt(i), select+1);
                    visited[i] = false;
                }
            }
            return answer;
        }

        public static boolean check(String[] data, String combi) {
            for(int i=0; i<data.length; i++) {
                String condi = data[i];
                char a = condi.charAt(0);
                char b = condi.charAt(2);
                char c = condi.charAt(3);
                int d = Character.getNumericValue(condi.charAt(4));

                int locA = combi.indexOf(a);
                int locB = combi.indexOf(b);
                int dist = Math.abs(locA - locB) - 1;
                switch(c) {
                    case '=':
                        if(dist != d)
                            return false;
                        break;
                    case '<':
                        if(dist >= d)
                            return false;
                        break;
                    case '>':
                        if(dist <= d)
                            return false;
                }
            }
            return true;
        }
    }
}
