import java.io.*;
import java.util.Stack;

public class PolarBear {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Stack<Character> bracketPairs = new Stack<>();

    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        String goal = br.readLine();

        int answer = getMinDay(goal);
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    private static int getMinDay(String goal) {
        int minDay = -1;
        for (int i = N - 1; i >= 0; i--) {
            char bracket = goal.charAt(i);

            if (isNewPair(bracket)) {
                bracketPairs.push(bracket);
                minDay = Math.max(minDay, bracketPairs.size());
            } else if (isPrevPair(bracket, bracketPairs.peek())) {
                bracketPairs.pop();
            } else return -1;
        }

        return bracketPairs.isEmpty() ? minDay : -1;
    }

    private static boolean isNewPair(char bracket) {
        return bracketPairs.isEmpty() || bracketPairs.peek() == bracket;
    }

    private static boolean isPrevPair(char fromStr, char fromStack) {
        return (fromStr == '(' && fromStack == ')') || (fromStr == ')' && fromStack == '(');
    }
}
