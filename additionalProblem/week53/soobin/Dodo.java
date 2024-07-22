import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dodo {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX = 1000000000;

    private static Queue<int[]> pq;
    private static char[][] map;
    private static int[][] dist;
    private static int N, M;
    private static int sr, sc, er, ec;

    public static void main(String[] args) throws Exception {
        parseInput();
        String answer = solution();
        System.out.println(answer);
    }

    private static void parseInput() throws Exception {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                dist[i][j] = MAX;
                if (map[i][j] == 'C') {
                    sr = i;
                    sc = j;
                }
                if (map[i][j] == 'E') {
                    er = i;
                    ec = j;
                }
            }
        }
    }

    private static String solution() {
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        dist[sr][sc] = 0;
        pq.add(new int[] {sr, sc, 0});

        while (!pq.isEmpty()) {
            int r = pq.peek()[0], c = pq.peek()[1], d = pq.peek()[2];
            pq.poll();

            if (dist[r][c] < d) continue;

            // 뚫린 곳
            if (map[r][c] == 'X') {
                int nr = r + 1;
                while (isValid(nr, c) && map[nr][c] == 'X') nr++;
                if (isValid(nr, c)) move(nr, c, d + 10);
            } else {
                // 위 사다리
                if (map[r][c] == 'L' && isValid(r - 1, c))
                    move(r - 1, c, d + 5);

                // 아래 사다리
                if (isValid(r + 1, c) && map[r + 1][c] == 'L')
                    move(r + 1, c, d + 5);

                // 좌우 이동
                if (isValid(r, c + 1)) move(r, c + 1, d + 1);
                if (isValid(r, c - 1)) move(r, c - 1, d + 1);
            }
        }

        return dist[er][ec] != MAX ? String.valueOf(dist[er][ec]) : "dodo sad";
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M && map[r][c] != 'D';
    }

    private static void move(int r, int c, int d) {
        if (dist[r][c] > d) {
            dist[r][c] = d;
            pq.add(new int[] {r, c, d});
        }
    }
}
