package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        func(list, "", 0, 0, n);
        return list;
    }

    public void func(List<String> list, String str, int open, int close, int n) {
        if(open + close >= 2 * n) {
            if(open == close && isWellFormed(str)) {
                list.add(str);
            }
            return;
        }
        func(list, str + "(", open + 1, close, n);
        if(open > close) {
            func(list, str + ")", open, close + 1, n);
        }
    }

    public boolean isWellFormed(String str) {
        Stack<Character> stack = new Stack<>();
        for(char ch : str.toCharArray()) {
            if(ch == '(') {
                stack.push(ch);
            }
            else {
                if(stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
