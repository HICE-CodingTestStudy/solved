package queue;

import java.math.BigInteger;
import java.util.Scanner;

public class SummitTalks {
    //https://www.acmicpc.net/problem/1670
    //정상회담 2
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] dp = new long[N + 1];
        dp[0] = 1;
        if (N == 0) {
            System.out.println(dp[N]);
            return;
        }
        dp[2] = 1;
        if (N == 2) {
            System.out.println(dp[N]);
            return;
        }
        dp[4] = 2;
        if (N == 4) {
            System.out.println(dp[N]);
            return;
        }
        for (int i = 6; i <= N; i += 2) {
            long dpI = 2 * dp[i - 2];
            for (int j = 2; j < i - 2; j += 2) {
                dpI += (dp[j] * dp[i - 2 - j]) % 987654321;
            }
            dp[i] = dpI % 987654321;
        }
        System.out.println(dp[N] % 987654321);
    }
}
