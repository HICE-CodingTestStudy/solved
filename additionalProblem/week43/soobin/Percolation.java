import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Percolation {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static boolean[][] isConductor;
    private static int N, M;

    public static void main(String[] args) {
        parseInput();
        boolean answer = canElectricityPassThrough();
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            isConductor = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++)
                    isConductor[i][j] = line.charAt(j) == '0';
            }
        } catch (IOException ignored) {}
    }

    private static boolean canElectricityPassThrough() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] isPassed = new boolean[N][M];

        for (int i = 0; i < M; i++)
            if (isConductor[0][i]) {
                queue.add(new int[] {0, i});
                isPassed[0][i] = true;
            }


        while (!queue.isEmpty()) {
            int r = queue.peek()[0], c = queue.peek()[1];
            queue.poll();

            for (int[] move : moves) {
                int nr = r + move[0], nc = c + move[1];
                if (isInvalid(nr, nc) || isPassed[nr][nc] || !isConductor[nr][nc]) continue;

                queue.add(new int[] {nr, nc});
                isPassed[nr][nc] = true;
            }
        }

        for (int i = 0; i < M; i++)
            if (isPassed[N - 1][i]) return true;
        return false;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static void printAnswer(boolean answer) {
        try {
            bw.write(answer ? "YES" : "NO");
            bw.flush();
        } catch (IOException ignored) {}
    }
}
