package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class 오픈채팅방 {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(new 오픈채팅방().solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"}))
        );
    }

    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        for(String str : record) {
            String[] s = str.split(" ");
            if(s[0].equals("Enter")) {
                map.put(s[1], s[2]);
            }
            else if(s[0].equals("Change")) {
                map.replace(s[1], s[2]);
            }
        }

        List<String> answerList = new ArrayList<>();
        for(String str : record) {
            String[] s = str.split(" ");
            if(s[0].equals("Enter"))
                answerList.add(map.get(s[1]) + "님이 들어왔습니다.");
            else if(s[0].equals("Leave")) {
                answerList.add(map.get(s[1]) + "님이 나갔습니다.");
            }
        }

        return answerList.toArray(new String[0]);
    }

}
