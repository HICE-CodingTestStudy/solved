package week5.soobin;

import java.io.*;

public class Wine {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] wine;
    private static int[][] memo;

    private static int dp(int n, int drunk) {
        if (n == 0) return memo[n][drunk] = drunk == 0 ? wine[0] : 0;
        if (n == 1)
            return memo[n][drunk] = drunk == 0 ? wine[0] + wine[1] : wine[0];

        if (memo[n][drunk] > -1) return memo[n][drunk];

        return memo[n][drunk] = drunk == 0
                ? Math.max(dp(n-2, 1) + wine[n-1], Math.max(dp(n-2, 0), dp(n-2, 1))) + wine[n]
                : Math.max(dp(n-1, 0), dp(n-1, 1));
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        memo = new int[N][2];
        for (int i = 0; i < N; i++)
            memo[i][0] = memo[i][1] = -1;

        wine = new int[N];
        for (int i = 0; i < N; i++)
            wine[i] = Integer.parseInt(br.readLine());

        int maxWine = Math.max(dp(N-1, 0), dp(N-1, 1));
        bw.write(String.valueOf(maxWine));
        bw.flush();
    }
}
