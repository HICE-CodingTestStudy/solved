package week10.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class MovingPipe {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] nonDiagonal = {{0, 0}};
    private static final int[][] diagonal = {{0, -1}, {-1, 0}, {0, 0}};

    private static long[][][] memo;
    private static int[][] house;
    private static int N;

    private static boolean isWall(int r, int c, int[][] moves) {
        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];
            if (isBlocked(nr, nc) || house[nr][nc] == 1) return true;
        }

        return false;
    }

    private static boolean isBlocked(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static long dp(int r, int c, int shape) {
        if (r == 0 && c == 0)
            return memo[r][c][shape] = 0;

        if (r == 0 && c == 1)
            return memo[r][c][shape] = shape == 0 ? 1 : 0;

        if (isBlocked(r, c))
            return 0;

        if (memo[r][c][shape] > -1)
            return memo[r][c][shape];

        if (shape == 0) {
            return memo[r][c][shape] = isWall(r, c, nonDiagonal)
                    ? 0 : dp(r, c - 1, 0) + dp(r, c - 1, 2);
        } else if (shape == 1) {
            return memo[r][c][shape] = isWall(r, c, nonDiagonal)
                    ? 0 : dp(r - 1, c, 1) + dp(r - 1, c, 2);
        } else {
            return memo[r][c][shape] = isWall(r, c, diagonal)
                    ? 0 : dp(r - 1, c - 1, 0) + dp(r - 1, c - 1, 1) + dp(r - 1, c - 1, 2);
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        house = new int[N][N];
        memo = new long[N][N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                memo[i][j][0] = memo[i][j][1] = memo[i][j][2] = -1;
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long answer = dp(N - 1, N - 1, 0) + dp(N - 1, N - 1, 1) + dp(N - 1, N - 1, 2);
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
