package third.soobin;

import java.io.*;

public class PrinaryNumber {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static long[] memo;

    private static long dp(int n) {
        if (n <= 2) return memo[n] = 1;

        if (memo[n] > 0) return memo[n];

        return memo[n] = dp(n - 1) + dp(n - 2);
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        memo = new long[N + 1];
        long answer = dp(N);
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
