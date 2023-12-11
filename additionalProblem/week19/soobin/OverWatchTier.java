package week19.soobin;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OverWatchTier {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static double[][] dp;
    private static double[] probabilities;
    private static double W, L, D;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Double.parseDouble(st.nextToken());
            L = Double.parseDouble(st.nextToken());
            D = Double.parseDouble(st.nextToken());

            dp = new double[21][2500];
            for (int i = 0; i < 21; i++)
                Arrays.fill(dp[i], -1);
            probabilities = new double[5];
        } catch (IOException e) {}
    }

    private static double dp(int game, int score) {
        if (game == 0 && score == 1000) return 1;
        if (game == 0) return 0;

        if (score < 0 || score >= 2500) return 0;

        if (dp[game][score] > -1) return dp[game][score];

        dp[game][score] = dp(game - 1, score - 50) * W
                + dp(game - 1, score + 50) * L
                + dp(game - 1, score) * D;
        return dp[game][score];
    }

    private static void printOutput() {
        final double ROUND = 100000000;
        try {
            for (int i = 0; i < 5; i++) {
                double probability = Math.round(probabilities[i] * ROUND) / ROUND;
                bw.write(String.format("%.8f\n", probability));
            }
            bw.flush();
        } catch (IOException e) {}
    }

    private static void calculateTotalProbabilityOfTier(int tier) {
        for (int i = tier * 500; i < (tier + 1) * 500; i += 10) {
            probabilities[tier] += dp(20, i);
        }
    }

    public static void main(String[] args) {
        parseInput();
        for (int i = 0; i < 5; i++)
            calculateTotalProbabilityOfTier(i);
        printOutput();
    }
}
