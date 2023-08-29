package seventh.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class CalculateSurface {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] around = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static int[][] blocks;
    private static int N, M;

    private static boolean isApplicable(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static int getOuter(int r, int c) {
        int outer = 0;
        for (int[] a : around) {
            int nr = r + a[0];
            int nc = c + a[1];

            if (!isApplicable(nr, nc)) outer++;
        }

        return outer;
    }

    private static int solution() {
        int totalSpace = 0;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                totalSpace += getOuter(i, j) * blocks[i][j];

                if (isApplicable(i + 1, j))
                    totalSpace += Math.abs(blocks[i][j] - blocks[i + 1][j]);
                if (isApplicable(i, j + 1))
                    totalSpace += Math.abs(blocks[i][j] - blocks[i][j + 1]);
            }

        return totalSpace + (2 * N * M);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        blocks = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                blocks[i][j] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(solution()));
        bw.flush();
    }
}
