package eighth.soobin;


import java.io.*;
import java.util.*;

public class Cheese {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static class Coordinate {
        public int r, c;

        public Coordinate(int r, int c) { this.r = r; this.c = c; }

        public boolean equals(Object o) {
            return this.r == ((Coordinate) o).r && this.c == ((Coordinate) o).c;
        }

        public int hashCode() { return Objects.hash(r, c); }
    }

    private static char[][] fridge;
    private static boolean[][] isExposed;
    private static int N, M;

    private static boolean isUnavailable(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static void bfs() {
        boolean[][] visited = new boolean[N][M];
        isExposed = new boolean[N][M];
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate start = new Coordinate(0, 0);
        queue.add(start);
        isExposed[0][0] = true;
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Coordinate n = queue.poll();

            for (int[] move : moves) {
                int nr = n.r + move[0];
                int nc = n.c + move[1];
                if (isUnavailable(nr, nc) || visited[nr][nc] || fridge[nr][nc] == '1') continue;

                visited[nr][nc] = true;
                queue.add(new Coordinate(nr, nc));
                if (fridge[nr][nc] == '0') isExposed[nr][nc] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        Set<Coordinate> cheeseLoc = new HashSet<>();
        fridge = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                fridge[i][j] = st.nextToken().equals("0") ? '0' : '1';
                if (fridge[i][j] == '1') cheeseLoc.add(new Coordinate(i, j));
            }
        }

        int answer = 0;
        while (cheeseLoc.size() > 0) {
            Queue<Coordinate> queue = new LinkedList<>();
            bfs();

            for (Coordinate cheese : cheeseLoc) {
                int around = 0;
                for (int i = 0; i < 4; i++) {
                    int nr = cheese.r + moves[i][0], nc = cheese.c + moves[i][1];
                    if (isUnavailable(nr, nc)) continue;

                    if (isExposed[nr][nc]) around++;
                }

                if (around >= 2) {
                    queue.add(cheese);
                    fridge[cheese.r][cheese.c] = '0';
                }
            }

            for (Coordinate cheese : queue) cheeseLoc.remove(cheese);

            answer++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
