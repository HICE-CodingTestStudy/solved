package nineth.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class LBS {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;

    private static int[] getMaxLength(int[] arr) {
        int[] dp = new int[N];

        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < N; j++)
                if (arr[i] > arr[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
        }

        return dp;
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] inc = new int[N], dec = new int[N];

        for (int i = 0; i < N; i++)
            inc[i] = dec[N - i - 1] = Integer.parseInt(st.nextToken());

        int[] dp1 = getMaxLength(inc);
        int[] dp2 = getMaxLength(dec);

        int answer = dp1[0] + dp2[N - 1] - 1;
        for (int i = 1; i < N; i++)
            answer = Math.max(answer, dp1[i] + dp2[N - i -1] - 1);

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
