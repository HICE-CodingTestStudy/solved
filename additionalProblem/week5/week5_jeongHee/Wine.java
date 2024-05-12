package queue;

import java.util.Scanner;

public class Wine {
    //https://www.acmicpc.net/problem/2156
    //포도주 시식
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        //0 -> 전 단계의 포도주랑 이번거를 먹었을때의 최대값
        //1 -> 전전 단계의 포도주랑 이번거를 먹었을때의 최대값
        //2 -> 이번걸 안먹은 경우의 최대값

        //2번이 필요한 이유? -> 현재 컵의 포도주가 0인 경우 때문
        int[] cups = new int[n];
        for (int i = 0; i < n; i++) {
            cups[i] = sc.nextInt();
        }
        int[][] dp = new int[n][3];
        dp[0][0] = cups[0];
        dp[0][1] = dp[0][0];
        dp[0][2] = 0;
        if (n == 1) {
            System.out.println(dp[0][0]);
            return;
        }
        dp[1][1] = cups[1];
        dp[1][0] = dp[0][1] + dp[1][1];
        dp[1][2] = dp[0][0];
        for (int i = 2; i < n; i++) {
            int wine = cups[i];
            dp[i][0] = dp[i - 1][1] + wine;
            dp[i][1] = Math.max(Math.max(dp[i - 2][0], dp[i - 2][1]),dp[i-1][2]) + wine;
            dp[i][2] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), Math.max(dp[i - 2][0], dp[i - 2][1]));
        }
        System.out.println(Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), dp[n - 2][0]));
    }
}
