package programmers.level1;

public class NaiveKeypad {

}

class Solution1 {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int left = 10;
        int right = 12;
        for(int i=0; i<numbers.length; i++) {
            // 가운데 열 숫자
            if(numbers[i] == 0 || numbers[i] % 3 == 2) {
                int now = numbers[i] == 0 ? 11 : numbers[i];
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
            else if(numbers[i] % 3 == 0) {
                answer += "R";
                right = numbers[i];
            }
            // 왼쪽 열 숫자
            else if(numbers[i] % 3 == 1) {
                answer += "L";
                left = numbers[i];
            }
        }
        return answer;
    }

    public int getDistance(int num1, int num2) {
        int row = Math.abs(getRow(num1) - getRow(num2));
        int col = Math.abs(getCol(num1) - getCol(num2));
        return row + col;
    }

    public int getCol(int num) {
        if(num % 3 == 0)
            return 3;
        return num % 3;
    }

    public int getRow(int num) {
        if(num <= 3)
            return 1;
        else if(num <= 6)
            return 2;
        else if(num <= 9)
            return 3;
        return 4;
    }
}