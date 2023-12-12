package com.example.demo.heap;

import java.util.HashMap;
import java.util.Scanner;

public class LCS {
    //https://www.acmicpc.net/problem/9251
    //LCS
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.nextLine();
        String second = sc.nextLine();
        int[][] dp = new int[first.length()][second.length()];
        for (int i = 0; i < first.length(); i++) {
            if(i==0){
                if(first.charAt(i)==second.charAt(i)) dp[i][0]=1;
                else dp[i][0]=0;
                continue;
            }
            if(first.charAt(i)==second.charAt(0)) dp[i][0]=1;
            else dp[i][0]=dp[i-1][0];

        }
        for (int i = 0; i < second.length(); i++) {
            if(i==0){
                if(second.charAt(i)==first.charAt(i)) dp[0][i]=1;
                else dp[0][i]=0;
                continue;
            }
            if(second.charAt(i)==first.charAt(0)) dp[0][i]=1;
            else dp[0][i]=dp[0][i-1];

        }
        for (int i = 1; i < first.length(); i++) {
            for (int j = 1; j < second.length(); j++) {
                if(first.charAt(i)==second.charAt(j))
                    dp[i][j]=dp[i-1][j-1]+1;
                else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[first.length()-1][second.length()-1]);
    }
}
