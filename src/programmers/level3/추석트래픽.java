package programmers.level3;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class 추석트래픽 {

    public static void main(String[] args) throws ParseException {
        System.out.println(new 추석트래픽().solution(new String[] {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"}));
    }

    static class Duration {
        LocalDateTime startTime;
        LocalDateTime endTime;

        public Duration(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public int solution(String[] lines) throws ParseException {
        int max = 0;
        Duration[] durations = new Duration[lines.length];

        for(int i=0; i<lines.length; i++) {
            String[] s = lines[i].split(" ");
            String processSeconds = s[2].substring(0, s[2].length() - 1);
            LocalDateTime endTime = LocalDateTime.of(LocalDate.parse(s[0]), LocalTime.parse(s[1]));
            LocalDateTime startTime = endTime.minus((int) (Double.parseDouble(processSeconds) * 1000L) - 1, ChronoUnit.MILLIS);
            durations[i] = new Duration(startTime, endTime);
        }

        // 시작 시간과 끝 시간을 구한다
        for(int i=0; i<durations.length; i++) {
            LocalDateTime endTime = durations[i].endTime;
            LocalDateTime startTime = durations[i].startTime;

            int count = 0;
            LocalDateTime endTimeMinusSec = endTime.minus(999L, ChronoUnit.MILLIS);
            LocalDateTime endTimePlusSec = endTime.plus(999L, ChronoUnit.MILLIS);

            // 끝점 기준 왼쪽 1초 구간
            for(int j=0; j<durations.length; j++) {
                if(!(endTimeMinusSec.isAfter(durations[j].endTime) || endTime.isBefore(durations[j].startTime)))
                    count++;
            }
            if(count > max) max = count;

            // 끝점 기준 오른쪽 1초 구간
            count = 0;
            for(int j=0; j<durations.length; j++) {
                if(!(endTime.isAfter(durations[j].endTime) || endTimePlusSec.isBefore(durations[j].startTime)))
                    count++;
            }
            if(count > max) max = count;
        }

        return max;
    }

}