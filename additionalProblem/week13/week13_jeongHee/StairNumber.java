package additional;

import java.util.Arrays;
import java.util.Scanner;

public class StairNumber {
    //https://www.acmicpc.net/problem/1562
    //계단수
    static long[][][] dp;
    static final long divide = 1000000000;
    public static long makeStairNumber(int digit, int endNum, int visited) {
        if (endNum < 0 || endNum > 9) return 0;
        if (digit <= 0 || visited <= 0) return 0;
        if (digit == 1 && endNum == 0) return 0;
        if (((1 << endNum) & visited) != (1 << endNum)) return 0;
        if (digit == 1) {
            if (1 << (endNum) != visited) return 0;
            else return 1;
        }

        if (dp[digit][endNum][visited] != -1)
            return dp[digit][endNum][visited];


        return dp[digit][endNum][visited] = (
                makeStairNumber(digit - 1, endNum - 1, visited) +
                        makeStairNumber(digit - 1, endNum - 1, visited - (1 << (endNum))) +
                        makeStairNumber(digit - 1, endNum + 1, visited) +
                        makeStairNumber(digit - 1, endNum + 1, visited - (1 << (endNum)))) % divide;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        dp = new long[N + 1][10][1 << 10];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += makeStairNumber(N, i, (1 << 10) - 1);
            ans %= divide;
        }
        System.out.println(ans);
    }
}
