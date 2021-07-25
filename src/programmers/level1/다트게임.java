package programmers.level1;

// 이건 좀.. 일단 생각나는대로 풀긴했는데
public class 다트게임 {
    public static void main(String[] args) {
        Solution10 solution10 = new Solution10();
        System.out.println(solution10.solution("1S*2T*3S"));
    }
}

class Solution10 {
    public int solution(String dartResult) {
        int answer = 0;
        boolean star = false;
        boolean acha = false;
        int[] points = new int[26];
        points['S' - 'A'] = 1;
        points['D' - 'A'] = 2;
        points['T' - 'A'] = 3;

        for(int i=dartResult.length()-1; i>=0; i--) {
            char ch = dartResult.charAt(i);
            if(ch == '*') {
                int point = points[dartResult.charAt(i-1) - 'A'];
                int number = dartResult.charAt(i-2) - '0';
                if(i-3 >= 0 && Character.isDigit(dartResult.charAt(i-3))) {
                    number += 10 * (dartResult.charAt(i-3) - '0');
                    i--;
                }
                int temp = (int) (2 * Math.pow(number, point));
                if(star)
                    temp *= 2;
                answer += temp;
                star = true;
                i -= 2;
            }
            else if(ch == '#') {
                acha = true;
            }
            else {
                int point = points[ch - 'A'];
                int number = dartResult.charAt(i-1) - '0';
                if(i-2 >= 0 && Character.isDigit(dartResult.charAt(i-2))) {
                    number += 10 * (dartResult.charAt(i-2) - '0');
                    i--;
                }
                int temp = (int)Math.pow(number, point);
                if(star) {
                    temp *= 2;
                    star = false;
                }
                if(acha) {
                    temp *= -1;
                    acha = false;
                }
                answer += temp;
                i--;
            }
        }
        return answer;
    }
}
