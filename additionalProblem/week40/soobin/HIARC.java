import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class HIARC {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int otStart, otEnd, streamingEnd;

    public static void main(String[] args) {
        parseOtTime();
        int answer = countParticipatedMember();
        printAnswer(answer);
    }

    private static void parseOtTime() {
        try {
            String[] input = br.readLine().split(" ");
            otStart = convertTime(input[0]);
            otEnd = convertTime(input[1]);
            streamingEnd = convertTime(input[2]);
        } catch (IOException ignored) {}
    }

    private static int countParticipatedMember() {
        Set<String> member = new HashSet<>();
        int numChecked = 0;

        try {
            while (true) {
                String input = br.readLine();
                if (input == null || input.isEmpty()) break;

                String[] memberInput = input.split(" ");
                int time = convertTime(memberInput[0]);
                String nickname = memberInput[1];

                if (time > streamingEnd) break;

                if (time <= otStart) {
                    member.add(nickname);
                    numChecked = member.size();
                } else if (time >= otEnd) {
                    member.remove(nickname);
                }
            }
            return numChecked - member.size();
        }  catch (IOException ignored) {}
        return -1;
    }

    private static int convertTime(String timeFormat) {
        String[] splitFormat = timeFormat.split(":");
        int hour = Integer.parseInt(splitFormat[0]);
        int min = Integer.parseInt(splitFormat[1]);
        return hour * 60 + min;
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
