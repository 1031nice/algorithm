package programmers.level1;

public class 신규아이디추천 {
    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();

        System.out.println(solution2.solution("=.="));
    }
}

class Solution2 {
    public String solution(String new_id) {
        // 1
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<new_id.length(); i++) {
            char ch = new_id.charAt(i);
            if(Character.isUpperCase(ch))
                sb.append(Character.toLowerCase(ch));
            else
                sb.append(ch);
        }
        new_id = sb.toString();
        sb = new StringBuilder();

        // 2
        for(int i=0; i<new_id.length(); i++) {
            char ch = new_id.charAt(i);
            if(ch == '-' || ch == '_' || ch == '.' || Character.isDigit(ch) || Character.isAlphabetic(ch))
                sb.append(ch);
        }
        new_id = sb.toString();

        // 3
        new_id = new_id.replaceAll("[.]+", ".");

        // 4
        if(new_id.indexOf('.') == 0)
            new_id = new_id.substring(1);
        if(new_id.lastIndexOf('.') != -1 && new_id.lastIndexOf('.') == new_id.length()-1)
            new_id = new_id.substring(0, new_id.length()-1);

        // 5
        if(new_id.length() == 0)
            new_id = "a";

        // 6
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            if(new_id.charAt(14) == '.')
                new_id = new_id.substring(0, 14);
        }

        if(new_id.length() <= 2) {
            new_id += new_id.charAt(new_id.length()-1);
        }

        return new_id;
    }
}
