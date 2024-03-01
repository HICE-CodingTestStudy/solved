import java.io.*;
import java.util.*;

public class Fire {
    private static class Status {
        int r, c, time;

        Status(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        public Status getNextStatus(int direction) {
            int nr = r + moves[direction][0], nc = c + moves[direction][1];
            return new Status(nr, nc, time + 1);
        }

        public boolean isEscapable() {
            return (r == 0 || c == 0 || r == N - 1 || c == M - 1) && (maze[r][c] == '.' || timeOnFire[r][c] > time);
        }

        public boolean isUnreachable() {
            return isInvalid(r, c) || isFire() || maze[r][c] == '#';
        }

        private boolean isFire() {
            return maze[r][c] == 'F' && timeOnFire[r][c] <= time;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static char[][] maze;
    private static int[][] timeOnFire;
    private static Queue<int[]> fireLoc;
    private static int N, M, startR, startC;

    public static void main(String[] args) {
        parseInput();
        spreadFire();
        int answer = escapeMaze();
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);

            fireLoc = new ArrayDeque<>();
            timeOnFire = new int[N][M];
            maze = new char[N][M];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    char type = line.charAt(j);
                    timeOnFire[i][j] = -1;
                    if (type == 'J') {
                        startR = i;
                        startC = j;
                        maze[i][j] = '.';
                        continue;
                    }

                    if (type == 'F') {
                        fireLoc.add(new int[]{i, j});
                        timeOnFire[i][j] = 0;
                    }
                    maze[i][j] = type;
                }
            }
        } catch (IOException ignored) {}
    }

    private static void spreadFire() {
        while (!fireLoc.isEmpty()) {
            int fr = fireLoc.peek()[0], fc = fireLoc.peek()[1];
            fireLoc.poll();
            for (int[] move : moves) {
                int nr = fr + move[0], nc = fc + move[1];
                if (isInvalid(nr, nc) || maze[nr][nc] != '.') continue;

                timeOnFire[nr][nc] = timeOnFire[fr][fc] + 1;
                fireLoc.add(new int[] {nr, nc});
                maze[nr][nc] = 'F';
            }
        }
    }

    private static int escapeMaze() {
        Queue<Status> statuses = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        visited[startR][startC] = true;
        statuses.add(new Status(startR, startC, 0));

        while (!statuses.isEmpty()) {
            Status current = statuses.poll();
            if (current.isEscapable()) return current.time + 1;

            for (int i = 0; i < 4; i++) {
                Status next = current.getNextStatus(i);
                if (next.isUnreachable() || visited[next.r][next.c]) continue;

                visited[next.r][next.c] = true;
                statuses.add(next);
            }
        }

        return -1;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(answer == -1 ? "IMPOSSIBLE" : String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
