package leetcode;

import java.util.ArrayList;

public class LargestOddNumberInString {
    public String largestOddNumberFastest(String num) {
        for(int i=num.length()-1; i>=0; i--) {
            if((num.charAt(i) - '0') % 2 == 1) {
                return num.substring(0, i+1);
            }
        }

        return "";
    }

    public String largestOddNumberFaster(String num) {
        ArrayList<Integer> oddNumIndexList = new ArrayList<>();

        for(int i=num.length()-1; i>=0; i--) {
            if((num.charAt(i) - '0') % 2 == 1) {
                oddNumIndexList.add(i);
            }
        }

        if(oddNumIndexList.isEmpty()) return "";
        else return num.substring(0, oddNumIndexList.get(oddNumIndexList.size()-1) + 1);
    }

    public String largestOddNumber(String num) {
        String max = "";
        boolean found = false;
        for(int len=num.length(); len>=1; len--) { // length
            for(int i=0; i<num.length(); i++) { // start point
                if(i+len > num.length()) break;
                String substr = num.substring(i, i+len);
                boolean isOdd = (substr.charAt(substr.length()-1) - '0') % 2 == 1;
                if(isOdd && substr.compareTo(max) > 0) {
                    max = substr;
                    found = true;
                }
            }
            if(found) break;
        }
        return max;
    }
    
}
