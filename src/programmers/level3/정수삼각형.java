package programmers.level3;

public class 정수삼각형 {
    public int solution(int[][] triangle) {
        int size = triangle.length;
        int[][] matrix = new int[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<triangle[i].length; j++) {
                matrix[i][j] = triangle[i][j];
            }
        }
        int[][] cache = new int[size][size];
        return func(matrix, cache, 0, 0);
    }

    public int func(int[][] matrix, int[][] cache, int row, int col) {
        if(row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length) {
            return 0;
        }
        else if(cache[row][col] != 0) {
            return cache[row][col];
        }
        return cache[row][col] = matrix[row][col] +
                Math.max(func(matrix, cache, row+1, col), func(matrix, cache, row+1, col+1));
    }
}
