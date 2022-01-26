package programmers.level2;

public class k진수에서소수개수구하기 {
    public int solution(int n, int k) {
        int answer = 0;
        String strNum = convert(n, k);
        String[] split = strNum.split("0+");
        for(String str : split) {
            if(!str.isEmpty() && isPrime(Long.parseLong(str))) answer++;
        }
        return answer;
    }

    String convert(int number, int k) {
        StringBuilder sb = new StringBuilder();
        while(number > k-1) {
            sb.append(number % k);
            number /= k;
        }
        sb.append(number);
        return sb.reverse().toString();
    }

    boolean isPrime(long number) {
        if(number <= 1) return false;
        else if(number == 2) return true;
        for(long i=3; i*i<=number; i++) {
            if(number % i == 0) return false;
        }
        return true;
    }
}
