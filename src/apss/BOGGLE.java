package apss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class BOGGLE {

    static int[] rowDir = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] colDir = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/APSS/APSS.inputs/input_BOGGLE.txt"));
        ListIterator<String> iterator = lines.listIterator();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char[][] board = new char[5][5];
        int row = 0;

        // input
        while(iterator.hasNext()){
            String next = iterator.next();
            for(int col=0; col<next.length(); col++){
                board[row][col] = next.charAt(col);
            }
            row++;
        }

        // debug
        for(int i=0; i<5; i++){
            System.out.println(Arrays.toString(board[i]));
        }

        // algorithm
        for(row=0; row<board.length; row++){
            for(int col=0; col<board[0].length; col++){
                if(find(board, row, col, input)){
                    System.out.println("FOUND");
                    return;
                }
            }
        }
        System.out.println("NOT FOUND");
    }

    private static boolean find(char[][] board, int row, int col, String input) {
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return false;
        else if(input.charAt(0) != board[row][col])
            return false;
        else {
            if(input.length() == 1)
                return true;
            for(int dir=0; dir<8; dir++){
                if(find(board, row+rowDir[dir], col+colDir[dir], input.substring(1)))
                    return true;
            }
            return false;
        }
    }

}
