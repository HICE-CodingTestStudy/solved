package week20.soobin;

import java.io.*;
import java.util.*;

public class FishingKing {
    static class Coordinate {
        int row, col;

        Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean equals(Object o) {
            return ((Coordinate) o).row == row && ((Coordinate) o).col == col;
        }

        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    static class Shark {
        Coordinate current;
        int dir, v, size;

        Shark(int dir, int v, int size, int startRow, int startCol) {
            this.dir = dir;
            if (dir < 2) this.v = v % ((R - 1) * 2);
            else this.v = v % ((C - 1) * 2);
            this.size = size;
            current = new Coordinate(startRow, startCol);
        }

        private boolean isValidRow(int r) {
            return r >= 0 && r < R;
        }

        private boolean isValidCol(int c) {
            return c >= 0 && c < C;
        }

        private void changeDirection() {
            if (dir == 0 || dir == 2) dir++;
            else dir--;
        }

        private int handleDecreaseDirection(int n, int D) {
            if (Math.abs(n) <= D - 1) {
                n = -n;
                changeDirection();
            } else n = 2 * (D - 1) + n;
            return n;
        }

        private int handleIncreaseDirection(int n, int D) {
            if (n <= 2 * (D - 1)) {
                n = 2 * (D - 1) - n;
                changeDirection();
            }
            else n = n - 2 * (D - 1);
            return n;
        }

        private int handleRowOOI(int nr) {
            if (dir == 0) return handleDecreaseDirection(nr, R);
            return handleIncreaseDirection(nr, R);
        }

        private int handleColOOI(int nc) {
            if (dir == 3) return handleDecreaseDirection(nc, C);
            return handleIncreaseDirection(nc, C);
        }

        public void move() {
            int nr = current.row + moves[dir][0] * v;
            int nc = current.col + moves[dir][1] * v;

            if (!isValidRow(nr)) nr = handleRowOOI(nr);
            if (!isValidCol(nc)) nc = handleColOOI(nc);

            current.row = nr;
            current.col = nc;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private static Shark[][] sharks;
    private static int R, C, M;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (M == 0) return;

            sharks = new Shark[R][C];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) - 1;
                int z = Integer.parseInt(st.nextToken());
                sharks[r][c] = new Shark(d, s, z, r, c);
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static Shark getClosestShark(int col) {
        for (int i = 0; i < R; i++) {
            if (sharks[i][col] == null) continue;
            Shark shark = sharks[i][col];
            sharks[i][col] = null;
            return shark;
        }
        return null;
    }

    private static void moveSharks() {
        Shark[][] movedSharks = new Shark[R][C];

        for (int i = 0; i < R; i++) {
             for (int j = 0; j < C; j++) {
                 if (sharks[i][j] == null) continue;
                 Shark shark = sharks[i][j];

                 // 다음 위치로 이동
                 shark.move();
                 Coordinate next = shark.current;
                 // 이미 그 자리에 다른 상어 있을 시
                 if (movedSharks[next.row][next.col] != null) {
                     Shark before = movedSharks[next.row][next.col];
                     shark = shark.size > before.size ? shark : before;
                 }
                 movedSharks[next.row][next.col] = shark;
             }
        }

        sharks = movedSharks;
    }

    private static int fishSharks() {
        if (M == 0) return 0;

        int total = 0;

        for (int i = 0; i < C; i++) {
            // 상어 잡기
            Shark shark = getClosestShark(i);
            if (shark != null) total += shark.size;
            // 상어 이동
            moveSharks();
        }
        return total;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = fishSharks();
        printOutput(answer);
    }
}
