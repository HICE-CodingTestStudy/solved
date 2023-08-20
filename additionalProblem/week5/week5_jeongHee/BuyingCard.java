package queue;

import java.util.Scanner;

public class BuyingCard {
    //https://www.acmicpc.net/problem/11052
    //카드 구매하기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] card = new int[N + 1];
        for (int i = 0; i < N; i++) {
            card[i + 1] = sc.nextInt();
        }
        //N+1 에는 최대값을 저장함
        int[][] dp = new int[N + 1][N + 2];

        //i원 어치를 살때 최대값을 계산하기 위해 1~N까지의 팩을 사는 경우의 수를 계산함
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i - j < 0) continue;
                dp[i][j] = dp[i - j][N + 1] + card[j];
                dp[i][N + 1] = Math.max(dp[i][N + 1], dp[i][j]);
            }
        }
        System.out.println(dp[N][N+1]);
    }
}
