package com.example.demo.heap;

import java.util.Scanner;

public class RgbDistance {
    //https://www.acmicpc.net/problem/1149
    //RGB 거리
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] dp = new int[N][3];
        int[][] cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                cost[i][j] = sc.nextInt();
            }
        }
        System.arraycopy(cost[0], 0, dp[0], 0, 3);
        for (int i = 1; i < N; i++) {
            dp[i][0]=Math.min(dp[i-1][1],dp[i-1][2])+cost[i][0];
            dp[i][1]=Math.min(dp[i-1][0],dp[i-1][2])+cost[i][1];
            dp[i][2]=Math.min(dp[i-1][0],dp[i-1][1])+cost[i][2];
        }
        System.out.println(Math.min(dp[N-1][0],Math.min(dp[N-1][1],dp[N-1][2])));
    }
}
