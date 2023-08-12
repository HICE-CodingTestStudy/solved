package dp;

import java.util.Scanner;

public class OneTwoThreePlus {
    //https://www.acmicpc.net/problem/9095
    //1 2 3 더하기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[] dp = new int[n+1];
            dp[1]=1;
            if(n==1){
                System.out.println(dp[n]);
                continue;
            }
            dp[2]=2;
            if(n==2){
                System.out.println(dp[n]);
                continue;
            }
            dp[3]=4;
            if(n==3){
                System.out.println(dp[n]);
                continue;
            }
            for (int j = 4; j <= n; j++) {
                dp[j]=dp[j-1]+dp[j-2]+dp[j-3];
            }
            System.out.println(dp[n]);
        }

    }
}
