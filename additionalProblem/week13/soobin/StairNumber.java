package week13.soobin;

import java.io.*;

public class StairNumber {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MOD = 1000000000;

    private static long[][][] dp;
    private static int N;

    private static long dp(int n, int k, int mask) {
        if (n == N)
            return mask == 1023 ? 1 : 0;

        if (dp[n][k][mask] > 0) return dp[n][k][mask];

        dp[n][k][mask] = 0;
        if (k > 0)
            dp[n][k][mask] += dp(n + 1, k - 1, mask | (1 << (k - 1))) % MOD;
        if (k < 9)
            dp[n][k][mask] += dp(n + 1, k + 1, mask | (1 << (k + 1))) % MOD;
        return dp[n][k][mask] % MOD;
    }

    private static long solution() {
        long answer = dp(1, 1, 1 << 1) % MOD;
        for (int i = 2; i < 10; i++)
            answer += dp(1, i, 1 << i) % MOD;

        return answer % MOD;
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1][10][1024];

        if (N >= 1 && N <= 9) {
            bw.write(String.valueOf(0));
            bw.flush();
            return;
        }

        long answer = solution();
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
