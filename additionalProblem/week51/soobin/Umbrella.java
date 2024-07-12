import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Umbrella {
    private static class Status {
        int r, c, stuff, time;

        Status(int r, int c, int stuff, int time) {
            this.r = r;
            this.c = c;
            this.stuff = stuff;
            this.time = time;
        }

        public boolean isFinished() {
            return house[r][c] == 'E' && stuff == (1 << totalStuff) - 1;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    private static char[][] house;
    private static int N, M, totalStuff;

    public static void main(String[] args) throws Exception {
        int[] start = parseInput();
        int answer = solution(start[0], start[1]);
        printAnswer(answer);
    }

    private static int[] parseInput() throws Exception {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[0]);
        house = new char[N][M];

        int[] start = new int[2];
        for (int i = 0; i < N; i++) {
            house[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (house[i][j] == 'X') {
                    house[i][j] = (char) ('0' + totalStuff);
                    totalStuff++;
                }
                if (house[i][j] == 'S') start = new int[] {i, j};
            }
        }

        return start;
    }

    private static int solution(int sr, int sc) {
        Queue<Status> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][1 << totalStuff];
        queue.add(new Status(sr, sc, 0, 0));
        visited[sr][sc][0] = true;

        while (!queue.isEmpty()) {
            Status curr = queue.poll();

            if (curr.isFinished()) return curr.time;

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dx[i], nc = curr.c + dy[i];
                if (isInvalid(nr, nc)) continue;

                int newStuff = curr.stuff;
                if (isStuff(nr, nc)) {
                    int idx = house[nr][nc] - '0';
                    newStuff |= 1 << idx;
                }

                if (visited[nr][nc][newStuff]) continue;
                queue.add(new Status(nr, nc, newStuff, curr.time + 1));
                visited[nr][nc][newStuff] = true;
            }
        }

        return 0;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || house[r][c] == '#';
    }

    private static boolean isStuff(int r, int c) {
        return house[r][c] >= '0' && house[r][c] <= '5';
    }

    private static void printAnswer(int answer) throws Exception {
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
