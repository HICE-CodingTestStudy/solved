package week6.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class Downhill {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves  = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static int[][] map;
    private static long[][] cases;
    private static int N, M;

    private static long dp(int r, int c) {
        if (r == 0 && c == 0)
            return cases[r][c] = 1;

        if (cases[r][c] > -1) return cases[r][c];

        long total = 0;
        for (int[] move : moves) {
            int nr = r + move[0], nc = c + move[1];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] <= map[r][c]) continue;

            total += dp(nr, nc);
        }
        return cases[r][c] = total;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cases = new long[N][M];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cases[i][j] = -1;
            }
        }

        bw.write(String.valueOf(dp(N - 1, M - 1)));
        bw.flush();
    }
}
