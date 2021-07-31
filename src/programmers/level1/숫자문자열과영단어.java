package programmers.level1;

public class 숫자문자열과영단어 {
    public static void main(String[] args) {
        Solution11 solution11 = new Solution11();
        System.out.println(solution11.solution("2three45sixseven"));
    }
}

class Solution11 {

    String[] words = {"zero", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine"};

    public int solution(String s) {
        StringBuilder answer = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                answer.append(ch);
            }
            else {
                for(int j=0; j<words.length; j++) {
                    String word = words[j];
                    if(s.length() - i >= word.length() && s.startsWith(word, i)) {
                        answer.append(j);
                        i += word.length()-1;
                        break;
                    }
                }
            }
        }
        return Integer.parseInt(answer.toString());
    }
}