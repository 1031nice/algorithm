package apss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class WILDCARD {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/APSS/APSS.inputs/input_WILDCARD.txt"));
        Iterator<String> iterator = lines.iterator();
        int TEST_CASES = Integer.parseInt(iterator.next());
        for(int i=0; i<TEST_CASES; i++){
            String pattern = iterator.next();
            int INPUTS = Integer.parseInt(iterator.next());
            for(int j=0; j<INPUTS; j++){
                String input = iterator.next();
                if(func(pattern, input))
                    System.out.println(input);
            }
        }
    }

    private static boolean func(String pattern, String input) {
        // base case
        if(isAllWildCard(pattern))
            return true;
        if(input.length() == 0) {
            return pattern.length() == 0;
        }
        else if(pattern.length() == 0){
            return false;
        }

        // recursive case
        else {
            char patternFirst = pattern.charAt(0);
            char inputFirst = input.charAt(0);

            if(patternFirst == '*') {
                int indexOfFirstChar = pattern.length() - 1;
                char firstChar = '\0';
                for (int i = 0; i < pattern.length(); i++) {
                    if (pattern.charAt(i) != '*') { // 반드시 들어올 수밖에 없음
                        indexOfFirstChar = i;
                        firstChar = pattern.charAt(i);
                        break;
                    }
                }

                int[] indexOfMatchedChar = new int[input.length()];
                int numOfMatchedChar = 0;
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) == firstChar) {
                        indexOfMatchedChar[numOfMatchedChar++] = i;
                    }
                }

                for (int i = 0; i < numOfMatchedChar; i++) {
                    if (func(pattern.substring(indexOfFirstChar), input.substring(indexOfMatchedChar[i])))
                        return true;
                }
                return false;
            }

            else if(patternFirst == '?'){
                return func(pattern.substring(1), input.substring(1));
            }

            else {
                if(patternFirst != inputFirst)
                    return false;
                else
                    return func(pattern.substring(1), input.substring(1));
            }
        }

    }

    private static boolean isAllWildCard(String pattern) {
        if(pattern.length() == 0)
            return false;
        for(int i=0; i<pattern.length(); i++){
            if(pattern.charAt(i) != '*')
                return false;
        }
        return true;
    }

}
