package dp;

import java.util.Scanner;

public class GirlsOnTop {
    //https://www.acmicpc.net/problem/2775
    //부녀 회장이 될테야
    public static void main(String[] args) {
        int T,k,n;
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            k = sc.nextInt();
            n = sc.nextInt();
            int[][]dp = new int[k+1][n+1];
            for (int j = 1; j <= n; j++) {
                dp[0][j]=j;
            }
            for (int j = 1; j <= k ; j++) {
                for (int l = 1; l <= n; l++) {
                    if(l==1)
                        dp[j][l]=dp[j-1][l];
                    else dp[j][l]=dp[j][l-1]+dp[j-1][l];
                }
            }
            System.out.println(dp[k][n]);
        }
    }
}
