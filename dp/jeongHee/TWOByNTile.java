package dp;

import java.util.Scanner;

public class TWOByNTile {
    //https://www.acmicpc.net/problem/11726
    //2*n 타일링
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        switch (n){
            case 1:
                System.out.println(1);
                return;
            case 2:
                System.out.println(2);
                return;
            default:
                int[] dp = new int[n+1];
                dp[1]=1;
                dp[2]=2;
                for (int i = 3; i < n+1 ; i++) {
                    dp[i]=(dp[i-2]+dp[i-1])%10007;
                }
                System.out.println(dp[n]%10007);
        }
    }
}
