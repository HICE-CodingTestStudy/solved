package week16.soobin;

import java.io.*;
import java.util.*;

public class BuildingSwimmingPool {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int[][] walls;
    private static boolean[][] visited;
    private static int N, M, minHeight, maxHeight;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            walls = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++)
                    walls[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        } catch (IOException e) {}
    }

    private static void findMinMaxHeight() {
        minHeight = walls[0][0];
        maxHeight = walls[0][0];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                maxHeight = Math.max(walls[i][j], maxHeight);
                minHeight = Math.min(walls[i][j], minHeight);
            }
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isOuterWall(int r, int c) {
        return r == 0 || r == N - 1 || c == 0 || c == M - 1;
    }

    private static int fillWaterIntoSmallPool(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> pool = new ArrayList<>();
        int currentHeight = walls[i][j], wallHeight = Integer.MAX_VALUE;

        int[] start = new int[] {i, j};
        visited[start[0]][start[1]] = true;
        queue.add(start);
        pool.add(start);

        boolean isFlood = false;
        while (!queue.isEmpty()) {
            int[] wall = queue.poll();
            for (int[] move : moves) {
                int nr = wall[0] + move[0], nc = wall[1] + move[1];
                // 현재 높이보다 높은 벽 중 가장 낮은 높이가 물을 최대로 채울 수 있는 높이
                if (walls[nr][nc] > currentHeight) {
                    wallHeight = Math.min(wallHeight, walls[nr][nc]);
                    continue;
                }

                // 가장자리 벽이거나 현재 높이보다 낮은 벽이 있으면 물이 넘침 -> 수영장 건설 불가
                if (isOuterWall(nr, nc) || walls[nr][nc] < currentHeight) {
                    isFlood = true;
                    continue;
                }

                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});
                pool.add(new int[] {nr, nc});
            }
        }

        if (!isFlood) {
            for (int[] p : pool) {
                walls[p[0]][p[1]] = wallHeight;
                visited[p[0]][p[1]] = false;
            }
        }

        return isFlood ? 0 : pool.size() * (wallHeight - currentHeight);
    }

    private static int fillWaterUntilAvailable() {
        int totalAmount = 0;

        for (int h = minHeight; h <= maxHeight; h++) {
            // 가장자리 벽은 어차피 물을 채울 수 없으므로 거르고 시작
            for (int i = 1; i < N - 1; i++)
                for (int j = 1; j < M - 1; j++) {
                    if (visited[i][j] || walls[i][j] != h) continue;
                    totalAmount += fillWaterIntoSmallPool(i, j);
                }
        }

        return totalAmount;
    }

    public static void main(String[] args) throws IOException {
        parseInput();
        findMinMaxHeight();
        int answer = fillWaterUntilAvailable();
        printAnswer(answer);
    }
}
