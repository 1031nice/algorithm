package programmers.level2;

public class 피로도 {
    public static void main(String[] args) {
        System.out.println(new 피로도().solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}}));
    }

    public int solution(int k, int[][] dungeons) {
        return func(k, dungeons, new boolean[dungeons.length], 0);
    }

    public int func(int k, int[][] arr, boolean[] visited, int count) {
        int ret = count;

        for (int i = 0; i < arr.length; i++) {
            int min = arr[i][0];
            int cost = arr[i][1];
            if (!visited[i] && k >= min) {
                visited[i] = true;
                k -= cost;
                ret = Math.max(ret, func(k, arr, visited, count + 1));
                k += cost;
                visited[i] = false;
            }
        }

        return ret;
    }
}