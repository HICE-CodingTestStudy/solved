package com.example.demo.heap;

import java.util.Arrays;
import java.util.Scanner;

public class GueNimTi {
    //https://www.acmicpc.net/problem/14613
    //너의 티어는?
    static double W, L, D;
    static double[][] dp;
    static double[] tier = new double[5];

    static double solution(int score, int count) {
        if (score < 1000 || score > 3000) return 0.0;
        if (dp[score][count] != -1) return dp[score][count];
        if (count == 0) return dp[score][count] = 0.0;
        return dp[score][count] = solution(score, count - 1) * D +
                solution(score + 50, count - 1) * L +
                solution(score - 50, count - 1) * W;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        W = sc.nextDouble();
        L = sc.nextDouble();
        D = sc.nextDouble();
        dp = new double[3001][21];
        for (int i = 0; i < 3001; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[2000][0] = 1;
        for (int i = 1000; i <= 3000; i += 50) {
            solution(i, 20);
        }
        for (int i = 1000; i <= 3000; i += 50) {
            double p = dp[i][20];
            if (i <= 1499) {
                tier[0] += p;
                continue;
            }
            if (i <= 1999) {
                tier[1] += p;
                continue;
            }
            if (i <= 2499) {
                tier[2] += p;
                continue;
            }
            if (i <= 2999) {
                tier[3] += p;
                continue;
            }
            tier[4] += p;
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(String.format("%.8f",tier[i]));
        }
    }

}
