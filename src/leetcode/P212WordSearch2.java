package leetcode;

import java.util.ArrayList;
import java.util.List;

public class P212WordSearch2 {

    /*
    단어가 board에 있는 알파벳으로 만들어질 수 있는지 대충(빈도 검사 없이) 검사해도 훨씬 빨라진다
     */
    public List<String> findWords(char[][] board, String[] words) {
        boolean[] visited = new boolean[26];
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                visited[board[i][j] - 'a'] = true;
            }
        }
        List<String> ret = new ArrayList<>();
        for(String word : words) {
            boolean maybeExist = true;
            for(int i=0; i<word.length(); i++) {
                if (!visited[(word.charAt(i) - 'a')]) {
                    maybeExist = false;
                    break;
                }
            }
            if(maybeExist && doesExist(board, word)) ret.add(word);
        }
        return ret;
    }

    private boolean doesExist(char[][] board, String word) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    if(doesExistRecursively(board, visited, i, j, word, 0)) return true;
                }
            }
        }
        return false;
    }

    int[] dirRow = {-1, 0, 1, 0};
    int[] dirCol = {0, 1, 0, -1};

    private boolean doesExistRecursively(char[][] board, boolean[][] visited,
                                         int row, int col, String word, int index) {
        if(row >= board.length || row < 0 || col >= board[row].length || col < 0) {
            return false;
        }
        else if(visited[row][col]) {
            return false;
        }
        char now = board[row][col];
        if(now != word.charAt(index)) {
            return false;
        }
        else if(index == word.length() - 1){
            return true;
        }
        else {
            visited[row][col] = true;
            boolean ret = false;
            for(int dir=0; dir<4; dir++) {
                int newRow = row + dirRow[dir];
                int newCol = col + dirCol[dir];
                ret = ret || doesExistRecursively(board, visited, newRow, newCol, word, index+1);
            }
            visited[row][col] = false;
            return ret;
        }
    }
}
