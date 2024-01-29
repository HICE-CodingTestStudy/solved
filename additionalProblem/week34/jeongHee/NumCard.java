package stack;

import java.util.Scanner;

public class NumCard {
    //https://www.acmicpc.net/problem/2591
    //숫자 카드
    static String num;
    static long[] dp;

    static boolean isValid(String stringNum) {
        int intNum = Integer.parseInt(stringNum);
        return !stringNum.startsWith("0") && intNum <= 34 && intNum > 0;
    }

    static long solution() {
        if (num.length() == 1)
            return 1;
        dp[0] = 1;
        dp[1] = isValid(num.substring(0, 2)) && num.charAt(1) != '0' ? 2 : 1;
        for (int i = 2; i < num.length(); i++) {
            if (num.charAt(i) != '0')
                dp[i] = dp[i - 1];
            if (isValid(num.substring(i - 1, i + 1)))
                dp[i] += dp[i - 2];
        }
        return dp[num.length() - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextLine();
        dp = new long[num.length()];
        System.out.println(solution());
    }
}
