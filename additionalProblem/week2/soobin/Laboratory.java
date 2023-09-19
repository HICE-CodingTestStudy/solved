package week2.soobin;

import java.io.*;
import java.util.*;

public class Laboratory {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int[][] lab;
    private static List<int[]> viruses;
    private static int nonSafe = 0;
    private static int maxSafe = Integer.MIN_VALUE;
    private static int N, M;

    private static void setWall(int walls) {
        if (walls == 3) {
            maxSafe = Math.max(maxSafe, countSafe());
            return;
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 2 || lab[i][j] == 1) continue;

                lab[i][j] = 1; nonSafe++;
                setWall(walls + 1);
                lab[i][j] = 0; nonSafe--;
            }
    }

    private static int countSafe() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        for (int[] virus : viruses) {
            queue.add(virus);
            visited[virus[0]][virus[1]] = true;
        }

        int infected = 0;
        while (!queue.isEmpty()) {
            int[] n = queue.poll();

            for (int[] move : moves) {
                int nr = n[0] + move[0];
                int nc = n[1] + move[1];
                if ((nr < 0 || nr >= N) || (nc < 0 || nc >= M) || visited[nr][nc] || lab[nr][nc] != 0) continue;

                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
                infected++;
            }
        }
        return N * M - infected - nonSafe;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        viruses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int type = Integer.parseInt(st.nextToken());
                if (type == 1 || type == 2) nonSafe++;
                if (type == 2) viruses.add(new int[] {i, j});
                lab[i][j] = type;
            }
        }

        setWall(0);
        bw.write(String.valueOf(maxSafe));
        bw.flush();
    }
}
