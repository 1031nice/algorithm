package apss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;

public class BOARDCOVER {

    // 도형의 종류가 하나의 for문
    // 도형을 선택했을 때 예를 들어, L 모양이면 위, 지금, 오른쪽이 도형을 차지하는 포인트이므로 각각의 포인트를 배열로, 이 세개의 포인트를 또 배열로
    // 사실 2차 배열이면 충분할듯
    public static int[][][] coverType = {
            {{0, 0}, {-1, 0}, {0, 1}},
            {{0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {1, 0}, {0, -1}},
            {{0, 0}, {-1, 0}, {0, -1}}
    };

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/APSS.inputs/boardcover.txt"));
        ListIterator<String> iterator = lines.listIterator();

        int numOfTestCases = Integer.parseInt(iterator.next());
        int [] answers = new int[numOfTestCases];
        int row;
        int col;
        char [][] arr;

        for(int i=0; i<numOfTestCases; i++){
            row = Integer.parseInt(iterator.next());
            col = Integer.parseInt(iterator.next());
            arr = new char [row][col];
            int count = 0;

            for(int p=0; p<row; p++){
                String line;
                while(true){
                    line = iterator.next();
                    if(!line.isEmpty()) break;
                }
                for(int q=0; q<col; q++){
                    arr[p][q] = line.charAt(q);
                    if(arr[p][q] == '.') count++;
                }
            }

            if(count % 3 != 0)
                answers[i] = 0;
            else {
                answers[i] = func(arr, 0, count);
            }
        }

        for(int i=0; i<answers.length; i++)
            System.out.println(answers[i]);
    }

    private static int func(char[][] arr, int index, int count) {
        if(count == 0) // 모두선택
            return 1;
        else if(index >= arr.length * arr[0].length)
            return 0;
        else if(check(arr, index))
            return 0;
        else { // recursive
            int ret = 0;
            for(int dir=1; dir<=4; dir++){ // dir: n사분면
                if (set(arr, index, dir, '#')) { // pick
                    ret += func(arr, index+1, count - 3);
                    set(arr, index, dir, '.'); // unpick
                }
            }
            ret += func(arr, index+1, count);
            return ret;
        }
    }

    // 최적화를 위한 부분
    private static boolean check(char[][] arr, int index) {
        int row = index / arr[0].length;
        if(row < 2)
            return false;
        else{
            for(int i=0; i<arr[0].length; i++){
                if(arr[row-2][i] == '.')
                    return true;
            }
            return false;
        }
    }

//    private static void unpick(char[][] arr, int index, int type) {
//        int row = index / arr[0].length;
//        int col = index % arr[0].length;
//        for(int i=0; i<3; i++){
//            arr[row + coverType[type-1][i][0]][col + coverType[type-1][i][1]] = '.';
//        }
////        switch (type) {
////            case 1:
////                arr[row][col] = '.';
////                arr[row - 1][col] = '.';
////                arr[row][col + 1] = '.';
////                break;
////            case 2:
////                arr[row][col] = '.';
////                arr[row + 1][col] = '.';
////                arr[row][col + 1] = '.';
////                break;
////            case 3:
////                arr[row][col] = '.';
////                arr[row + 1][col] = '.';
////                arr[row][col - 1] = '.';
////                break;
////            case 4:
////                arr[row][col] = '.';
////                arr[row - 1][col] = '.';
////                arr[row][col - 1] = '.';
////                break;
////        }
//    }

    private static boolean set(char[][] arr, int index, int type, char delta) {
        int row = index / arr[0].length;
        int col = index % arr[0].length;
        for(int i=0; i<3; i++) {
            int newRow = row + coverType[type - 1][i][0];
            int newCol = col + coverType[type - 1][i][1];
            if(newRow >= arr.length || newRow < 0 || newCol >= arr[0].length || newCol < 0)
                return false;
            if(arr[newRow][newCol] == delta)
                return false;
        }
        for(int i=0; i<3; i++){
            arr[row + coverType[type-1][i][0]][col + coverType[type-1][i][1]] = delta;
        }
        return true;
//        switch (type) {
//            case 1:
//                if (row - 1 < 0 || col + 1 >= arr[0].length || arr[row][col] == '#' || arr[row-1][col] == '#' || arr[row][col+1] == '#')
//                    return false;
//                else {
//                    arr[row][col] = '#';
//                    arr[row - 1][col] = '#';
//                    arr[row][col + 1] = '#';
//                    return true;
//                }
//            case 2:
//                if (row + 1 >= arr.length || col + 1 >= arr[0].length || arr[row][col] == '#' || arr[row+1][col] == '#' || arr[row][col+1] == '#')
//                    return false;
//                else {
//                    arr[row][col] = '#';
//                    arr[row + 1][col] = '#';
//                    arr[row][col + 1] = '#';
//                    return true;
//                }
//            case 3:
//                if (row + 1 >= arr.length || col - 1 < 0 || arr[row][col] == '#' || arr[row+1][col] == '#' || arr[row][col-1] == '#')
//                    return false;
//                else {
//                    arr[row][col] = '#';
//                    arr[row + 1][col] = '#';
//                    arr[row][col - 1] = '#';
//                    return true;
//                }
//            case 4:
//                if (row - 1 < 0 || col - 1 < 0 || arr[row][col] == '#' || arr[row-1][col] == '#' || arr[row][col-1] == '#')
//                    return false;
//                else {
//                    arr[row][col] = '#';
//                    arr[row - 1][col] = '#';
//                    arr[row][col - 1] = '#';
//                    return true;
//                }
//            default:
//                return false;
//        }
    }

}
