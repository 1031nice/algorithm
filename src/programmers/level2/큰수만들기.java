package programmers.level2;

public class 큰수만들기 {
    public String solution(String number, int k) {
        int n = number.length();
        int start = 0;
        int end = k;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n-k; i++) {
            int maxIndex = findFirstMaxIndex(sb, number, start, end);
            start = maxIndex+1;
            end++;
        }
        return sb.toString();
    }

    public int findFirstMaxIndex(StringBuilder sb, String number, int start, int end) {
        int max = -1;
        int maxIndex = -1;
        for(int i=start; i<=end; i++) {
            int now = number.charAt(i) - '0';
            if(now > max) {
                max = now;
                maxIndex = i;
            }
        }
        sb.append(String.valueOf(max));
        return maxIndex;
    }
}
