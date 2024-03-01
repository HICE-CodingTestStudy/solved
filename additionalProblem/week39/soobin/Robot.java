import java.io.*;
import java.util.*;

public class Robot {
    private static class Status {
        int r, c, direction, numOrder;

        Status(String[] input) {
            this.r = Integer.parseInt(input[0]) - 1;
            this.c = Integer.parseInt(input[1]) - 1;
            this.direction = Integer.parseInt(input[2]) - 1;
            this.numOrder = 0;
        }

        Status(int r, int c, int direction, int numOrder) {
            this.r = r;
            this.c = c;
            this.direction = direction;
            this.numOrder = numOrder;
        }

        public boolean isDestination() {
            return r == dest.r && c == dest.c && direction == dest.direction;
        }

        public Status move(int nr, int nc, int direction) {
            return new Status(nr, nc, direction, numOrder + 1);
        }

        public Status turn(int direction) {
            return new Status(r, c, direction, numOrder + 1);
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static boolean[][] isRailSet;
    private static Status start, dest;
    private static int N, M, answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        parseInput();
        moveRobot();
        printAnswer();
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);

            isRailSet = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                for (int j = 0; j < M; j++) {
                    isRailSet[i][j] = input[j].charAt(0) == '0';
                }
            }

            start = new Status(br.readLine().split(" "));
            dest = new Status(br.readLine().split(" "));
        } catch (IOException ignored) {}
    }

    private static void moveRobot() {
        Queue<Status> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][4];
        visited[start.r][start.c][start.direction] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Status current = queue.poll();

            if (current.isDestination())
                answer = Math.min(answer, current.numOrder);

            for (int i = 0; i < 4; i++) {
                if (isVerticalDirection(current.direction, i)) continue;

                if (current.direction == i) {
                    for (int j = 1; j <= 3; j++) {
                        int nr = current.r + moves[i][0] * j, nc = current.c + moves[i][1] * j;

                        if (isInvalid(nr, nc) || !isRailSet[nr][nc]) break;
                        if (visited[nr][nc][i]) continue;

                        Status next = current.move(nr, nc, i);
                        visited[nr][nc][i] = true;
                        queue.add(next);
                    }
                }
                else if (!visited[current.r][current.c][i]) {
                    Status next = current.turn(i);
                    visited[current.r][current.c][i] = true;
                    queue.add(next);
                }
            }
        }
    }

    private static boolean isVerticalDirection(int prev, int next) {
        return (prev == 0 && next == 1) || (prev == 1 && next == 0)
                || (prev == 2 && next == 3) || (prev == 3 && next == 2);
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }

    private static void printAnswer() {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
