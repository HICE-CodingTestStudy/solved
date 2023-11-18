package week26.soobin;

import java.io.*;
import java.util.*;

public class FarmManagement {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    private static boolean[][] visited;
    private static int[][] farm;
    private static int N, M;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            farm = new int[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++)
                    farm[i][j] = Integer.parseInt(st.nextToken());
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static boolean isMountainPeak(int r, int c) {
        for (int[] move : moves) {
            int nr = r + move[0], nc = c + move[1];
            if (!isValid(nr, nc)) continue;
            if (farm[nr][nc] > farm[r][c]) return false;
        }
        return true;
    }

    private static boolean findPeak(int sr, int sc) {
        if (!isMountainPeak(sr, sc)) return false;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sr, sc});
        visited[sr][sc] = true;


        boolean isPeak = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] move : moves) {
                int nr = cur[0] + move[0], nc = cur[1] + move[1];
                if (!isValid(nr, nc) || visited[nr][nc] || farm[nr][nc] != farm[cur[0]][cur[1]]) continue;

                isPeak = isPeak & isMountainPeak(nr, nc);
                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }

        return isPeak;
    }

    private static int findPeaks() {
        visited = new boolean[N][M];

        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || farm[i][j] == 0) continue;

                boolean isPeak = findPeak(i, j);
                if (isPeak) total++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = findPeaks();
        printOutput(answer);
    }
}