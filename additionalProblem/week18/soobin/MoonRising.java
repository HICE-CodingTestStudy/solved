package week18.soobin;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MoonRising {
    private static class Status {
        int row, col, move, keys;

        Status(int row, int col, int move, int keys) {
            this.row = row;
            this.col = col;
            this.move = move;
            this.keys = keys;
        }

        void addKey(int keyType) {
            keys = keys | (1 << keyType);
        }

        boolean isKeyAvailable(int keyType) {
            return (keys & (1 << keyType)) > 0;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static char[][] maze;
    private static boolean[][][] visited;
    private static int N, M;

    private static int[] parseInput() {
        int[] start = new int[2];
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            maze = new char[N][M];
            visited = new boolean[1 << 7][N][M];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    maze[i][j] = line.charAt(j);
                    if (maze[i][j] == '0') start = new int[] {i, j};
                }
            }
        } catch (IOException e) {}
        return start;
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M && maze[r][c] != '#';
    }

    private static boolean isKey(int r, int c) {
        return maze[r][c] >= 'a' && maze[r][c] <= 'f';
    }

    private static boolean isDoor(int r, int c) {
        return maze[r][c] >= 'A' && maze[r][c] <= 'F';
    }

    private static int escapeMaze(int[] start) {
        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(start[0], start[1], 0, 0));
        visited[0][start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            Status curr = queue.poll();

            for (int[] move : moves) {
                int nr = curr.row + move[0], nc = curr.col + move[1];
                if (!isValid(nr, nc) || visited[curr.keys][nr][nc]) continue;

                Status next = new Status(nr, nc, curr.move + 1, curr.keys);

                if (isKey(nr, nc)) next.addKey(maze[nr][nc] - 'a');
                if (isDoor(nr, nc) && !curr.isKeyAvailable(maze[nr][nc] - 'A')) continue;
                if (maze[nr][nc] == '1') return next.move;

                visited[next.keys][nr][nc] = true;
                queue.add(next);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] start = parseInput();
        int answer = escapeMaze(start);
        printOutput(answer);
    }
}
