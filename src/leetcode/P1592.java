package leetcode;

import java.util.ArrayList;

public class P1592 {
    public String reorderSpaces(String text) {
        int nSpace = 0;
        int nWord = 0;
        ArrayList<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<text.length(); i++) {
            char now = text.charAt(i);
            if(Character.isAlphabetic(now)) {
                sb.append(now);
            }
            else {
                nSpace++;
                if(sb.length() != 0) {
                    words.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        if(sb.length() != 0) {
            words.add(sb.toString());
            sb.setLength(0);
        }
        nWord = words.size();

        int quo = nWord > 1 ? nSpace / (nWord-1) : 0;
        String space = " ".repeat(quo);
        int mod = nWord > 1 ? nSpace % (nWord-1) : nSpace;
        String extra = " ".repeat(mod);

        for(int i=0; i<nWord; i++) {
            sb.append(words.get(i)).append(i == nWord-1 ? extra : space);
        }

        return sb.toString();
    }
}
