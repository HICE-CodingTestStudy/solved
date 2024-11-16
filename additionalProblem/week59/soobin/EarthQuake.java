import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class EarthQuake {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};

    private static Queue<int[]> next;
    private static char[][] map;
    private static int N, M, total, nDestroyed;

    public static void main(String[] args) throws Exception {
        init();
        solution();
        System.out.println(nDestroyed + " " + (total - nDestroyed));
    }

    private static void init() throws Exception {
        next = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int sr = 0, sc = 0;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '@') {
                    sr = i;
                    sc = j;
                }

                if (map[i][j] == '*' || map[i][j] == '#') total++;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 2; j++) {
                int nr = sr + dx[i] * j;
                int nc = sc + dy[i] * j;
                if (isInvalid(nr, nc)) break;
                shake(nr, nc);
            }
        }
    }

    private static void solution() {
        while (!next.isEmpty()) {
            int r = next.peek()[0], c = next.peek()[1];
            next.poll();

            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i], nc = c + dy[i];
                if (isInvalid(nr, nc)) continue;
                shake(nr, nc);
            }
        }
    }

    private static void shake(int r, int c) {
        if (map[r][c] == '*') {
            next.add(new int[] {r, c});
            map[r][c] = '.';
            nDestroyed++;
        }

        if (map[r][c] == '#') {
            map[r][c] = '*';
        }
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == '|';
    }
}
