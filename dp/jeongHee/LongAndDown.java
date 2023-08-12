package dp;

import java.util.Scanner;

public class LongAndDown {
    //https://www.acmicpc.net/problem/11722
    //가장 긴 감소하는 부분 수열
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i]=sc.nextInt();
        }
        int[] dp = new int[N];
        dp[0]=1;
        int max = 1;
        for (int i = 1; i < N ; i++) {
            dp[i]=1;
            for (int j = i-1; j >=0 ; j--) {
                if(arr[j]>arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            max=Math.max(dp[i],max);
        }
        System.out.println(max);
    }
}
