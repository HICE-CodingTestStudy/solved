package week13.soobin;

import java.io.*;
import java.util.*;

public class CoinDivision {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Queue<int[]> coins = new LinkedList<>();

    private static boolean[] dp;
    private static int total, target;

    private static void clear() {
        coins.clear();
        dp = new boolean[100001];
        total = 0;
    }

    private static void input(int N) throws IOException {
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int coin = Integer.parseInt(st.nextToken());
            int quantity = Integer.parseInt(st.nextToken());

            coins.add(new int[] {coin, quantity});
            for (int i = 1; i <= quantity; i++)
                dp[coin * i] = true;
            total += coin * quantity;
        }
    }

    private static boolean divideCoin() {
        dp[0] = true;
        while (!coins.isEmpty()) {
            int[] coin = coins.poll();
            int value = coin[0], quantity = coin[1];

            for (int i = target; i >= value; i--) {
                if (!dp[i - value]) continue;

                for (int j = 1; j <= quantity; j++) {
                    if (i - value + value * j > target) break;
                    dp[i - value + value * j] = true;
                }

                if (dp[target]) return true;
            }
        }

        return dp[target];
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 3; i++) {
            int N = Integer.parseInt(br.readLine());
            clear();
            input(N);

            if (total % 2 > 0) {
                bw.write(String.format("%d\n", 0));
                continue;
            }

            target = total / 2;
            if (dp[target]) {
                bw.write(String.format("%d\n", 1));
                continue;
            }

            int result = divideCoin() ? 1 : 0;
            bw.write(String.format("%d\n", result));
        }
        bw.flush();
    }
}
