package programmers.level2;

public class n진수게임 {

    /*
    answer <- ""
    string <- ""
    number <- 0
    sum <- 0

    while(sum < m * t)
        newNumber <- number를 n진법의 수로 변환한다
        sum += newNumber의 자릿수
        string += newNumber
        number++;

    for(i=1; i<=t; i++)
        answer += string.charAt((p-1)*i)

    return answer
     */

    public String solution(int n, int t, int m, int p) {
        String answer = "";
        String string = "";
        int number = 0;
        int sum = 0;
        while(sum < m * t) {
            String newNumber = convert(n, number);
            System.out.println(newNumber);
            sum += newNumber.length();
            string += newNumber;
            number++;
        }
        System.out.println(string);
        for(int i=1; i<=t; i++) {
            answer += string.charAt(p + m * (i-1) - 1);
        }
        return answer;
    }

    public String convert(int n, int number) {
        StringBuilder sb = new StringBuilder();
        while(number > n-1) {
            int mod = number % n;
            sb.append(mod >= 10 ? numberToChar(mod) : mod + "");
            number /= n;
        }
        sb.append(number >= 10 ? numberToChar(number) : number + "");
        return sb.reverse().toString();
    }

    public char numberToChar(int number) {
        switch(number) {
            case 10: return 'A';
            case 11: return 'B';
            case 12: return 'C';
            case 13: return 'D';
            case 14: return 'E';
            case 15: return 'F';
            default: return '\0';
        }
    }

}
