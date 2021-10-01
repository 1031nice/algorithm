package programmers.level3;

import java.util.Arrays;

public class 징검다리건너기 {
    public static void main(String[] args) {
        System.out.println(new 징검다리건너기().solution(new int[]{2,4,5,3,2,1,4,2,5,1}, 3));
    }

    static class Stone implements Comparable<Stone>{
        int index;
        int number;
        public Stone(int index, int number){
            this.index = index;
            this.number = number;
        }

        @Override
        public int compareTo(Stone o) {
            return Integer.compare(this.number, o.number);
        }
    }

    public int solution(int[] stones, int k) {
        // 각각의 인덱스도 기록해둔 뒤 돌을 내림차순 정렬한다
        Stone[] stoneArr = new Stone[stones.length];
        for(int i=0; i<stones.length; i++) {
            stoneArr[i] = new Stone(i, stones[i]);
        }
        Arrays.sort(stoneArr);

        // 숫자가 가장 작은 돌부터 union find를 수행한다
        int[] parent = new int[stones.length];
        Arrays.fill(parent, -1);
        for(int i=0; i<stoneArr.length; i++) {
            Stone now = stoneArr[i];
            if(now.index-1 >= 0 && stones[now.index-1] <= now.number) {
                union(parent, now.index, now.index - 1);
            }
            if(now.index+1 < stoneArr.length && stones[now.index+1] <= now.number) {
                union(parent, now.index, now.index + 1);
            }
            // 한 번의 union find가 끝날 때마다 변화가 일어난 group의 size와 k를 비교한다(최대 group을 찾을 필요 없음)
            if(-parent[find(parent, now.index)] >= k)
                // 건너는 사람을 카운트 하는 것은 가장 최근 돌의 숫자를 그대로 쓰면 될 것 같다
                return now.number;
        }
        // 모두 돌을 건넜으므로 가장 큰 숫자
        return stoneArr[stoneArr.length-1].number;
    }

    public void union(int[] parent, int i1, int i2) {
        int p1 = find(parent, i1);
        int p2 = find(parent, i2);
        if(p1 == p2)
            return;
        if(parent[p1] < parent[p2]) { // 크기는 음수이므로 p2가 더 크다는 뜻
            parent[p1] += parent[p2];
            parent[p2] = p1;
        }
        else {
            parent[p2] += parent[p1];
            parent[p1] = p2;
        }
    }

    public int find(int[] parent, int i) {
        if(parent[i] < 0)
            return i;
        return (parent[i] = find(parent, parent[i]));
    }
}
