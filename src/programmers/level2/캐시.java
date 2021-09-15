package programmers.level2;

import java.util.Arrays;
import java.util.LinkedList;

public class 캐시 {

    public static void main(String[] args) {
        System.out.println(new 캐시().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
    }

    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) {
            return 5 * cities.length;
        }
        cities = Arrays.stream(cities).map(String::toLowerCase).toArray(String[]::new);
        LinkedList<String> list = new LinkedList<>();
        int time = 0;
        for(String city : cities) {
            int index = list.indexOf(city);
            if(index != -1) { // hit
                time++;
                list.remove(index);
                list.addFirst(city);
            }
            else { // miss
                if(list.size() == cacheSize)
                    list.removeLast();
                list.addFirst(city);
                time += 5;
            }
        }
        return time;
    }

}
