package additional2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OpenChattingRoom {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42888
    //오픈채팅방
    static Map<String, String> hm = new HashMap<>();

    enum Message {
        Enter("님이 들어왔습니다."),
        Leave("님이 나갔습니다.");

        private String kor;

        Message(String kor) {
            this.kor = kor;
        }
    }

    static ArrayList<String> buildPrintMessage(String[] record) {
        ArrayList<String> ans = new ArrayList<>();
        for (String s : record) {
            String[] chats = s.split(" ");
            if (chats[0].equals("Enter")) {
                StringBuilder sb = new StringBuilder();
                sb.append(hm.get(chats[1])).append(Message.Enter.kor);
                ans.add(sb.toString());
            }
            if (chats[0].equals("Leave")) {
                StringBuilder sb = new StringBuilder();
                sb.append(hm.get(chats[1])).append(Message.Leave.kor);
                ans.add(sb.toString());
            }
        }
        return ans;
    }

    public ArrayList<String> solution(String[] record) {
        for (String s : record) {
            String[] chats = s.split(" ");
            if (chats[0].equals("Enter") || chats[0].equals("Change")) {
                hm.put(chats[1], chats[2]);
            }
        }
        return buildPrintMessage(record);
    }
}
