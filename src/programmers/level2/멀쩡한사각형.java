package programmers.level2;

import java.util.Random;

public class 멀쩡한사각형 {
    public static void main(String[] args) {
        멀쩡한사각형 sol = new 멀쩡한사각형();
        System.out.println(sol.solution(8, 12));
    }

    // 실수를 사용하지 않는 풀이
    public long solution(int w, int h) {
        if(w > h) {
            int temp = w;
            w = h;
            h = temp;
        }

        long[] gradient = new long[]{h, w};

        long sum = 0;
        long[] before = new long[]{0, 1};
        long[] now = null;
        for(int i=1; i<=w; i++) {
            now = new long[]{gradient[0]*i, gradient[1]};
            int end = (int) (now[0] % now[1] == 0 ? now[0]/now[1] : now[0]/now[1]+1);
            int start = (int) (before[0]/before[1]);
            sum += end - start;
            before = now;
        }

        long loss = sum;
        long all = (long)w * h;
        return all - loss;
    }

    // 실수를 사용하는 풀이
//    public long solution2(int w, int h) {
//        if(w > h) {
//            int temp = w;
//            w = h;
//            h = temp;
//        }
//
//        double gradient = (double)h / w;
//        int count, miniW;
//        int gcf = gcf(w, h);
//        if(gcf == 1) { // 이미 기약분수
//            count = 1;
//            miniW = w;
//        }
//        else {
//            count = w / (w / gcf);
//            miniW = w / gcf;
//        }
//        long sum = 0;
//        int start = 0;
//        int end = 0;
//        for(int i=1; i<=miniW; i++) {
//            double now = gradient * i;
//            System.out.println(now);
//            System.out.println("now: " + now);
//            end = (int)Math.ceil(now);
//            System.out.println("end: " + end);
//            sum += end - start;
//            start = (int)Math.floor(now);
//            System.out.println("start: " + start);
//        }
//        long loss = sum * count;
//        long all = (long)w * h;
//        return all - loss;
//    }
//
//    int gcf(int a, int b) {
//        if(b > a) {
//            int temp = a;
//            a = b;
//            b = temp;
//        }
//        if(a % b == 0)
//            return b;
//        return gcf(b, a%b);
//    }
}
