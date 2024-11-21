import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SundayMorning {
    private static class State {
        int r, c, numThrough, numPass;

        public State(int r, int c, int numThrough, int numPass) {
            this.r = r;
            this.c = c;
            this.numThrough = numThrough;
            this.numPass = numPass;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int MAX = 100000;

    private static char[][] map;
    private static int[][][] dist;
    private static int N, M;
    private static int sr, sc, dr, dc;

    public static void main(String[] args) throws Exception {
        init();
        dijkstra();
        System.out.println(dist[dr][dc][0] + " " + dist[dr][dc][1]);
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    sr = i;
                    sc = j;
                }
                if (map[i][j] == 'F') {
                    dr = i;
                    dc = j;
                }
            }
        }
    }

    private static void dijkstra() {
        Queue<State> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.numThrough == o2.numThrough) return o1.numPass - o2.numPass;
            return o1.numThrough - o2.numThrough;
        });
        dist = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist[i][j][0] = dist[i][j][1] = MAX;
            }
        }
        dist[sr][sc][0] = dist[sr][sc][1] = 0;
        pq.add(new State(sr, sc, 0 ,0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            if (isMin(curr.r, curr.c, curr.numThrough, curr.numPass)) continue;

            for (int[] move : moves) {
                int nr = curr.r + move[0], nc = curr.c + move[1];
                if (isInvalid(nr, nc)) continue;

                int numThrough = curr.numThrough, numPass = curr.numPass;
                if (map[nr][nc] == 'g') {
                    numThrough++;
                } else if (isTrashAround(nr, nc)) {
                    numPass++;
                }

                if (dist[nr][nc][0] > numThrough || dist[nr][nc][0] == numThrough && dist[nr][nc][1] > numPass) {
                    dist[nr][nc][0] = numThrough;
                    dist[nr][nc][1] = numPass;
                    pq.add(new State(nr, nc, numThrough, numPass));
                }
            }
        }
    }

    private static boolean isMin(int r, int c, int nt, int np) {
        return dist[r][c][0] < nt
                || (dist[r][c][0] == nt && dist[r][c][1] < np);
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static boolean isTrashAround(int r, int c) {
        if (map[r][c] != '.') return false;

        for (int[] move : moves) {
            int nr = r + move[0], nc = c + move[1];
            if (isInvalid(nr, nc)) continue;
            if (map[nr][nc] == 'g') return true;
        }
        return false;
    }
}
