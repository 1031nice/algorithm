package hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
ranked와 player가 정렬되어 있다는 게 큰 힌트인 것 같다.
정렬되어 있기 때문에 각 배열을 한 번씩만 확인하면 된다. -> O(N)
정렬되어 있기 때문에 새로운 player가 ranked에 추가된다고 해도
그 뒤에 오는 player의 rank에는 아무 영향을 주지 않는다.
 */

public class ClimbingTheLeaderboard {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        HashMap<Integer, Integer> scoreToRank = new HashMap<>();
        int rank = 1;
        for(Integer r : ranked) {
            if(!scoreToRank.containsKey(r)) {
                scoreToRank.put(r, rank++);
            }
        }

        List<Integer> answer = new ArrayList<>();
        int index = ranked.size()-1;
        for(int j=0; j<player.size(); j++) {
            int last = -1;
            for(int i = index; i>=0; i--) {
                if(ranked.get(i) > player.get(j)) {
                    answer.add(scoreToRank.get(ranked.get(i)) + 1);
                    last = i;
                    break;
                }
            }
            index = last;
            if(index < 0) {
                for(int k=j; k<player.size(); k++) {
                    answer.add(1);
                }
                break;
            }
        }
        return answer;
    }

}
