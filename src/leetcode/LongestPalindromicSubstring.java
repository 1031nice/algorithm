package leetcode;

public class LongestPalindromicSubstring {

    // v4
    public String longestPalindrome(String s) {
        int maxLen = 1;
        int[] maxIndexes = {0, 0};
        for(int i=0; i<s.length(); i++) {
            int[] odd = getPalindrome(s, i, i); // 홀수
            int oddLen = odd[1] - odd[0];
            maxIndexes = oddLen > maxLen ? odd : maxIndexes;
            maxLen = Math.max(oddLen, maxLen);

            int[] even = getPalindrome(s, i, i+1); // 짝수
            int evenLen = even[1] - even[0];
            maxIndexes = evenLen > maxLen ? even : maxIndexes;
            maxLen = Math.max(evenLen, maxLen);
        }
        return s.substring(maxIndexes[0], maxIndexes[1]);
    }

    public int[] getPalindrome(String s, int toLeft, int toRight) {
        for(; toLeft >= 0 && toRight < s.length(); toLeft--, toRight++) {
            if(s.charAt(toLeft) != s.charAt(toRight)) {
                break;
            }
        }
        return new int[]{toLeft+1, toRight};
    }

    // v3
//     public String longestPalindrome(String s) {
//         return func(s, s.length() - 1);
//     }

//     public String func(String s, int endIdx) {
//         if(endIdx == 0) {
//             return s.substring(0, 1);
//         }

//         String beforePalindrome = func(s, endIdx - 1);

//         for(int startIdx=0; startIdx < endIdx - beforePalindrome.length() + 1; startIdx++) {
//             boolean isPalindrome = true;
//             int len = endIdx - startIdx + 1;
//             for(int j=0; j < len/2; j++) {
//                 if(s.charAt(startIdx + j) != s.charAt(endIdx - j)) {
//                     isPalindrome = false;
//                     break;
//                 }
//             }
//             if(isPalindrome) return s.substring(startIdx, endIdx + 1);
//         }

//         return beforePalindrome;
//     }


    // v2
//     public String longestPalindrome(String s) {
//         String ret = s.substring(0, 1);
//         int len = s.length();
//         for(int i=0; i<len; i++) {
//             for(int j=len; j>i; j--) {
//                 int maxLen = len - i;
//                 if(ret.length() >= maxLen) {
//                     return ret;
//                 }
//                 String substr = s.substring(i, j);
//                 if(substr.length() > ret.length() && isPalindromic(substr)) {
//                     ret = substr;
//                 }
//             }
//         }
//         return ret;
//     }


    // v1
//     public String longestPalindrome(String s) {
//         String ret = s.substring(0, 1);
//         int len = s.length();
//         for(int i=0; i<len; i++) {
//             for(int j=i+1; j<len; j++) {
//                 String substr = s.substring(i, j+1);
//                 if(substr.length() > ret.length() && isPalindromic(substr)) {
//                     ret = substr;
//                 }
//             }
//         }
//         return ret;
//     }

//    public boolean isPalindromic(String str) {
//        for(int i=0; i<str.length() / 2; i++) {
//            if(str.charAt(i) != str.charAt(str.length() - 1 - i)) {
//                return false;
//            }
//        }
//        return true;
//    }

}
