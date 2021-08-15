package programmers.level2;

import java.util.Arrays;

public class 행렬테두리회전하기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 행렬테두리회전하기().solution(3, 3,
                new int[][]{{1,1,2,2},{1,2,2,3,},{2,1,3,2},{2,2,3,3}})));
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] matrix = new int[rows][columns];
        int count = 1;
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                matrix[i][j] = count++;
            }
        }
        int[] answer = new int[queries.length];
        for(int j=0; j<queries.length; j++) {
            int r1 = queries[j][0] - 1;
            int c1 = queries[j][1] - 1;
            int r2 = queries[j][2] - 1;
            int c2 = queries[j][3] - 1;
            int save = matrix[r1][c1];
            int min = save;
            // r1,c1 -> r2,c1
            // r2,c1 -> r2,c2
            // r2,c2 -> r1,c2
            // r1,c2 -> r1,c1

            // 직사각형이 아니라 테두리를 쭉 폈다고 생각하고 직선으로 여긴뒤에
            // 각각의 위치를 1차원 배열에 담으면 반복문 하나로도 처리 가능할 듯
            for(int i=1; i<=r2-r1; i++) {
                matrix[r1+i-1][c1] = matrix[r1+i][c1];
                min = Math.min(matrix[r1+i-1][c1], min);
            }
            for(int i=1; i<=c2-c1; i++) {
                matrix[r2][c1+i-1] = matrix[r2][c1+i];
                min = Math.min(matrix[r2][c1+i-1], min);
            }
            for(int i=1; i<=r2-r1; i++) {
                matrix[r2-i+1][c2] = matrix[r2-i][c2];
                min = Math.min(matrix[r2-i+1][c2], min);
            }
            for(int i=1; i<=c2-c1; i++) {
                matrix[r1][c2-i+1] = matrix[r1][c2-i];
                min = Math.min(matrix[r1][c2-i+1], min);
            }
            matrix[r1][c1+1] = save;
            answer[j] = min;
        }
        return answer;
    }
}
