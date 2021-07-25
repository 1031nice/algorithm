package programmers.level1;

public class 자연수뒤집어배열로만들기 {
}

// while문 돌면서 각각의 자릿수 구하는 것도 가능하겠지만
// 이런 것도 가능
class Solution5 {
    public int[] solution(long n) {
        String s = String.valueOf(n);
        Character[] arr = new Character[s.length()];
        for(int i=0; i<arr.length; i++) {
            arr[i] = s.charAt(i);
        }
        int[] answer = new int[arr.length];
        for(int i=0; i<arr.length; i++) {
            answer[i] = arr[arr.length-1-i] - '0';
        }
        return answer;
    }
}