package programmers.level1;

import java.util.ArrayList;

public class 수포자 {
}

class Solution수포자 {

    int[][] random = {
            {1,2,3,4,5},
            {2,1,2,3,2,4,2,5},
            {3,3,1,1,2,2,4,4,5,5}
    };

    public int[] solution(int[] answers) {
        ArrayList<Integer> list = null;
        int max = -1;
        for(int i=0; i<3; i++) {
            int score = 0;
            for(int j=0; j<answers.length; j++) {
                if(answers[j] == random[i][j % random[i].length])
                    score++;
            }
            if(score < max)
                continue;
            else if(score > max) {
                max = score;
                list = new ArrayList<>();
            }
            list.add(i+1);
        }
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
