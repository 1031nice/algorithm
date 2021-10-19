package hackerrank;

import java.util.List;

public class PickingNumbers {
    public static int pickingNumbers(List<Integer> a) {
        int max = -1;

        // select first number
        for(int i=0; i<a.size(); i++) {
            int first = a.get(i);
            int count = 1; // count for first

            // select second number
            for(int j=i+1; j<a.size(); j++) {
                int second = a.get(j);
                if(Math.abs(first - second) > 1) {
                    continue;
                }
                count++; // count for second

                // when first number and second number are fixed
                // others have no choice(should be first number or second number)
                int temp = count;
                for(int k=j+1; k<a.size(); k++) {
                    if(a.get(k) == first || a.get(k) == second) {
                        temp++; // count
                    }
                }
                if(temp > max) max = temp;

                count--; // drop now second number
            }
        }
        return max;
    }
}
