package programmers.level2;

import java.util.ArrayList;
import java.util.Collections;

public class 모음사전 {

    public static void main(String[] args) {
        System.out.println(new 모음사전().solution("AAAAE"));
    }

    public int solution(String word) {
        char[] vowel = {'A', 'E', 'I', 'O', 'U'};
        ArrayList<String> list = new ArrayList<>();
        for(int i=1; i<=5; i++) {
            func(list, vowel, new StringBuilder(), word, 0, i);
        }
        Collections.sort(list);
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).equals(word))
                return i+1;
        }
        return -1;
    }

    public void func(ArrayList<String> list, char[] vowel, StringBuilder sb, String word, int length, int size) {
        if(length == size) {
            list.add(sb.toString());
            return;
        }

        for(int i=0; i<vowel.length; i++) {
            sb.append(vowel[i]);
            func(list, vowel, sb, word, length+1, size);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
