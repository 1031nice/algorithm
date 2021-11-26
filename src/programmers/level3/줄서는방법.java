package programmers.level3;

import java.util.ArrayList;

public class 줄서는방법 {
    static long[] cache = new long[21];

    public int[] solution(int n, long k) {
        ArrayList<Integer> list = new ArrayList<>();
        func(list, new boolean[n], n, n, k);
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    // final solution---------------------------------------
    public void func(ArrayList<Integer> answer, boolean[] visited,
                     int n, int nowN, long k) {
        if (k == 1) {
            for (int i = 0; i < n; i++)
                if (!visited[i])
                    answer.add(i + 1);
            return;
        }

        long f = factorial(nowN - 1);
        int quo = (int) (k / f + 1);
        if (k % f == 0) quo--;

        int i = 0;
        for (int j = 0; i < n && j < quo; i++) {
            if(!visited[i]) j++;
        }
        i--;

        visited[i] = true;
        answer.add(i + 1);
        func(answer, visited, n, nowN - 1, k - (f * (quo - 1)));
    }

    public long factorial(int number) {
        if(cache[number] != 0) {
            return cache[number];
        }
        else {
            long ret = 1;
            for(int i=1; i<=number; i++) {
                ret *= i;
            }
            return cache[number] = ret;
        }
    }


    // fail solution---------------------------------------
    // naive 보다 빠르게 풀려고 했으나 실패한 풀이, 접근은 맞았으나 구현하지 못함. 끝까지 풀지 않고 다시 처음부터 품
    public int[] failSolution(int n, long k) {
        boolean[] visited = new boolean[n+1];
        ArrayList<Integer> list = new ArrayList<>();
        while(true) {
            long a = factorial(n-1);
            int quo = (int)(k / a); // 몫

            long diff = k - a * quo;
            if(diff == 1) { // 맨 끝이므로 남은 숫자들을 내림차순으로 채워 넣는다
                list.add(quo);
                visited[quo] = true;
                for(int i=visited.length-1; i>=0; i--) {
                    if(!visited[i])
                        list.add(i);
                }
                break;
            }
            else {
                int temp = 1;
                int num = 0;
                for(int i=1; i<visited.length; i++) {
                    if(temp == quo + 1) {
                        num = i;
                        break;
                    }
                    else if(!visited[i])
                        temp++;
                }
                list.add(num);
                visited[num] = true;
                n--;
                k = diff;
            }
        }

        int[] answer = new int[visited.length-1];
        for(int i=0; i<answer.length; i++)
            answer[i] = list.get(i);
        return answer;
    }


    // naive solution---------------------------------------
     static int count = 0;

     public int[] naiveSolution(int n, long k) {
         return func(new ArrayList<Integer>(), new boolean[n], n, k);
     }

     public int[] func(ArrayList<Integer> list, boolean[] visited, int n, long k) {
         if(list.size() == n) {
             count++;
             if(count == k) {
                 int[] answer = new int[list.size()];
                 for(int i=0; i<list.size(); i++) {
                     answer[i] = list.get(i);
                 }
                 return answer;
             } else {
                 return null;
             }
         }

         int[] ret = null;
         for(int i=0; i<visited.length; i++) {
             if (!visited[i]){
                 visited[i] = true;
                 list.add(i+1);
                 int[] temp = func(list, visited, n, k);
                 if(temp != null) {
                     ret = temp;
                     break;
                 }
                 list.remove(list.size()-1);
                 visited[i] = false;
             }
         }

         return ret;
     }
}
