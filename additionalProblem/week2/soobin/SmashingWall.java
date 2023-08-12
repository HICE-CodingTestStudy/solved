package week2.soobin;

import java.io.*;
import java.util.*;

public class SmashingWall {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private static char[][] map;
    private static List<int[]> walls;
    private static int N, M;

    private static void bfs(int[] start, int[][] visited) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(start);
        visited[start[0]][start[1]] = 1;

        while (!queue.isEmpty()) {
            int[] n = queue.poll();

            for (int[] move : moves) {
                int nr = n[0] + move[0], nc = n[1] + move[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] != 0) continue;

                if (map[nr][nc] == '1') {
                    visited[nr][nc] = visited[n[0]][n[1]] + 1;
                    continue;
                }

                queue.add(new int[] {nr, nc});
                visited[nr][nc] = visited[n[0]][n[1]] + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        walls = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                char type = row.charAt(j);
                if (type == '1') walls.add(new int[] {i, j});
                map[i][j] = type;
            }
        }

        int[][] start = new int[N][M];
        int[][] end = new int[N][M];
        bfs(new int[] {0, 0}, start);
        bfs(new int[] {N - 1, M - 1}, end);

        int min = Integer.MAX_VALUE;
        for (int[] wall : walls) {
            int wr = wall[0], wc = wall[1];
            if (start[wr][wc] != 0 && end[wr][wc] != 0)
                min = Math.min(start[wr][wc] + end[wr][wc] - 1, min);
        }

        if (walls.size() == 0) min = end[0][0];

        bw.write(String.valueOf(min == Integer.MAX_VALUE ? -1 : min));
        bw.flush();
    }
}
