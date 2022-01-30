package programmers.level2;

/*
변화가 없을 때까지 반복
    1. 같은 모양의 2 by 2 블록 찾기
    2. 같은 모양의 2 by 2 블록 지우기
*/

public class 프렌즈4블록 {

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] _board = new char[board.length][];
        for(int i=0; i<board.length; i++) {
            _board[i] = board[i].toCharArray();
        }

        while(true) {
            boolean notChanged = true;
            boolean[][] shouldBeRemoved = new boolean[_board.length][_board[0].length];
            for(int i=0; i<_board.length; i++) {
                for(int j=0; j<_board[i].length; j++) {
                    if(_board[i][j] == '\0') continue;
                    int result = getSameBlockCount(_board, shouldBeRemoved, i, j);
                    if(result != 0) notChanged = false;
                    answer += result;
                }
            }
            if(notChanged) break;
            else cleanUp(_board, shouldBeRemoved);
        }

        return answer;
    }

    public int getSameBlockCount(char[][] board, boolean[][] shouldBeRemoved, int row, int col) {
        int count = 0;

        if(row+1 >= board.length || col+1 >= board[0].length) return count;

        char leftUp = board[row][col];
        char leftDown = board[row+1][col];
        char rightUp = board[row][col+1];
        char rightDown = board[row+1][col+1];

        if(leftUp == leftDown && leftDown == rightUp && rightUp == rightDown) {
            count += shouldBeRemoved[row][col] ? 0 : 1;
            count += shouldBeRemoved[row+1][col] ? 0 : 1;
            count += shouldBeRemoved[row][col+1] ? 0 : 1;
            count += shouldBeRemoved[row+1][col+1] ? 0 : 1;

            shouldBeRemoved[row][col] = shouldBeRemoved[row+1][col] =
                    shouldBeRemoved[row][col+1] = shouldBeRemoved[row+1][col+1] = true;
        }

        return count;
    }

    public void cleanUp(char[][] board, boolean[][] shouldBeRemoved) {
        for(int col=0; col<board[0].length; col++) {
            for(int row=board.length-1; row>=0; row--) {
                if(shouldBeRemoved[row][col]) { // 삭제 대상이라면
                    boolean isReplaced = replaceWithNextBlock(board, shouldBeRemoved, row, col);
                    if(!isReplaced) board[row][col] = '\0'; // 다음 블록이 없으면 바로 제거
                }
            }
        }
    }

    private boolean replaceWithNextBlock(char[][] board, boolean[][] shouldBeRemoved, int row, int col) {
        int i = 1;
        boolean isReplaced = false;
        while(row-i >= 0) {
            if(!shouldBeRemoved[row-i][col]) { // 멀쩡한 블록이 있으면
                board[row][col] = board[row-i][col];
                shouldBeRemoved[row-i][col] = true; // 그 블록을 삭제 대상으로 변경
                isReplaced = true;
                break;
            }
            i++;
        }
        return isReplaced;
    }
}