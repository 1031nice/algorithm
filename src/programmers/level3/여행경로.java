package programmers.level3;

import java.util.*;

/*
DFS, BFS를 여러번 풀다보면 기계적으로 코드를 짜게 되는데,
기계적으로 풀면 굉장히 여러 곳에서 문제가 발생한다.

1. 항공권이 여러개 있을 수 있으므로 matrix에 1이 아니라 항공권의 개수 만큼
2. 여러 번 방문이 가능해야 하지만 무한 루프에는 빠지지 않아야 함

주의 사항
- 알파벳이 더 빠르다고 해서 무조건 경로인 것은 아니다.
알파벳이 더 빠르다고 해도 모든 항공권을 쓸 수 있는 루트가 없다면 다른 공항을 알아봐야 한다.
 */

public class 여행경로 {
    public static void main(String[] args) {
        여행경로 s = new 여행경로();
        System.out.println(Arrays.toString(s.solution2(new String[][]{{"ICN", "BBB"},{"ICN", "CCC"},{"BBB", "CCC"},{"CCC", "BBB"},{"CCC", "ICN"}})));
        System.out.println("[ICN, BBB, CCC, ICN, CCC, BBB]");
        System.out.println(Arrays.toString(s.solution2(new String[][]{{"ICN", "B"},{"B", "ICN"},{"ICN", "A"},{"A", "D"},{"D", "A"}})));
        System.out.println("[ICN, B, ICN, A, D, A]");
        System.out.println(Arrays.toString(s.solution2(new String[][]{{"ICN", "AAA"},{"ICN", "AAA"},{"ICN", "AAA"},{"AAA", "ICN"},{"AAA", "ICN"}})));
        System.out.println("[ICN, AAA, ICN, AAA, ICN, AAA]");
        System.out.println(Arrays.toString(s.solution2(new String[][]{{"ICN", "COO"},{"ICN", "BOO"},{"COO", "ICN"},{"BOO", "DOO"}})));
        System.out.println("[ICN, COO, ICN, BOO, DOO]");
        System.out.println(Arrays.toString(s.solution2(new String[][]{{"ICN", "SFO"},{"SFO", "ICN"},{"ICN", "SFO"},{"SFO", "QRE"}})));
        System.out.println("[ICN, SFO, ICN, SFO, QRE]");
        System.out.println(Arrays.toString(s.solution2(new String[][]{{"ICN", "BOO"},{"ICN", "COO"},{"COO", "DOO"},
                {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"}})));
        System.out.println("[ICN, BOO, DOO, BOO, ICN, COO, DOO, COO, BOO]");
    }

    public String[] solution2(String[][] tickets) {
        // 공항을 숫자로 치환한다(알파벳이 빠를수록 낮은 숫자)
        HashSet<String> set = new HashSet<>();
        int count = 0;
        for(int i=0; i<tickets.length; i++) {
            set.add(tickets[i][0]);
            set.add(tickets[i][1]);
        }
        String[] airports = set.toArray(new String[0]);
        Arrays.sort(airports);

        HashMap<String, Integer> nameToId = new HashMap<>();
        HashMap<Integer, String> idToName = new HashMap<>();
        for(String s : airports) {
            nameToId.put(s, count);
            idToName.put(count++, s);
        }

        // 그래프로 표현한다
        int[][] matrix = new int[count][count];
        for(int i=0; i<tickets.length; i++) {
            int a = nameToId.get(tickets[i][0]);
            int b = nameToId.get(tickets[i][1]);
            matrix[a][b] += 1;
        }

        // 순회한다
        ArrayList<Integer> list = new ArrayList<>();
        dfs(matrix, list, nameToId.get("ICN"), tickets.length);
        String[] answer = new String[list.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = idToName.get(list.get(i));
        }
        return answer;
    }

    public boolean dfs(int[][] matrix, ArrayList<Integer> answer, int start, int leftTickets) {
        answer.add(start);
        boolean isAllVisited = true;
        for(int i=0; i<matrix[start].length; i++) {
            if(matrix[start][i] != 0) {
                isAllVisited = false;
                break;
            }
        }
        if(isAllVisited) {
            if(leftTickets != 0) {
                answer.remove(answer.size() - 1);
                return false;
            }
            else {
                return true;
            }
        }


        for(int i=0; i<matrix[start].length; i++) {
            if(matrix[start][i] != 0) {
                matrix[start][i] -= 1;
                if(dfs(matrix, answer, i, leftTickets-1))
                    return true;
                matrix[start][i] += 1;
            }
        }

        answer.remove(answer.size()-1);
        return false;
    }
}
