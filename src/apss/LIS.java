package apss;

public class LIS {

    public static void main(String[] args) {
        String numbers = "13424";
        System.out.println(solve(numbers));
    }

    static String solve(String numbers) {
        String max = "";
        for (int i = 0; i < numbers.length(); i++) {
            String solve = func(numbers.substring(i));
            if (solve.length() > max.length())
                max = solve;
        }
        return max;
    }

    static String func(String numbers) {
        if(numbers.length() <= 1)
            return numbers;

        int index = -1;
        String max = "";
        for (int i = 1; i < numbers.length(); i++) {
            if(Integer.parseInt(String.valueOf(numbers.charAt(i))) > Integer.parseInt(String.valueOf(numbers.charAt(0)))) {
                index = i;
                String temp = solve(numbers.substring(index));
                if(temp.length() > max.length())
                    max = temp;
            }
        }

        if(index < 0)
            return String.valueOf(numbers.charAt(0));
        return numbers.charAt(0) + solve(numbers.substring(index));
    }

}
