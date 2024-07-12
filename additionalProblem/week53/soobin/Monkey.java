import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Monkey {
    private static class Status {
        int row, col, time, k;

        Status(int row, int col, int time, int k) {
            this.row = row;
            this.col = col;
            this.time = time;
            this.k = k;
        }

        public boolean isEnd() {
            return row == H - 1 && col == W - 1;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] HORSE_MOVES = {{-1, -2}, {-2, -1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {1, 2}, {2, 1}};
    private static final int[][] MOVES = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static boolean[][] isBlock;
    private static int W, H, K;

    public static void main(String[] args) throws Exception {
        parseInput();
        int answer = solution();
        System.out.println(answer);
    }

    private static void parseInput() throws Exception {
        K = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        W = Integer.parseInt(input[0]);
        H = Integer.parseInt(input[1]);
        isBlock = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < W; j++)
                isBlock[i][j] = input[j].charAt(0) == '1';
        }
    }

    private static int solution() {
        Queue<Status> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[H][W][K + 1];
        queue.add(new Status(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Status curr = queue.poll();

            if (curr.isEnd()) return curr.time;

            if (curr.k < K) {
                for (int[] move : HORSE_MOVES) {
                    int nr = curr.row + move[0], nc = curr.col + move[1];
                    if (isInvalid(nr, nc) || visited[nr][nc][curr.k + 1]) continue;
                    queue.add(new Status(nr, nc, curr.time + 1, curr.k + 1));
                    visited[nr][nc][curr.k + 1] = true;
                }
            }

            for (int[] move : MOVES) {
                int nr = curr.row + move[0], nc = curr.col + move[1];
                if (isInvalid(nr, nc) || visited[nr][nc][curr.k]) continue;
                queue.add(new Status(nr, nc, curr.time + 1, curr.k));
                visited[nr][nc][curr.k] = true;
            }
        }

        return -1;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W || isBlock[r][c];
    }
}
