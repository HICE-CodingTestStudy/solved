package week8.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class TravelingSalesman {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int COST_MAX = 1000001;

    private static int[][] dist;
    private static int[][] memo;
    private static int N;

    private static int dp(int i, int mask) {
        if (mask - (1 << i) == 1) return memo[mask][i] = dist[0][i];

        if (memo[mask][i] > 0) return memo[mask][i];

        int min = Integer.MAX_VALUE;
        for (int j = 1; j < N; j++) {
            if (j == i || (mask & 1 << j) == 0) continue;

            min = Math.min(min, dp(j, mask - (1 << i)) + dist[j][i]);
        }

        return memo[mask][i] = min;
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        memo = new int[1 << N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                dist[i][j] = cost == 0 ? COST_MAX : cost;
            }
        }

        int totalMask = (1 << N) - 1;
        int min = dp(1, totalMask) + dist[1][0];
        for (int i = 2; i < N; i++)
            min = Math.min(min, dp(i, totalMask) + dist[i][0]);

        bw.write(String.valueOf(min));
        bw.flush();
    }
}
