package programmers.level2;

public class 양궁대회 {

    static int MAX = -1;
    static int[] answer;

    public int[] solution(int n, int[] info) {
        answer = new int[info.length];
        func(info, new int[info.length], n, 0);
        return MAX == -1 ? new int[]{-1} : answer;
    }

    private void func(int[] other, int[] mine, int n, int index) {
        if(index >= other.length) {
            int diff = calcDiff(other, mine);
            if(diff >= MAX) {
                answer = diff > MAX ? mine.clone() : getLowerCase(mine, answer);
                answer[index-1] += Math.max(n, 0); // 남은 화살 소진
                MAX = diff;
            }
            return;
        }

        // 지금 점수에서 이기지 않는 경우
        func(other, mine.clone(), n, index + 1);

        // 지금 점수에서 이기는 경우
        int otherPoint = other[index];
        if(otherPoint < n) {
            mine[index] = otherPoint + 1; // 최소한으로 이긴다
            n -= (otherPoint + 1);
            func(other, mine, n, index + 1);
        }

    }

    private int[] getLowerCase(int[] a, int[] b) {
        for(int i=a.length-1; i>=0; i--) {
            if(a[i] == 0 && b[i] != 0) {
                return b.clone();
            } else if(a[i] != 0 && b[i] == 0) {
                return a.clone();
            }
        }
        return a.clone();
    }

    private int calcDiff(int[] others, int[] mines) {
        int me = 0;
        int other = 0;
        for(int i=0; i<mines.length; i++) {
            if(others[i] >= mines[i] && others[i] != 0) {
                other += 10-i;
            } else if(mines[i] > others[i]) {
                me += 10-i;
            }
        }
        return me > other ? me-other : -1;
    }

}