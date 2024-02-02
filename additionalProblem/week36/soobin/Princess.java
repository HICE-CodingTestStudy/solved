import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Princess {
    private static class Cell {
        int row, col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean isOutOfBound() {
            return row < 0 || row >= N || col < 0 || col >= M;
        }

        public boolean isPrincess() {
            return row == N - 1 && col == M - 1;
        }

        public Cell move(int direction) {
            return new Cell(row + moves[direction][0], col + moves[direction][1]);
        }
    }
    private static class Knight {
        Cell cell;
        int time;
        boolean hasSword;

        public Knight(Cell cell, int time, boolean hasSword) {
            this.cell = cell;
            this.time = time;
            this.hasSword = hasSword;
        }

        public boolean isTimeOver() {
            return time > timeLimit;
        }

        public boolean hasMetPrincess() {
            return cell.isPrincess();
        }

        public Cell move(int direction) {
            return cell.move(direction);
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int[][] map;
    private static int N, M, timeLimit;

    public static void main(String[] args) {
        parseInput();
        int time = findPrincess();
        printAnswer(time);
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            timeLimit = Integer.parseInt(input[2]);
            map = new int[N][M];

            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                for (int j = 0; j < M; j++) {
                    int type = Integer.parseInt(input[j]);
                    map[i][j] = type;
                }
            }
        } catch (IOException ignored) {}
    }

    private static int findPrincess() {
        Queue<Knight> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];
        queue.add(new Knight(new Cell(0, 0), 0, false));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Knight knight = queue.poll();

            if (knight.isTimeOver()) return -1;
            if (knight.hasMetPrincess()) return knight.time;

            for (int direction = 0; direction < 4; direction++) {
                Cell next = knight.move(direction);
                if (next.isOutOfBound() || visited[next.row][next.col][knight.hasSword ? 1 : 0]) continue;
                if (!knight.hasSword && map[next.row][next.col] == 1) continue;

                boolean hasSword = map[next.row][next.col] == 2 || knight.hasSword;
                Knight nextKnight = new Knight(next, knight.time + 1, hasSword);
                visited[next.row][next.col][hasSword ? 1 : 0] = true;
                queue.add(nextKnight);
            }
        }

        return -1;
    }

    private static void printAnswer(int time) {
        try {
            bw.write(time == -1 ? "Fail" : String.valueOf(time));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
