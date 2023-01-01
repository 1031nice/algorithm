package apss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class QUADTREE {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/APSS/APSS.inputs/input_QUADTREE.txt"));
        Iterator<String> iterator = lines.iterator();
        int testCases = Integer.parseInt(iterator.next());

        for(int i=0; i<testCases; i++){
            String input = iterator.next();
            System.out.println(func(input));
        }
    }

    private static String func(String input) {
        String[] ret = {"", "", "", ""};
        int retIndex = 0;
        int index = 0;
        while(index < input.length() && retIndex < 4) {
            if (input.charAt(index) == 'x') {
                String part = func(input.substring(index+1));
                ret[retIndex++] = "x" + part;
                index += part.length()+1;
            }
            else {
                ret[retIndex++] = String.valueOf(input.charAt(index++));
            }
        }
        return ret[2] + ret[3] + ret[0] + ret[1];
    }

}
