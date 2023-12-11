package week9.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class DDR {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX_STAGE = 100001;
    private static final int[] stages = new int[MAX_STAGE];

    private static int[][][] memo;
    private static int N;

    private static int getPower(int prev, int next) {
        if (prev == 0) return 2;
        if (prev == next) return 1;
        return Math.abs(prev - next) == 2 ? 4 : 3;
    }

    private static int dp(int i, int left, int right) {
        if (i == N) return memo[i][left][right] = 0;

        if (memo[i][left][right] > 0) return memo[i][left][right];

        return memo[i][left][right] = Math.min(
                dp(i + 1, stages[i], right) + getPower(left, stages[i]),
                dp(i + 1, left, stages[i]) + getPower(right, stages[i])
        );
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = -1;

        for (int i = 0; st.hasMoreTokens(); i++, N++)
            stages[i] = Integer.parseInt(st.nextToken());
        memo = new int[N + 1][5][5];

        int answer = N == 0 ? 0 : dp(0, 0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
