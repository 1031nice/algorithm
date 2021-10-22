package hackerrank;

import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

/*
부등호 쓸 때 각별한 주의가 필요하다
 */

public class BiggerIsGreater {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("./src/hackerrank/inputs/biggerisgreater.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                String w = bufferedReader.readLine();
                String result = BiggerIsGreater.biggerIsGreater(w);
                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static String biggerIsGreater(String w) {
        int start = w.length()-1; // descending substring start index
        int end = w.length()-1; // descending substring end(include) index
        char before = w.charAt(w.length()-1);

        // rear -> front
        for(int i=1; i<w.length(); i++) {
            char now = w.charAt(w.length() - 1 - i);
            if(now < before) {
                break; // find the first number not fit in descending substring
            }
            else {
                start--;
                before = now;
            }
        }

        if(start == 0) { // all number is descending order
            return "no answer";
        }
        else {
            char target = w.charAt(start-1);
            String sub = w.substring(start, end+1);

            // find the min char larger than target
            int minIndex = 0;
            char min = 'z';
            for(int i=0; i<sub.length(); i++) {
                char now = sub.charAt(i);
                if(now < min && now > target) {
                    min = now;
                    minIndex = i;
                }
            }
            char[] chars = w.toCharArray();

            // swap
            char temp = chars[start-1];
            chars[start-1] = chars[start + minIndex];
            chars[start + minIndex] = temp;

            // sort
            w = String.valueOf(chars);
            sub = w.substring(start, end+1);
            char[] subChars = sub.toCharArray();
            Arrays.sort(subChars);

            // merge
            for(int i=start; i<w.length(); i++) {
                chars[i] = subChars[i-start];
            }
            return String.valueOf(chars);

        }
    }
}
