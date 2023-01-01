package apss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class TRIANGLEPATH {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/APSS/APSS.inputs/input_TRIANGLEPATH.txt"));
        Iterator<String> iterator = lines.iterator();
        String[] rowAndCol = iterator.next().split(" ");
        int row = Integer.parseInt(rowAndCol[0]);
        int col = Integer.parseInt(rowAndCol[1]);
        int[][] board = new int[row][col];
        int[][] cache = new int[row][col];
        for(int i=0; i<row; i++){
            String[] split = iterator.next().split(" ");
            for(int j=0; j<i+1; j++){
                board[i][j] = Integer.parseInt(split[j]);
            }
        }

        System.out.println(solve(board, cache, 0, 0));
    }

    private static int solve(int[][] board, int[][] cache, int row, int col) {
        if(row >= board.length || col >= board[0].length) // 작을 순 없음
            return 0;
        else if(row == board.length-1)
            return board[row][col];
        else if(cache[row][col] != 0)
            return cache[row][col];
        else {
            int ret = Math.max(solve(board, cache, row+1, col), solve(board, cache, row+1, col+1)) + board[row][col];
            cache[row][col] = ret;
            return ret;
        }
    }

}
