package week5.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class BuyingCard {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] cards;
    private static int[] memo;

    private static int dp(int n) {
        if (n == 1) return memo[n] = cards[n];

        if (memo[n] > 0) return memo[n];

        int max = n * cards[1];
        for (int i = 1; i <= n / 2; i++) {
            int tmp = dp(i) + dp(n - i);
            max = Math.max(max, tmp);
        }
        return memo[n] = Math.max(max, cards[n]);
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        cards = new int[N + 1];
        memo = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int card = Integer.parseInt(st.nextToken());
            cards[i] = card;
        }

        bw.write(String.valueOf(dp(N)));
        bw.flush();
    }
}
