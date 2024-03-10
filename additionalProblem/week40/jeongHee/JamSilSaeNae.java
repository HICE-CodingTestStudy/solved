import java.util.Scanner;

public class JamSilSaeNae {
    //https://www.acmicpc.net/problem/14651
    //걷다보기 신천역 삼
    static final int MOD = 1000000009;
    static int N;
    static int[][] dp;

    static int solution() {
        dp[1][1] = 1;
        dp[1][2] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i][1] = dp[i][2] = ((dp[i - 1][0] + dp[i - 1][1]) % MOD + dp[i - 1][2]) % MOD;
        }
        return dp[N][0] % MOD;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N + 1][3];
        System.out.println(solution());
    }
}
