package additional2;

import java.util.Scanner;

public class StoneBridge {
    //https://www.acmicpc.net/problem/2602
    //돌다리 건너기
    static int[][] dpFirst;
    static int[][] dpSecond;
    static String ans;
    static String first, second;

    static int solution(String a, String b, int[][] dp) {
        if (a.charAt(0) == ans.charAt(0)) dp[0][0] = 1;
        for (int i = 1; i < a.length(); i++) {
            if (a.charAt(i) == ans.charAt(0)) {
                dp[0][i] += dp[0][i - 1] + 1;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 1; i < ans.length(); i++) {
            if (i % 2 == 1) {
                for (int j = 1; j < a.length(); j++) {
                    if (b.charAt(j) == ans.charAt(i)) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            } else {
                for (int j = 1; j < a.length(); j++) {
                    if (a.charAt(j) == ans.charAt(i)) {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }

        return dp[ans.length() - 1][a.length() - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ans = sc.next();
        first = sc.next();
        second = sc.next();
        dpFirst = new int[ans.length()][first.length()];
        dpSecond = new int[ans.length()][second.length()];
        System.out.println(solution(first,second,dpFirst)+solution(second,first,dpSecond));
    }
}
