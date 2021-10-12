package programmers.level2;

import java.util.ArrayList;
import java.util.HashMap;

/*
지금까지 풀어본 조합 문제의 경우 대부분 모든 조합을 알아야 했고,
그럴 때면 재귀를 통해 모든 조합을 구했다.
그래서 이 문제도 모든 조합을 구해서 풀려고 접근했고, 풀이가 매우 이상해졌다.
이 문제는 모든 조합의 원소 하나하나를 알 필요가 없는,
그냥 전체 개수만 계산하면 되는 문제인데도 말이다.
문제를 많이 풀어보는 것은 일반적으로 플러스이지만,
어디서 본 듯한 문제라고 문제 제대로 안 읽고 이렇게 대충 접근하면 마이너스가 될 수도 있다.
 */
public class 위장 {

    public static void main(String[] args) {
        System.out.println(new 위장().solution(new String[][]{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}}));
    }

    public int solution(String[][] clothes) {
        HashMap<String, Integer> clothToIndex = new HashMap<>();
        ArrayList<Integer> counts = new ArrayList<>();
        for(int i=0; i<clothes.length; i++) {
            String cloth = clothes[i][1];
            if(clothToIndex.containsKey(cloth)) {
                int index = clothToIndex.get(cloth);
                counts.set(index, counts.get(index)+1);
            }
            else {
                clothToIndex.put(cloth, counts.size());
                counts.add(1);
            }
        }

        int answer = 1;
        for(Integer c : counts) {
            answer *= (c+1);
        }
        return answer - 1;
    }
}
