package week23.soobin;

import java.io.*;

public class Captain {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] stacks, memo;
    private static int N;

    public static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void initialize() {
        memo = new int[N + 1];
        stacks = new int[121];

        stacks[1] = 1;
        for (int i = 2; i < 121; i++)
            stacks[i] = stacks[i - 1] + (i * (i + 1)) / 2;

        for (int i = 0; i <= N; i++) memo[i] = i;
    }

    private static int dp() {
        for (int i = 1; i <= N; i++)
            for (int j = 0; j < 121; j++) {
                if (i < stacks[j]) break;

                memo[i] = Math.min(memo[i], memo[i - stacks[j]] + 1);
            }

        return memo[N];
    }

    public static void main(String[] args) {
        parseInput();
        initialize();
        int answer = dp();
        printOutput(answer);
    }
}
