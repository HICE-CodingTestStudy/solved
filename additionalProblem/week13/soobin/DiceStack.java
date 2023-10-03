package week13.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class DiceStack {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] dices, dp;

    private static int pair(int i) {
        if (i == 0 || i == 5) return 5 - i;
        if (i == 1 || i == 3) return 4 - i;
        return 6 - i;
    }

    private static int getMaxSideValue(int n, int i) {
        int pair = pair(i), max = 0;
        for (int j = 0; j < 6; j++) {
            if (j == i || j == pair) continue;

            max = Math.max(max, dices[n][j]);
        }

        return max;
    }

    private static int getBottomFace(int n, int value) {
        int i = 0;
        for (; i < 6; i++)
            if (dices[n][i] == value) return i;
        return i;
    }

    private static int dp(int n, int face) {
        if (n == 0) return dp[n][face] = getMaxSideValue(n, face);

        if (dp[n][face] > 0) return dp[n][face];

        int pair = pair(face);
        int bottom = getBottomFace(n - 1, dices[n][pair]);
        return dp[n][face] = dp(n - 1, bottom) + getMaxSideValue(n, face);
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        dices = new int[N][6];
        dp = new int[N][6];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++)
                dices[i][j] = Integer.parseInt(st.nextToken());
        }

        int max = dp(N - 1, 0);
        for (int i = 1; i < 6; i++)
            max = Math.max(dp(N - 1, i), max);

        bw.write(String.valueOf(max));
        bw.flush();
    }
}
