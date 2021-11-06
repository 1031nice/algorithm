package programmers.level2;

import java.util.Arrays;

public class 쿼드압축후개수세기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 쿼드압축후개수세기().solution(new int[][] {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}})));
    }

    static int zero = 0;
    static int one = 0;

    public int[] solution(int[][] arr) {
        compress(arr, 0, 0, arr.length);
        return new int[] {zero, one};
    }

    public void compress(int[][] arr, int leftUpRow, int leftUpCol, int length) {
        if(length == 1) {
            if(arr[leftUpRow][leftUpCol] == 0) {
                zero++;
            } else {
                one++;
            }
            return;
        }

        int value = arr[leftUpRow][leftUpCol];
        boolean isAllSame = true;
        for(int row=0; row<length; row++) {
            if(!isAllSame) {
                break;
            }
            for(int col=0; col<length; col++) {
                if(value != arr[leftUpRow + row][leftUpCol + col]) {
                    isAllSame = false;
                    break;
                }
            }
        }

        if(isAllSame) {
            if(value == 0) {
                zero++;
            } else {
                one++;
            }
            return;
        }

        compress(arr, leftUpRow, leftUpCol, length / 2);
        compress(arr, leftUpRow, leftUpCol + length / 2, length / 2);
        compress(arr, leftUpRow + length / 2, leftUpCol, length / 2);
        compress(arr, leftUpRow + length / 2, leftUpCol + length / 2, length / 2);
    }

}
