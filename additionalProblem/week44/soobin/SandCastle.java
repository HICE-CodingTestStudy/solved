import java.io.*;
import java.util.*;

public class SandCastle {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}};

    private static Queue<int[]> queue;
    private static int[][] castle;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        parseInput();
        int answer = countTotalWave();
        printAnswer(answer);
    }

    private static void parseInput() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        queue = new ArrayDeque<>();
        castle = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == '.') queue.add(new int[] {i, j});
                else castle[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }
    }

    private static int countTotalWave() {
        int waves = 0;

        while (true) {
            int sand = queue.size();
            wave(sand);
            if (queue.isEmpty()) break;
            waves++;
        }

        return waves;
    }

    private static void wave(int k) {
        while (k-- > 0) {
            int r = queue.peek()[0], c = queue.peek()[1];
            queue.poll();

            for (int[] move : moves) {
                int nr = r + move[0], nc = c + move[1];
                if (isInvalid(nr, nc) || castle[nr][nc] == 0) continue;
                if (castle[nr][nc] > 0) castle[nr][nc]--;
                if (castle[nr][nc] == 0) queue.add(new int[] {nr, nc});
            }
        }
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static void printAnswer(int answer) throws IOException {
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
