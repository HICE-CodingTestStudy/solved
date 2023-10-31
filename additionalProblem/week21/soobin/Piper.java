package week21.soobin;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Piper {
    private static class Coordinate {
        int r, c;

        Coordinate(int r, int c) { this.r = r; this.c = c; }

        public boolean equals(Object obj) {
            return r == ((Coordinate) obj).r && c == ((Coordinate) obj).c;
        }

        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static char[][] map;
    private static boolean[][] visited;
    private static Coordinate[][] check;
    private static int answer, N, M;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            visited = new boolean[N][M];
            check = new Coordinate[N][M];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++)
                    map[i][j] = line.charAt(j);
            }
        } catch (IOException e) {}
    }

    private static void printOutput() {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int convertDirection(char c) {
        if (c == 'D') return 0;
        if (c == 'U') return 1;
        if (c == 'R') return 2;
        return 3;
    }

    private static Coordinate dfs(int r, int c) {
        char dirChar = map[r][c];
        int direction = convertDirection(dirChar);
        int nr = r + moves[direction][0];
        int nc = c + moves[direction][1];

        if (check[nr][nc] != null) {
            return check[r][c] = check[nr][nc];
        }

        if (visited[nr][nc]) {
            answer++;
            return check[r][c] = new Coordinate(nr, nc);
        }

        visited[nr][nc] = true;
        return check[r][c] = dfs(nr, nc);
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;

                visited[i][j] = true;
                dfs(i, j);
            }
        }
    }

    public static void main(String[] args) {
        parseInput();
        solution();
        printOutput();
    }
}
