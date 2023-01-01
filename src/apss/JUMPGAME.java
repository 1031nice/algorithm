package apss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class JUMPGAME {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/APSS/APSS.inputs/input_JUMPGAME.txt"));
        Iterator<String> iterator = lines.iterator();
        int row = Integer.parseInt(iterator.next());
        int col = Integer.parseInt(iterator.next());
        int[][] board = new int[row][col];
        int[][] cache = new int[row][col];
        for(int j=0; j<row; j++){
            String[] tokens = iterator.next().split(" ");
            for(int k=0; k<col; k++){
                board[j][k] = Integer.parseInt(tokens[k]);
                cache[j][k] =  -1;
            }
        }

        System.out.println(func(board, cache, 0, 0));
    }

    private static int func(int[][] board, int[][] cache, int row, int col) {
        if(row >= board.length || col >= board[0].length){
            return 0;
        }
        else if(row == board.length-1 || col == board[0].length-1){
            return 1;
        }
        else if(cache[row][col] != -1) {
            return cache[row][col];
        }
        else{
            int ret = func(board, cache, row + board[row][col], col) + func(board, cache, row, col + board[row][col]);
            cache[row][col] = ret;
            return ret;
        }
    }

}
