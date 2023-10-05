package week14.soobin;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Shepherd {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static char[][] pasture;
    private static boolean[][] visited;
    private static int N, M, sheep, wolves;

    private static boolean isValid(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M && pasture[i][j] != '#';
    }

    private static void bfs(int startI, int startJ) {
        Queue<int[]> queue = new LinkedList<>();
        visited[startI][startJ] = true;
        queue.add(new int[] {startI, startJ});

        int currentSheep = 0, currentWolves = 0;
        while (!queue.isEmpty()) {
            int[] n = queue.poll();
            int r = n[0], c = n[1];
            if (pasture[r][c] == 'v') currentWolves++;
            else if (pasture[r][c] == 'k') currentSheep++;

            for (int[] move : moves) {
                int nr = r + move[0], nc = c + move[1];
                if (!isValid(nr, nc) || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});
            }
        }

        if (currentSheep == 0 || currentWolves == 0) {
            sheep += currentSheep;
            wolves += currentWolves;
            return;
        }

        if (currentSheep > currentWolves) sheep += currentSheep;
        else wolves += currentWolves;
    }

    private static void solution() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                if (pasture[i][j] == '#' || visited[i][j]) continue;

                bfs(i, j);
            }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pasture = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++)
                pasture[i][j] = line.charAt(j);
        }

        solution();
        bw.write(String.format("%d %d", sheep, wolves));
        bw.flush();
    }
}
