import java.io.*;
import java.util.*;

public class DoorMan {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Deque<Character> waiting;
    private static int X, men, women;

    public static void main(String[] args) {
        parseInput();
        int answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            X = Integer.parseInt(br.readLine());
            waiting = new ArrayDeque<>();
            char[] input = br.readLine().toCharArray();

            for (char person : input)
                waiting.addLast(person);
        } catch (IOException ignored) {}
    }

    private static int solution() {
        while (!waiting.isEmpty()) {
            boolean isMan = waiting.peekFirst() == 'M';
            int diff = calcDiff(isMan);
            if (diff <= X) {
                letPersonEnter(isMan);
                continue;
            }

            char first = waiting.pollFirst();
            if (waiting.isEmpty()) break;

            isMan = waiting.peekFirst() == 'M';
            diff = calcDiff(isMan);
            if (diff <= X) {
                letPersonEnter(isMan);
                waiting.addFirst(first);
            } else break;
        }

        return men + women;
    }

    private static int calcDiff(boolean isMan) {
        return isMan ? Math.abs((men + 1) - women) : Math.abs(men - (women + 1));
    }

    private static void letPersonEnter(boolean isMan) {
        waiting.pollFirst();
        if (isMan) men++;
        else women++;
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
