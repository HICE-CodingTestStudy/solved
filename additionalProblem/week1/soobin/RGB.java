package first.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class RGB {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] house;
    private static int[][] memo;
    private static int answer = 0;

    private static int dp(int n, int color) {
        if (n <= 0) return memo[0][color] = house[0][color];

        if (memo[n][color] > 0) return memo[n][color];

        memo[n][color] = Math.min(dp(n - 1, (color + 1) % 3), dp(n - 1, (color + 2) % 3)) + house[n][color];
        return memo[n][color];
        /**
         * 0; (0 + 1) % 3 = 1, (0 + 2) % 3 = 2
         * 1; (1 + 1) % 3 = 2, (1 + 2) % 3 = 0
         * 2; (2 + 1) % 3 = 0, (2 + 2) % 3 = 1
         */
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        house = new int[N][3];
        memo = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            house[i][0] = Integer.parseInt(st.nextToken());
            house[i][1] = Integer.parseInt(st.nextToken());
            house[i][2] = Integer.parseInt(st.nextToken());
        }

        answer = Math.min(dp(N - 1, 0), Math.min(dp(N - 1, 1), dp(N - 1, 2)));

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
