package programmers.level2;

public class 수식최대화 {

    public static void main(String[] args) {
        System.out.println(new 수식최대화().solution("100-200*300-500+20"));
    }

    static String[][] combinations = {{"\\+", "-", "\\*"},
                            {"\\+", "\\*", "-"},
                            {"-", "\\+", "\\*"},
                            {"-", "\\*", "\\+"},
                            {"\\*", "\\+", "-"},
                            {"\\*", "-", "\\+"}};

    public long solution(String expression) {
        // 모든 가능한 연산자 우선순위를 만든 뒤 func를 호출한다
        long max = -1;
        String[] operators = new String[3];
        boolean[] visited = new boolean[3];
        for (String[] combination : combinations) {
            long temp = func(expression, combination);
            max = Math.max(max, Math.abs(temp));
        }
        return max;
    }

    // func가 할 일은 연산자 우선순위에 따라 expression을 연산하고 그 결과를 돌려준다
    public long func(String expression, String[] operators) {
        if(isNumeric(expression))
            return Long.parseLong(expression);
        boolean[] visited = new boolean[3];
        long ret = 0;
        for (String operator : operators) {
            String[] split = expression.split(operator);
            if (split.length == 1) // 해당 연산자가 존재하지 않음
                continue;
            ret = func(split[0], operators);
            for (int j = 1; j < split.length; j++) {
                switch (operator) {
                    case "\\*":
                        ret *= func(split[j], operators);
                        break;
                    case "\\+":
                        ret += func(split[j], operators);
                        break;
                    case "-":
                        ret -= func(split[j], operators);
                        break;
                }
            }
            // 수식도 변할리 없고 한 번 주어진 것에서 바뀌지 않으므로 주어진 수식에서 사용해야 할 연산자는 하나 밖에 없으니 한 번만 수행되면 바로 끝낸다
            break;
        }
        return ret;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null)
            return false;
        try {
            long l = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
