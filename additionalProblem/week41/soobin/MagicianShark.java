import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MagicianShark {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int[][] map;
    private static int[] stormLevels;
    private static int N;

    public static void main(String[] args) {
        parseInput();
        for (int level : stormLevels) {
            fireStorm(level);
            meltIce();
        }
        int[] answer = countAnswer();
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            N = 1 << Integer.parseInt(input[0]);
            int Q = Integer.parseInt(input[1]);
            map = new int[N][N];
            stormLevels = new int[Q];

            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                }
            }

            input = br.readLine().split(" ");
            for (int i = 0; i < Q; i++)
                stormLevels[i] = Integer.parseInt(input[i]);
        } catch (IOException ignored) {}
    }

    private static void fireStorm(int level) {
        if (level == 0) return;

        int[][] copied = new int[N][N];
        level = 1 << (level);

        for (int i = 0; i < N; i += level)
            for (int j = 0; j < N; j += level)
                rotate(i, j, level, copied);

        map = copied;
    }

    private static void rotate(int sr, int sc, int level, int[][] copied) {
        for (int i = 0; i < level; i++)
            for (int j = 0; j < level; j++)
                copied[sr + i][sc + j] = map[sr + level - 1 - j][sc + i];
    }

    private static void meltIce() {
        int[][] copied = new int[N][N];
        for (int i = 0; i < N; i++)
            copied[i] = Arrays.copyOf(map[i], N);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) continue;
                if (!isSurroundedByIce(i, j)) copied[i][j]--;
            }
        map = copied;
    }

    private static boolean isSurroundedByIce(int r, int c) {
        int count = 0;
        for (int[] move : moves) {
            int nr = r + move[0], nc = c + move[1];
            if (isInvalid(nr, nc)) continue;
            if (map[nr][nc] > 0) count++;
        }
        return count >= 3;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static int[] countAnswer() {
        int totalIce = 0, maxGlacier = 0;
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || map[i][j] == 0) continue;
                int[] glacier = countGlacier(i, j, visited);
                totalIce += glacier[0];
                maxGlacier = Math.max(maxGlacier, glacier[1]);
            }
        }

        return new int[] {totalIce, maxGlacier};
    }

    private static int[] countGlacier(int r, int c, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {r, c});
        visited[r][c] = true;
        int ice = 0, glacier = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            ice += map[curr[0]][curr[1]];
            glacier++;

            for (int[] move : moves) {
                int nr = curr[0] + move[0], nc = curr[1] + move[1];
                if (isInvalid(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;
                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }

        return new int[] {ice, glacier};
    }

    private static void printAnswer(int[] answer) {
        try {
            bw.write(answer[0] + "\n" + answer[1]);
            bw.flush();
        } catch (IOException ignored) {}
    }
}
