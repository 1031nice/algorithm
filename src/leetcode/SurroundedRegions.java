package leetcode;

public class SurroundedRegions {
    public static void main(String[] args) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.solve(new char[][]{{'X','X','X','X','X'},{'X','O','O','O','X'},{'X','X','O','O','X'},{'X','X','X','O','X'},{'X','O','X','X','X'}});
        surroundedRegions.solve(new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}});
        surroundedRegions.solve(new char[][]{{'O','X','O','O','O','O','O','O','O'},{'O','O','O','X','O','O','O','O','X'},{'O','X','O','X','O','O','O','O','X'},{'O','O','O','O','X','O','O','O','O'},{'X','O','O','O','O','O','O','O','X'},{'X','X','O','O','X','O','X','O','X'},{'O','O','O','X','O','O','O','O','O'},{'O','O','O','X','O','O','O','O','O'},{'O','O','O','O','O','X','X','O','O'}});
    }

    public void solve(char[][] board) {
        boolean[][] cache = new boolean[board.length][board[0].length];
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i=1; i<board.length-1; i++) {
            for(int j=1; j<board[i].length-1; j++) {
                if(board[i][j] == 'O' && !visited[i][j]) {
                    if(isFlippable(board, new boolean[board.length][board[0].length], i, j)) {
                        flip(board, new boolean[board.length][board[0].length], i, j);
                    }
                    else {
                        visit(board, visited, i, j);
                    }
                }
            }
        }
    }

    int[] dirRow = {-1, 0, 1, 0}; // up, right, down, left
    int[] dirCol = {0, 1, 0, -1};

    public boolean isFlippable(char[][] board, boolean[][] visited, int row, int col) {
        // 'O' on the border
        if((row == board.length-1 || row == 0 || col == 0 || col == board[row].length-1) && board[row][col] == 'O') {
            return false;
        }
        else if(board[row][col] == 'X') {
            return true;
        }
        else if(visited[row][col]) {
            return true;
        }

        visited[row][col] = true;
        for(int dir=0; dir<4; dir++) {
            int newRow = row + dirRow[dir];
            int newCol = col + dirCol[dir];
            if(!isFlippable(board, visited, newRow, newCol)) {
                return false;
            }
        }

        return true;
    }

    public void flip(char[][] board, boolean[][] visited, int row, int col) {
        if(row >= board.length-1 || row <= 0 || col >= board[row].length-1 || col <= 0) {
            return;
        }
        else if(board[row][col] == 'X') {
            return;
        }
        else if(visited[row][col]) {
            return;
        }

        board[row][col] = 'X';
        visited[row][col] = true;
        for(int dir=0; dir<4; dir++) {
            int newRow = row + dirRow[dir];
            int newCol = col + dirCol[dir];
            flip(board, visited, newRow, newCol);
        }
    }

    public void visit(char[][] board, boolean[][] visited, int row, int col) {
        if(row >= board.length-1 || row <= 0 || col >= board[row].length-1 || col <= 0) {
            return;
        }
        else if(board[row][col] == 'X') {
            return;
        }
        else if(visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        for(int dir=0; dir<4; dir++) {
            int newRow = row + dirRow[dir];
            int newCol = col + dirCol[dir];
            visit(board, visited, newRow, newCol);
        }
    }
}
