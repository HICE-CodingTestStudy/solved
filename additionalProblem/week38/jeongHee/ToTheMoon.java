package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ToTheMoon {
    //https://www.acmicpc.net/problem/17485
    //진우의 달 여행
    static int N, M;
    static int[][] cost;
    static int[][][] dp;

    static int solution() {
        dp = new int[N][M][3];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 3; j++) {
                dp[0][i][j] = cost[0][i];
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 3; k++) {
                    int jIndex = j + k - 1;
                    if (jIndex < 0 || jIndex >= M) {
                        dp[i][j][k] = Integer.MAX_VALUE;
                        continue;
                    }
                    int min = Integer.MAX_VALUE;
                    for (int l = 0; l < 3; l++) {
                        if (l == k) continue;
                        min = Math.min(dp[i - 1][jIndex][l], min);
                    }
                    dp[i][j][k] = min + cost[i][j];
                    if (i == N - 1) ans = Math.min(ans, dp[i][j][k]);
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cost = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }
}
