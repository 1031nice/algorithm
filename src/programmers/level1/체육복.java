package programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;

public class 체육복 {
}

class Solution6 {
    public int solution(int n, int[] lost, int[] reserve) {
        int indexOfLost = 0;
        int indexOfReserve = 0;
        int cover = 0;

        Arrays.sort(lost);
        ArrayList<Integer> lostList = new ArrayList<>();
        ArrayList<Integer> tempLostList = new ArrayList<>();
        for(int i=0; i<lost.length;i ++) {
            lostList.add(lost[i]);
            tempLostList.add(lost[i]);
        }

        Arrays.sort(reserve);
        ArrayList<Integer> reserveList = new ArrayList<>();
        ArrayList<Integer> tempReserveList = new ArrayList<>();
        for(int i=0; i<reserve.length;i ++) {
            reserveList.add(reserve[i]);
            tempReserveList.add(reserve[i]);
        }

        reserveList.removeAll(tempLostList);
        lostList.removeAll(tempReserveList);

        while(indexOfLost < lostList.size() && indexOfReserve < reserveList.size()) {
            // 1 차이 날 때
            if(Math.abs(reserveList.get(indexOfReserve) - lostList.get(indexOfLost)) <= 1) {
                if(indexOfLost+1 < lostList.size() &&
                        lostList.get(indexOfLost+1) == reserveList.get(indexOfReserve)) {
                    // 다음에 자기가 입어야함
                    indexOfLost++;
                }
                else {
                    // 커버가능
                    cover++;
                    indexOfReserve++;
                    indexOfLost++;
                }
                continue;
            }
            if(reserveList.get(indexOfReserve) > lostList.get(indexOfLost))
                indexOfLost++;
            else
                indexOfReserve++;
        }

        return n - lostList.size() + cover;
    }

    /*
    이건 하나를 통과 못하더라
    public int solution(int n, int[] lost, int[] reserve) {
        int indexOfLost = 0;
        int indexOfReserve = 0;
        int cover = 0;

        Arrays.sort(lost);
        Arrays.sort(reserve);

        while(indexOfLost < lost.length && indexOfReserve < reserve.length) {
            // 1 차이 날 때
            if(Math.abs(reserve[indexOfReserve] - lost[indexOfLost]) <= 1) {
                if(indexOfLost+1 < lost.length &&
                       lost[indexOfLost+1] == reserve[indexOfReserve]) {
                    // 다음에 자기가 입어야함
                    indexOfLost++;
                }
                else {
                    // 커버가능
                    cover++;
                    indexOfReserve++;
                    indexOfLost++;
                }
                continue;
            }
            if(reserve[indexOfReserve] > lost[indexOfLost])
                indexOfLost++;
            else
                indexOfReserve++;
        }

        return n - lost.length + cover;
    }
     */
}