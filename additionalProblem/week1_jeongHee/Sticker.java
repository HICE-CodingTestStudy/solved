package com.example.demo.heap;

import java.util.Scanner;

public class Sticker {
    //https://www.acmicpc.net/problem/9465
    //스티커
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int[][] cost = new int[2][N];
            int[][] dp = new int[2][N];
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < N; k++) {
                   cost[j][k]=sc.nextInt();
                }
            }
            dp[0][0]=cost[0][0];
            dp[1][0]=cost[1][0];
            if(N!=1) {
                dp[0][1] = dp[1][0] + cost[0][1];
                dp[1][1] = dp[0][0] + cost[1][1];
            }
            for (int j = 2; j < N; j++) {
                dp[0][j]=Math.max(dp[1][j-1],Math.max(dp[1][j-2],dp[0][j-2]))+cost[0][j];
                dp[1][j]=Math.max(dp[0][j-1],Math.max(dp[1][j-2],dp[0][j-2]))+cost[1][j];
            }
            System.out.println(Math.max(dp[0][N-1],dp[1][N-1]));
        }
    }
}
