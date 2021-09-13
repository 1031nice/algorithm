package programmers.level2;

import java.util.*;
import java.util.stream.Collectors;

public class 메뉴리뉴얼 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new 메뉴리뉴얼().solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2,3,4})));
        System.out.println(Arrays.toString(new 메뉴리뉴얼().solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2,3,4})));
    }

    static class Item {
        int count;
        ArrayList<String> strings;
        public Item(int count, ArrayList<String> strings) {
            this.count = count;
            this.strings = strings;
        }
    }

    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> setMenu_count = new HashMap<>();
        Set<Integer> courseSizes = Arrays.stream(course).boxed().collect(Collectors.toSet());

        for(int i=0; i<orders.length; i++) {
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = String.valueOf(chars);

            boolean[] visited = new boolean[orders[i].length()];
            generateEveryCombination(setMenu_count, visited, orders[i], new StringBuilder(), 0);
        }

        setMenu_count = setMenu_count.entrySet()
                .stream()
                .filter(a -> a.getValue() >= 2 && courseSizes.contains(a.getKey().length()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        String[] setMenus = setMenu_count.keySet().toArray(new String[0]);
        ArrayList<String> answer = new ArrayList<>();
        HashMap<Integer, Item> length_item = new HashMap<>();

        for (String nowSetMenu : setMenus) {
            Integer count = setMenu_count.get(nowSetMenu);
            if (length_item.containsKey(nowSetMenu.length())) {
                Item item = length_item.get(nowSetMenu.length());
                if (count > item.count) {
                    item.count = count;
                    item.strings.clear();
                    item.strings.add(nowSetMenu);
                } else if (count == item.count) {
                    item.strings.add(nowSetMenu);
                }
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(nowSetMenu);
                length_item.put(nowSetMenu.length(), new Item(count, list));
            }
        }
        Item[] items = length_item.values().toArray(new Item[0]);
        for (Item item : items) {
            answer.addAll(item.strings);
        }
        String[] ret = answer.toArray(new String[0]);
        Arrays.sort(ret);
        return ret;
    }

    private void generateEveryCombination(Map<String, Integer> map,
                                          boolean[] visited,
                                          String order,
                                          StringBuilder sb,
                                          int index) {
        if(index > order.length())
            return;

        else if(sb.length() >= 2) {
            String now = sb.toString();
            if(map.containsKey(now))
                map.put(now, map.get(now) + 1);
            else
                map.put(now, 1);
        }

        for(int i=index; i<order.length(); i++) {
            if(visited[i])
                continue;
            visited[i] = true;
            sb.append(order.charAt(i));
            generateEveryCombination(map, visited, order, sb, i);
            sb.deleteCharAt(sb.length()-1);
            visited[i] = false;
        }
    }
}
