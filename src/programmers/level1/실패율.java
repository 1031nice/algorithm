package programmers.level1;

import java.util.*;

class Stage {
    int id;
    double lose;

    Stage(int id, double lose) {
        this.id = id;
        this.lose = lose;
    }
}

class MyComparator implements Comparator<Stage> {
    @Override
    public int compare(Stage o1, Stage o2) {
        if(o1.lose == o2.lose)
            return o1.id - o2.id;
        return (int)(o1.lose - o2.lose);
    }
}

class Solution8 {
    public int[] solution(int N, int[] stages) {
        int[] counts = new int[N+1];
        ArrayList<Stage> list = new ArrayList<>();
        for(int i=0; i<stages.length; i++)
            counts[stages[i]-1]++;
        int left = stages.length;
        // N+1번째는 모든 스테이지 클리어이므로 필요 X
        for(int i=0; i<N; i++) {
            list.add(new Stage(i+1, (double)counts[i] / left));
            left -= counts[i];
        }
        Collections.sort(list, new MyComparator());
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++)
            answer[i] = list.get(i).id;
        return answer;
    }
}

public class 실패율 {
    public static void main(String[] args) {
        Solution8 solution8 = new Solution8();
        System.out.println(Arrays.toString(solution8.solution(5, new int[]{2,1,2,6,2,4,3,3})));
    }
}