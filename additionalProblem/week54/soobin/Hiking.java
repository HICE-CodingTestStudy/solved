import java.io.*;
import java.util.*;

public class Hiking {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] MOVES = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    private static final int MAX = 10000000;

    private static int[][] height, totalTimeCosts;
    private static int N, M, D, T;

    public static void main(String[] args) throws IOException {
        parseInput();
        hikeMountain(0,0, true);
        hikeMountain(0, 0, false);
        int answer = findMaxHeight();
        System.out.println(answer);
    }

    private static void parseInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        height = new int[N][M];
        totalTimeCosts = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++)
                height[i][j] = line.charAt(j) >= 'a'
                        ? line.charAt(j) - 'a' + 26
                        : line.charAt(j) - 'A';
        }
    }

    private static void hikeMountain(int sr, int sc, boolean isUp) {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        int[][] tempCosts = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(tempCosts[i], MAX);
        pq.add(new int[] {sr, sc, 0});
        tempCosts[sr][sc] = 0;

        while (!pq.isEmpty()) {
            int r = pq.peek()[0], c = pq.peek()[1], t = pq.peek()[2];
            pq.poll();

            if (tempCosts[r][c] < t) continue;

            for (int[] move : MOVES) {
                int nr = r + move[0], nc = c + move[1];
                if (isInvalid(nr, nc) || Math.abs(height[nr][nc] - height[r][c]) > T) continue;

                int time = calcTime(height[nr][nc], height[r][c], isUp);
                if (tempCosts[nr][nc] <= tempCosts[r][c] + time) continue;

                pq.add(new int[] {nr, nc, t + time});
                tempCosts[nr][nc] = t + time;
            }
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                totalTimeCosts[i][j] += tempCosts[i][j];
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static int calcTime(int x, int y, boolean isUp) {
        int diff = isUp ? x - y : y - x;
        return diff <= 0 ? 1 : diff * diff;
    }

    private static int findMaxHeight() {
        int max = height[0][0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (totalTimeCosts[i][j] > D) continue;
                max = Math.max(max, height[i][j]);
            }
        }

        return max;
    }
}
