package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DdongGame {
    //https://www.acmicpc.net/problem/23815
    //똥게임
    static final String DDONG = "ddong game";

    static long calc(char type, long option, long now) {
        switch (type) {
            case '+':
                now += option;
                break;
            case '-':
                now -= option;
                break;
            case '*':
                now *= option;
                break;
            case '/':
                now /= option;
        }
        return now;
    }

    static long max(String fir, String sec, long now) {
        return Math.max(calc(fir.charAt(0), fir.charAt(1) - '0', now),
                calc(sec.charAt(0), sec.charAt(1) - '0', now));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        long[][] dp = new long[N + 1][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        boolean canNoSkip = true;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            String fir = st.nextToken();
            String sec = st.nextToken();
            long nonSkip, skip;
            if (canNoSkip) {
                nonSkip = max(fir, sec, dp[i - 1][0]);
                skip = Math.max(max(fir, sec, dp[i - 1][1]), dp[i - 1][0]);
                if (nonSkip <= 0) canNoSkip = false;
            } else {
                nonSkip = max(fir, sec, dp[i - 1][1]);
                skip = max(fir, sec, dp[i - 1][1]);
            }

            dp[i][0] = nonSkip <= 0 ? 0 : nonSkip;
            dp[i][1] = skip;
            if (skip <= 0) {
                System.out.println(DDONG);
                return;
            }
        }
        long ans = Math.max(dp[N][0], dp[N][1]);
        System.out.println(ans);
    }
}
