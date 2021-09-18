package programmers.level2;

public class 땅따먹기 {

    public static void main(String[] args) {
        System.out.println(new 땅따먹기().solution(new int[][]{{1,2,3,5},{5,6,7,8},{4,3,2,1}}));
    }

    int solution(int[][] land) {
        int[][] cache = new int[land.length][land[0].length];
        return func(land, cache, 0, 0, -1);
    }

    int func(int[][] land, int[][] cache, int row, int sum, int before) {
        int ret = 0;
        if(row >= land.length)
            return sum;
        for(int i=0; i<land[row].length; i++) {
            if(i != before) {
                int temp;
                if(cache[row][i] != 0) {
                    temp = cache[row][i];
                }
                else {
                    /*
                    sum에 자기 자신의 값을 더한 뒤 다음으로 넘기는게 아니라,
                    결과를 받은 뒤 자기 자신을 더해야 한다.
                     */
                    temp = func(land, cache, row+1, sum, i);
                    temp += land[row][i];
                    cache[row][i] = temp;
                }
                ret = Math.max(ret, temp);
            }
        }
        return ret;
    }

}
