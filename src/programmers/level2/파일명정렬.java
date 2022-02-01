package programmers.level2;

import java.util.*;

// 2018 KAKAO
public class 파일명정렬 {
    static class Filename implements Comparable<Filename> {
        String head;
        String number;
        String tail;

        @Override
        public int compareTo(Filename o) {
            if(this.head.equalsIgnoreCase(o.head)) {
                return Integer.compare(Integer.parseInt(this.number),
                        Integer.parseInt(o.number));
            }
            return this.head.compareToIgnoreCase(o.head);
        }

        @Override
        public String toString() {
            return head+number+tail;
        }
    }

    public String[] solution(String[] files) {
        ArrayList<Filename> filenames = new ArrayList<>();
        for(String file : files) {
            filenames.add(tokenize(file));
        }
        Collections.sort(filenames);
        String[] answer = new String[files.length];
        for(int i=0; i<answer.length; i++) {
            answer[i] = filenames.get(i).toString();
        }
        return answer;
    }

    public Filename tokenize(String file) {
        Filename ret = new Filename();
        int len = file.length();
        int index = 0;
        StringBuilder sb = new StringBuilder();

        // head(not digit)
        for(; index < len; index++) {
            char now = file.charAt(index);
            if(!Character.isDigit(now)) sb.append(now);
            else break;
        }
        ret.head = sb.toString();
        sb.setLength(0);

        // number(digit)
        for(; index < len; index++) {
            char now = file.charAt(index);
            if(Character.isDigit(now)) sb.append(now);
            else break;
        }
        ret.number = sb.toString();

        // tail(else)
        ret.tail = file.substring(index);

        return ret;
    }
}
