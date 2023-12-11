package week11.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class Tetromino {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int[][] map;
    private static boolean[][] visited;
    private static int max, N, M;

    private static void spread(int r, int c, int sum, int blocks) {
        if (blocks == 4) {
            max = Math.max(max, sum);
            visited[r][c] = false;
            return;
        }

        for (int[] move : moves) {
            int nr = r + move[0];
            int nc = c + move[1];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;

            visited[nr][nc] = true;
            spread(nr, nc, sum + map[nr][nc], blocks + 1);
            visited[nr][nc] = false;
        }
    }

    private static int sumOfMountain(int r, int c, int exceptIdx) {
        int sum = map[r][c];
        for (int i = 0; i < 4; i++) {
            int nr = r + moves[i][0];
            int nc = c + moves[i][1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M || i == exceptIdx) continue;

            sum += map[nr][nc];
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 직선으로 퍼질 수 있는 모양
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                spread(i, j, map[i][j], 1);
                visited[i][j] = false;
            }

        // '뫼 산' 모양
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++)
                    max = Math.max(max, sumOfMountain(i, j, k));
            }

        bw.write(String.valueOf(max));
        bw.flush();
    }
}
