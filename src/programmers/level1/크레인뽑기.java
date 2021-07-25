package programmers.level1;
import java.util.Stack;

public class 크레인뽑기 {



}

class Solution7 {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();
        int ret = 0;
        for(int i=0; i<moves.length; i++) {
            int topDoll = getTopDollAndRemove(board, moves[i]);
            if(topDoll == -1)
                continue;

            if(!stack.isEmpty() && stack.peek() == topDoll) {
                ret += 2;
                stack.pop();
            }
            else
                stack.push(topDoll);
        }
        return ret;
    }

    public int getTopDollAndRemove(int[][] board, int move) {
        int ret = -1;
        for(int i=0; i<board.length; i++) {
            if(board[i][move-1] != 0) {
                ret = board[i][move-1];
                board[i][move-1] = 0; // remove
                break;
            }
        }
        return ret;
    }
}
