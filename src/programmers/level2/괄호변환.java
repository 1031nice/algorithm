package programmers.level2;

import java.util.Stack;

public class 괄호변환 {
    public String solution(String p) {
        // 1.
        if(p.length() == 0) return "";

        // 2.
        int open = 0, close = 0;
        String u = "", v = "";
        for(int i=0; i<p.length(); i++) {
            if(p.charAt(i) == '(') open++;
            else close++;
            if(open == close) {
                u = p.substring(0, i+1);
                v = p.substring(i+1, p.length());
                break;
            }
        }

        // 3.
        if(check(u)) return u + solution(v);

            // 4.
        else {
            u = u.substring(1, u.length()-1);
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(solution(v)).append(")");
            for(int i=0; i<u.length(); i++) {
                if(u.charAt(i) == '(') sb.append(')');
                else sb.append('(');
            }
            return sb.toString();
        }
    }

    boolean check(String str) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<str.length(); i++) {
            char now = str.charAt(i);
            if(now == '(') stack.push(now);
            else {
                if(stack.isEmpty() || stack.peek() == ')') return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
