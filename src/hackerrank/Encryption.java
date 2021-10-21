package hackerrank;

// medium
/*
- 문제에서 원하는 것을 구하기 위해 문제에서 설명한 방식을 꼭 따라야 하는 것은 아니다
문제는 추상화되어 있다. 구현시에는 문제의 설명보다도 좋은 방법이 있을 수 있다.
문제에서는 문자열을 행렬에 넣은 뒤 column 기준으로 읽어내면 암호화된 문자열을 얻을 수 있다고 설명하지만,
실제로는 행렬을 만들어 문자열을 넣지 않고도 곱셈 연산을 통해 같은 효과를 낼 수 있다.

- 행렬은 나누기(곱하기) 연산, 나머지 연산과 매우 관련이 깊다.
 */
public class Encryption {

    public static String encryption(String s) {
        s = s.trim();
        int len = s.length();
        int col = -1;
        for(int i=1; i<=9; i++) {
            if(i*i >= len) {
                col = i;
                break;
            }
        }

        StringBuilder answer = new StringBuilder();
        int count = 0;
        for(int i=0; i<col; i++) {
            while(i + (count * col) < len) {
                answer.append(s.charAt(i + (count++ * col)));
            }
            answer.append(" ");
            count = 0;
        }

        return answer.toString();
    }

    public static String naiveEncryption(String s) {
        s = s.trim();
        int len = s.length();
        int row = -1;
        int col = -1;
        for(int i=1; i<=9; i++) {
            if(i*i >= len) {
                col = i;
                row = len > (i-1)*i ? i : i-1;
                break;
            }
        }

        char[][] matrix = new char[row][col];
        int count = 0;
        boolean conti = true;
        for(int r=0; r<row; r++) {
            for(int c=0; c<col; c++) {
                if(count >= len) {
                    conti = false;
                    break;
                }
                matrix[r][c] = s.charAt(count++);
            }
            if(!conti) break;
        }

        String ret = "";
        for(int c=0; c<col; c++) {
            for(int r=0; r<row; r++) {
                if(matrix[r][c] == '\0') break;
                ret += matrix[r][c];
            }
            ret += " ";
        }
        return ret;
    }

}
