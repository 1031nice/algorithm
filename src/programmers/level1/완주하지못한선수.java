package programmers.level1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class 완주하지못한선수 {
}

class Solution4 {
    public String solution(String[] participant, String[] completion) {
        HashSet<String> cSet = new HashSet<>();
        List<String> cList = new ArrayList<>();
        HashSet<String> pSet = new HashSet<>();
        List<String> pList = new ArrayList<>();
        for(int i=0; i<completion.length; i++) {
            if(!cSet.contains(completion[i]))
                cSet.add(completion[i]);
            else
                cList.add(completion[i]);
        }
        for(int i=0; i<participant.length; i++) {
            if(!pSet.contains(participant[i]))
                pSet.add(participant[i]);
            else
                pList.add(participant[i]);
        }
        pSet.removeAll(cSet);
        if(!pSet.isEmpty())
            return pSet.toArray(new String[0])[0];
        for(int i=0; i<cList.size(); i++) {
            if(pList.contains(cList.get(i)))
                pList.remove(cList.get(i));
        }
        return pList.get(0);
    }
}