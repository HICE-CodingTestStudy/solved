package week20.soobin;

import java.util.*;

public class OpenChat {
    private class User {
        String uid, nickname;

        User(String id, String nickname) {
            this.uid = id;
            this.nickname = nickname;
        }

        public String enter() {
            return nickname + "님이 들어왔습니다.";
        }

        public String leave() {
            return nickname + "님이 나갔습니다.";
        }
    }

    private class Log {
        char command;
        String uid;

        Log(char command, String uid) {
            this.command = command;
            this.uid = uid;
        }

        public String printLog() {
            User user = totalUser.get(uid);
            return command == 'E' ? user.enter() : user.leave();
        }
    }

    private Map<String, User> totalUser;

    private List<Log> processRecords(String[] records) {
        List<Log> logs = new ArrayList<>();

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            String type = st.nextToken();
            String uid = st.nextToken();

            if (type.charAt(0) != 'C') logs.add(new Log(type.charAt(0), uid));
            if (type.charAt(0) == 'L') continue;

            String nickname = st.nextToken();
            User user = new User(uid, nickname);
            totalUser.put(uid, user);
        }

        return logs;
    }

    private List<String> printLogs(List<Log> logs) {
        List<String> answer = new ArrayList<>();
        for (Log log : logs) answer.add(log.printLog());

        return answer;
    }

    public List<String> solution(String[] records) {
        totalUser = new HashMap<>();
        List<Log> logs = processRecords(records);
        return printLogs(logs);
    }

    public static void main(String[] args) {
        OpenChat oc = new OpenChat();
        String[] records = new String[] {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        System.out.println(oc.solution(records));
    }
}
