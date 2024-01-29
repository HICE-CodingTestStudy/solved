import java.io.*;

public class NumberCard {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX_CARD = 34;

    private static int[] memo, numbers;
    private static int N;

    public static void main(String[] args) {
        parseInput();
        int answer = dp(0);
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            String input = br.readLine();
            N = input.length();
            memo = new int[N];
            numbers = new int[N];

            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
                memo[i] = -1;
            }
        } catch (IOException ignored) {}
    }

    private static int dp(int idx) {
        if (isValid(idx)) return 1;
        if (isInvalid(idx)) return 0;

        if (memo[idx] > -1) return memo[idx];

        memo[idx] = dp(idx + 1);
        if (isInCard(idx)) memo[idx] += dp(idx + 2);
        return memo[idx];
    }

    private static boolean isValid(int idx) {
        return idx == N || (idx == N - 1 && numbers[idx] != 0);
    }

    private static boolean isInvalid(int idx) {
        return numbers[idx] == 0;
    }

    private static boolean isInCard(int idx) {
        return numbers[idx] * 10 + numbers[idx + 1] <= MAX_CARD;
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
