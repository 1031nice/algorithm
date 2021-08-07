package programmers.level2;

public class 문자열압축 {
    public static void main(String[] args) {
        Solution.solution(	"aabbaccc");
    }
    static class Solution {
        // 다행히 무조건 앞에서부터 잘라야 한다고 한다.
        // "xabab"일 때 x / 2ab 이렇게는 안되고
        // "xabab"인 경우 가장 짧은 경우는 5
        public static int solution(String s) {
            int answer = s.length(); // 1로 자른 경우
            for(int j=1; j<=s.length(); j++) {
                int len = s.length();
                int index = 0;
                int count = 1; // 몇 번 등장했는지를 센다
                String token = s.substring(index, index+j);
                index += j;
                while(index + j <= s.length()) {
                    String now = s.substring(index, index+j);
                    if(now.equals(token)) {
                        len -= j;
                        count++;
                    }
                    else {
                        if(count > 1) {
                            len += String.valueOf(count).length();
                        }
                        token = now;
                        count = 1;
                    }
                    index += j;
                }
                if(count > 1) {
                    len += String.valueOf(count).length();
                }
                answer = Math.min(answer, len);
            }
            return answer;
        }
    }
}
