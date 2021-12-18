package programmers.level2;

import java.util.Arrays;

public class 스킬트리 {

    public int solution(String skill, String[] skill_trees) {
        int[] precedes = new int[26];
        int answer = 0;
        Arrays.fill(precedes, -1);
        precedes[skill.charAt(0) - 'A'] = skill.charAt(0) - 'A';
        for(int i=1; i<skill.length(); i++) {
            precedes[skill.charAt(i) - 'A'] = skill.charAt(i-1) - 'A';
        }

        for(String skillTree : skill_trees) {
            boolean[] visited = new boolean[26];
            boolean isOk = true;
            for(int i=0; i<skillTree.length(); i++) {
                int now = skillTree.charAt(i) - 'A';
                int precede = precedes[now];
                visited[now] = true;
                if(precede != -1 && !visited[precede]) {
                    isOk = false;
                    break;
                }
            }
            if(isOk) answer++;
        }
        return answer;
    }

}
