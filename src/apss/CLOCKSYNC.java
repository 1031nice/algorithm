package apss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class CLOCKSYNC {

    public static int[][] switches = {
            {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,0},
            {0,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1},
            {1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,0,1,0,1,0,0,0},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1},
            {0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1},
            {0,0,0,0,1,1,0,1,0,0,0,0,0,0,1,1},
            {0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,0,0,0,1,0,0,0,1,0,0}
    };

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/APSS/APSS.inputs/input_CLOCKSYNC.txt"));
        Iterator<String> iterator = lines.iterator();
        int testCases = Integer.parseInt(iterator.next());
        for(int j=0; j<testCases; j++) {
            String line = iterator.next();
            String[] tokens = line.split(" ");
            int[] clocks = new int[tokens.length];
            for(int i=0; i<tokens.length; i++){
                clocks[i] = Integer.parseInt(tokens[i]);
            }

            System.out.println(func(clocks, 0, 0));
        }
    }

    private static int func(int[] clocks, int indexOfSwitches, int numOfPush) {
        if(indexOfSwitches >= switches.length) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        for(int i=0; i<4; i++){ // 같은 버튼을 최대 3회 누를 수 있다
            if(checkClocks(clocks))
                return numOfPush;
            if(i != 0){
                push(clocks, indexOfSwitches);
                numOfPush++;
                if(checkClocks(clocks))
                    return numOfPush;
            }
//            for(int j=indexOfSwitches+1; j<switches.length; j++){
//                int ret = func(clocks, j, numOfPush);
//                if(ret > 0) {
//                    min = Math.min(min, ret);
//                }
//            }
            int ret = func(clocks, indexOfSwitches+1, numOfPush);
            if(ret > 0)
                min = Math.min(min, ret);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static void push(int[] clocks, int indexOfSwitches) {
        for(int i=0; i<clocks.length; i++){
            if(switches[indexOfSwitches][i] == 1) {
                clocks[i] += 3;
                if(clocks[i] > 12)
                    clocks[i] %= 12;
            }
        }

    }

    private static boolean checkClocks(int[] clocks) {
        for(int i=0; i<clocks.length; i++){
            if(clocks[i] != 12)
                return false;
        }
        return true;
    }

}
