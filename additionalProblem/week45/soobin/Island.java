import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Island {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int[][] map;
    private static int N, M, sr, sc;

    public static void main(String[] args) throws IOException {
        parseInput();
        int answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
                if (map[i][j] == 2) {
                    sr = i;
                    sc = j;
                }
            }
        }
    }

    private static int solution() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        queue.add(new int[] {sr, sc, 0});
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int r = queue.peek()[0], c = queue.peek()[1], dist = queue.peek()[2];
            queue.poll();

            if (map[r][c] >= 3) return dist;

            for (int[] move : moves) {
                int nr = r + move[0], nc = c + move[1];
                if (isInvalid(nr, nc) || visited[nr][nc]) continue;

                queue.add(new int[] {nr, nc, dist + 1});
                visited[nr][nc] = true;
            }
        }

        return -1;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == 1;
    }

    private static void printAnswer(int answer) throws IOException {
        bw.write(answer > -1 ? "TAK" : "NIE");
        if (answer > 0) bw.write("\n" + answer);
        bw.flush();
    }
}
