package programmers.level2;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class 셔틀버스 {
    public static void main(String[] args) {
        System.out.println(new 셔틀버스().solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
        System.out.println(new 셔틀버스().solution(1, 1, 1, new String[]{"23:59"}));
        System.out.println(new 셔틀버스().solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(new 셔틀버스().solution(10, 60, 45, new String[]{"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        Arrays.sort(timetable);
        Queue<String> q = new LinkedList<>();
        Collections.addAll(q, timetable);

        LocalTime busTime = LocalTime.of(9, 0);
        for (int i = 0; i < n - 1; i++) { // 마지막 버스 제외
            int count = 0;
            while (count < m && !q.isEmpty()) {
                LocalTime peek = LocalTime.parse(q.peek());
                // isAfter는 해당 시각을 포함하지 않는다. 이게 원인이었는데 계속 엉뚱한 곳을 헤맸다.
                if (busTime.isAfter(peek) || busTime.equals(peek)) { // 더 빨리 도착한 사람이라면
                    count++;
                    q.poll();
                }
                else {
                    break;
                }
            }
            busTime= busTime.plus((long) t, ChronoUnit.MINUTES);
        }

        // 콘은 마지막 버스를 탄다
        if (q.size() < m) { // 만약 마지막 버스에 인원이 충분하면
            return busTime.toString();
        } else { // 정원보다 인원이 많다면 m번째 사람보다 1분만 빨리오면 된다
            String last = "";
            int count = 0;
            while (count < m && !q.isEmpty()) {
                LocalTime peek = LocalTime.parse(q.peek());
                if(peek.isAfter(busTime))
                    break;
                count++;
                answer = q.poll();
            }
            if(count == 0) return busTime.toString();
            else if(count < m) return answer;
            return LocalTime.parse(answer).minus((long) 1, ChronoUnit.MINUTES).toString();
        }
    }
}
