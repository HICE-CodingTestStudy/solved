package dp;

import java.util.Scanner;

public class Stair {
    //https://www.acmicpc.net/problem/2579
    //계단 오르기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] stair = new int[n+1];
        int[][] dp = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            stair[i]=sc.nextInt();
        }
        if(n==1)  {
            System.out.println(stair[1]);
            return;
        }
        if(n==2){
            System.out.println(stair[1]+stair[2]);
            return;
        }
        dp[1][0]=stair[1];
        dp[2][0]=stair[1]+stair[2];
        dp[2][1]=stair[2];
        for (int i = 3; i <= n ; i++) {
            int one = Math.max(dp[i-3][0],dp[i-3][1])+stair[i-1]+stair[i];
            int two = Math.max(dp[i-2][0],dp[i-2][1])+stair[i];
            dp[i][0]=one;
            dp[i][1]=two;
        }
        System.out.println(Math.max(dp[n][0],dp[n][1]));
    }
}
