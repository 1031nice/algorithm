package programmers.level2;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class 방금그곡 {

    class MusicInfo implements Comparable<MusicInfo> {
        String title;
        String startTime;
        int length;

        public MusicInfo(String title, String startTime, int length) {
            this.title = title;
            this.startTime = startTime;
            this.length = length;
        }

        @Override
        public int compareTo(MusicInfo o) {
            if(this.length == o.length)
                return this.startTime.compareTo(o.startTime);
            return Integer.compare(o.length, this.length);
        }
    }

    public String solution(String m, String[] musicinfos) {
        ArrayList<MusicInfo> infoList = new ArrayList<>();

        for (String info : musicinfos) {
            String[] split = info.split(",");
            String start = split[0];
            String end = split[1];
            String title = split[2];
            String melody = split[3];
            int playLen = (int) Duration.between(LocalTime.parse(start), LocalTime.parse(end)).toMinutes();

            String actualMelody = buildActualMelody(melody, playLen);

            for(int i=0; i <= actualMelody.length() - m.length(); i++) {
                if (compare(m, actualMelody, i)) {
                    infoList.add(new MusicInfo(title, start, playLen));
                    break;
                }
            }
        }

        if (infoList.size() == 0) return "(None)";

        Collections.sort(infoList);
        return infoList.get(0).title;
    }

    private String buildActualMelody(String melody, int playLen) {
        int melodyLen = melody.length();
        for (int j = 0; j < melody.length(); j++) {
            if (melody.charAt(j) == '#') melodyLen--;
        }

        int div = playLen / melodyLen;
        int mod = playLen % melodyLen;
        StringBuilder sb = new StringBuilder();
        sb.append(melody.repeat(Math.max(0, div)));
        int cnt = 0;
        for (int j = 0; cnt < mod && j < melody.length(); j++) {
            sb.append(melody.charAt(j));
            if (j + 1 < melody.length() && melody.charAt(j + 1) == '#') {
                sb.append(melody.charAt(j + 1));
                j++;
            }
            cnt++;
        }
        return sb.toString();
    }

    public boolean compare(String a, String b, int start) {
        if(b.length() < start + a.length()) return false;
        String subStr = b.substring(start, start + a.length());
        if(a.equals(subStr)) {
            return b.length() <= start + a.length() || b.charAt(start + a.length()) != '#';
        }
        return false;
    }

    /*
    //    얼마나 일치했는지를 알면 다음에 계산할 때 일치한 만큼 건너뛸 수 있어서 더 빠를지도 모르겠다고 생각했으나
    //    얼마나 일치하는지 일일이 구하는 것보다, equals를 이용하여 뭉탱이로 검사하는 게
    //    직전 일치 검사 때 봤던 것을 다시 본다고 하더라도 훨씬 빠르다
    public int compare(String a, String b, int start) {
        int cnt = 0;
        int i=0;
        int j=start;
        while(i < a.length() && j < b.length()) {
            boolean isASharp = i+1 < a.length() && a.charAt(i+1) == '#';
            boolean isBSharp = j+1 < b.length() && b.charAt(j+1) == '#';
            if(a.charAt(i) == b.charAt(j) && isASharp == isBSharp) {
                cnt++;
            } else {
                break;
            }

            i += isASharp ? 2 : 1;
            j += isBSharp ? 2 : 1;
        }

        return cnt;
    }*/
}
