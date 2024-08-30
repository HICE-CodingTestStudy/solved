import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class IceMaze {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static final int MAX = 10000000;

    private static char[][] map;
    private static int N, M, sr, sc, er, ec;

    public static void main(String[] args) throws Exception {
        parseInput();
        int answer = solution();
        System.out.println(answer);
    }

    private static void parseInput() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = line[j];
                if (line[j] == 'T') {
                    sr = i;
                    sc = j;
                    map[sr][sc] = '0';
                }
                if (line[j] == 'E') {
                    er = i;
                    ec = j;
                }
            }
        }
    }

    private static int solution() {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], MAX);
        }
        pq.add(new int[] {sr, sc, 0});
        dist[sr][sc] = 0;

        while (!pq.isEmpty()) {
            int r = pq.peek()[0], c = pq.peek()[1], time = pq.peek()[2];
            pq.poll();

            if (dist[r][c] < time) continue;

            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i], nc = c + dy[i];
                int sliding = 0;
                while (!isInvalid(nr, nc) && isIce(nr, nc)) {
                    sliding += map[nr][nc] - '0';
                    nr += dx[i];
                    nc += dy[i];
                }

                if (isInvalid(nr, nc) || map[nr][nc] == 'H') continue;
                if (map[nr][nc] == 'E') {
                    dist[nr][nc] = Math.min(dist[nr][nc], sliding + time);
                    continue;
                }

                nr -= dx[i];
                nc -= dy[i];
                if (dist[nr][nc] > sliding + time) {
                    pq.add(new int[] {nr, nc, sliding + time});
                    dist[nr][nc] = sliding + time;
                }
            }
        }

        return dist[er][ec] == MAX ? -1 : dist[er][ec];
    }

    private static boolean isIce(int r, int c) {
        return map[r][c] >= '0' && map[r][c] <= '9';
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }
}
