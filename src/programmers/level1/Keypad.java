package programmers.level1;

/*
keypad를 주어진대로 쓸 경우
3으로 나눈 나머지도 1, 2, 0으로 순서가 엉망이고
3으로 나눈 몫도 n, n, n+1로 엉망이므로
얘를 별도로 처리해주는 것보다 keypad 자체를 수정하는 게 더 나을 것 같다.
1, 2, 3
4, 5, 6
...
이 아니라
0, 1, 2
3, 4, 5
...
이렇게 되면 나머지도 0, 1, 2가 되고
한 row에 속하는 모든 숫자는 3으로 나눈 몫이 같기 때문에
거리를 구하는게 더 수월해진다
 */
public class Keypad {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Solution solution = new Solution();
        System.out.println(solution.solution(numbers, "right"));
    }
}

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int left = 9;
        int right = 11;
        for(int i=0; i<numbers.length; i++) {
            int now = numbers[i] == 0 ? 10 : numbers[i]-1;
            // 가운데 열 숫자
            if(now % 3 == 1) {
                // 왼쪽과의 거리
                int leftDistance = getDistance(now, left);
                // 오른쪽과의 거리
                int rightDistance = getDistance(now, right);
                // 거리가 같으면 hand에 따라
                if(leftDistance == rightDistance) {
                    if(hand.equals("right")) {
                        answer += "R";
                        right = now;
                    }
                    else {
                        answer += "L";
                        left = now;
                    }
                }
                // 거리가 다르다면 가까운 것으로 선택
                else if(leftDistance > rightDistance){
                    answer += "R";
                    right = now;
                }
                else {
                    answer += "L";
                    left = now;
                }
            }
            // 오른쪽 열 숫자
            else if(now % 3 == 2) {
                answer += "R";
                right = now;
            }
            // 왼쪽 열 숫자
            else if(now % 3 == 0) {
                answer += "L";
                left = now;
            }
        }
        return answer;
    }

    public int getDistance(int num1, int num2) {
        int row = Math.abs(num1/3 - num2/3);
        int col = Math.abs(num1%3 - num2%3);
        return row + col;
    }
}
