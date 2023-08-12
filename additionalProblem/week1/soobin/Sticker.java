package week1.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class Sticker {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] memo;

    private static int solution() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] stickers = new int[2][N];
        memo = new int[2][3];

        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                stickers[i][j] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) return Math.max(stickers[0][0], stickers[1][0]);

        memo[0][0] = stickers[0][0];
        memo[1][0] = stickers[1][0];
        memo[0][1] = memo[1][0] + stickers[0][1];
        memo[1][1] = memo[0][0] + stickers[1][1];
        if (N == 2) return Math.max(memo[0][1], memo[1][1]);

        for (int i = 2; i < N; i++) {
            int pprev = Math.max(memo[0][0], memo[1][0]);
            memo[0][2] = Math.max(memo[1][1], pprev) + stickers[0][i];
            memo[1][2] = Math.max(memo[0][1], pprev) + stickers[1][i];

            memo[0][0] = memo[0][1];
            memo[1][0] = memo[1][1];
            memo[0][1] = memo[0][2];
            memo[1][1] = memo[1][2];
        }

        return Math.max(memo[0][2], memo[1][2]);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            bw.write(String.valueOf(solution()));
            bw.newLine();
        }
        bw.flush();
    }
}
