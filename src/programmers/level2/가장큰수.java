package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;

public class 가장큰수 {

    public static void main(String[] args) {
        System.out.println(new 가장큰수().solution(new int[]{1,2,3,4,5,9,1}));
    }

    static class MyComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            String a = s1+s2;
            String b = s2+s1;
            return -a.compareTo(b);
        }

        // 처음 접근한 방식: 두 문자열 각각을 비교하려고 했다.
        // 두 문자열의 크고 작음의 기준이 문자열 자체가 아니라
        // 어떤 문자열이 먼저 왔을 때 그 합이 더 큰지가 기준인데,
        // 문자열 두 개를 비교하며 합쳤을 때 더 큰 숫자가 되도록 우선순위를 갖게할 규칙을 찾으려고 하면 이상해진다.
        // 두 문자열을 A+B, 한 번은 B+A로 합쳐보고 이 둘을 비교하면 더 쉽다.
        /*public int compare(String s1, String s2) {
            int index = 0;
            while(index < s2.length() && index < s1.length()) {
                if(s2.charAt(index) > s1.charAt(index))
                    return 1;
                else if(s2.charAt(index) < s1.charAt(index))
                    return -1;
                index++;
            }
            int last = index-1;
            while(index < s1.length() && index >= s2.length()) {
                if(s2.charAt(last) > s1.charAt(index))
                    return 1;
                else if(s2.charAt(last) < s1.charAt(index))
                    return -1;
                index++;
            }
            while(index < s2.length() && index >= s1.length()) {
                if(s1.charAt(last) > s2.charAt(index))
                    return 1;
                else if(s1.charAt(last) < s2.charAt(index))
                    return -1;
                index++;
            }
            return 0;
        }*/

    }

    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        boolean isAllZero = true;
        String[] strNums = new String[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i] != 0)
                isAllZero = false;
            strNums[i] = String.valueOf(numbers[i]);
        }

        // 문자열을 리턴하지만 의미적으로는 숫자이기 때문에
        // 길이가 2 이상이고 각 자리의 숫자가 0인 숫자("00", "000", ...)는 있을 수 없다.
        // (하나라도 0이 아닌 수가 있다면 그 숫자가 가장 앞에 올 것이므로 모두 0인 경우만 신경쓰면 된다)
        if(isAllZero)
            return "0";

        Arrays.sort(strNums, new MyComparator());
        for(int i=0; i<strNums.length; i++) {
            sb.append(strNums[i]);
        }
        return sb.toString();
    }
}
