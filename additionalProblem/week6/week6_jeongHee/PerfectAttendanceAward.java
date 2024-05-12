package queue;

import java.util.Scanner;

public class PerfectAttendanceAward {
    //https://www.acmicpc.net/problem/1563
    //개근상
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][][] dp = new int[1001][3][4];
        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][2][0] = 1;
        if (N == 1) {
            System.out.println(3);
            return;
        }
        for (int i = 2; i <= N; i++) {
            dp[i][0][0] = dp[i - 1][2][0];
            dp[i][0][1] = dp[i - 1][0][0];
            dp[i][0][2] = (dp[i - 1][1][0] + dp[i - 1][2][1]) % 1000000;
            dp[i][0][3] = dp[i - 1][0][2];

            dp[i][1][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][2][0]) % 1000000;

            dp[i][2][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][2][0]) % 1000000;
            dp[i][2][1] = (dp[i - 1][0][2] + dp[i - 1][0][3] + dp[i - 1][1][0] + dp[i - 1][2][1]) % 1000000;
        }
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                ans += dp[N][i][j];
                ans = ans % 1000000;
            }
        }
        System.out.println(ans);
    }
}
