package week7.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class NoMoreFineDust {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int[] cleaner = new int[2];

    private static int R, C;
    private static int[][] dust;

    private static boolean isMovable(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static int spread(int i, int j, int[][] tmp) {
        int total = 0;
        for (int[] move : moves) {
            int nr = i + move[0], nc = j + move[1];
            if (!isMovable(nr, nc) || dust[nr][nc] == -1) continue;

            tmp[nr][nc] += dust[i][j] / 5;
            total++;
        }

        return total;
    }

    private static void diffuse() {
        int[][] tmp = new int[R][C];

        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                dust[i][j] -= dust[i][j] / 5 * spread(i, j, tmp);

        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                dust[i][j] += tmp[i][j];
    }

    private static int move(int r, int c, int prev) {
        int tmp = dust[r][c];
        dust[r][c] = prev;
        return tmp;
    }

    private static void wind() {
        int prev = dust[cleaner[0]][1];
        dust[cleaner[0]][1] = 0;

        // upper
        // right
        for (int j = 1; j < C - 1; j++)
            prev = move(cleaner[0], j + 1, prev);
        // up
        for (int i = cleaner[0]; i > 0; i--)
            prev = move(i - 1, C - 1, prev);
        // left
        for (int j = C - 1; j > 0; j--)
            prev = move(0, j - 1, prev);
        // down
        for (int i = 0; i < cleaner[0] - 1; i++)
            prev = move(i + 1, 0, prev);

        prev = dust[cleaner[1]][1];
        dust[cleaner[1]][1] = 0;

        // lower
        // right
        for (int j = 1; j < C - 1; j++)
            prev = move(cleaner[1], j + 1, prev);
        // down
        for (int i = cleaner[1]; i < R - 1; i++)
            prev = move(i + 1, C - 1, prev);
        // left
        for (int j = C - 1; j > 0; j--)
            prev = move(R - 1, j - 1, prev);
        // up
        for (int i = R - 1; i > cleaner[1] + 1; i--)
            prev = move(i - 1, 0, prev);
    }

    private static void simulation() {
        diffuse();
        wind();
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        dust = new int[R][C];

        int T = Integer.parseInt(st.nextToken());

        // Initialize room condition
        int c = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int x = Integer.parseInt(st.nextToken());

                if (x == -1)
                    cleaner[c++] = i;

                dust[i][j] = x;
            }
        }

        while (T-- > 0) {
            simulation();
        }

        int remain = 0;
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if (dust[i][j] > 0) remain += dust[i][j];

        bw.write(String.valueOf(remain));
        bw.flush();
    }
}
