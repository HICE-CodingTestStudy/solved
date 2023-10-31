package week21.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class MagicianShark2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][][] spreadSand = {
            {{-2, 0, 2}, {-1, -1, 10}, {-1, 0, 7}, {-1, 1, 1}, {2, 0, 2}, {1, -1, 10}, {1, 0, 7}, {1, 1, 1}, {0, -2, 5}, {0, -1, 0}},
            {{0, -2, 2}, {1, -1, 10}, {0, -1, 7}, {-1, -1, 1}, {0, 2, 2}, {1, 1, 10}, {0, 1, 7}, {-1, 1, 1}, {2, 0, 5}, {1, 0, 0}},
            {{-2, 0, 2}, {-1, 1, 10}, {-1, 0, 7}, {-1, -1, 1}, {2, 0, 2}, {1, 1, 10}, {1, 0, 7}, {1, -1, 1}, {0, 2, 5}, {0, 1, 0}},
            {{0, 2, 2}, {-1, 1, 10}, {0, 1, 7}, {1, 1, 1}, {0, -2, 2}, {-1, -1, 10}, {0, -1, 7}, {1, -1, 1}, {-2, 0, 5}, {-1, 0, 0}}
    };
    private static final int[][] moves = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static int[][] map;
    private static int N, base, dumped;

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            base = N / 2;
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }
        } catch (IOException e) {}
    }

    private static void printOutput() {
        try {
            bw.write(String.valueOf(dumped));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int changeDirection(int prev) {
        if (prev + 1 < 4) return prev + 1;
        return 0;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static void spreadSand(int row, int col, int direction) {
        int total = 0;
        for (int[] spread : spreadSand[direction]) {
            int nr = row + spread[0];
            int nc = col + spread[1];
            double ratio = spread[2] / 100.0;

            int sand = ratio == 0 ? map[row][col] - total : (int) (map[row][col] * ratio);
            if (!isValid(nr, nc)) {
                dumped += sand;
                total += sand;
                continue;
            }

            map[nr][nc] += sand;
            total += sand;
        }

        map[row][col] = 0;
    }

    private static void moveTornado() {
        int curRow = base, curCol = base;
        int limit = 1, direction = 0;

        int temp = 0;
        while (curRow != 0 || curCol != 0) {
            int nextRow = curRow + moves[direction][0];
            int nextCol = curCol + moves[direction][1];
            temp++;

            spreadSand(nextRow, nextCol, direction);
            curRow = nextRow;
            curCol = nextCol;

            if (temp == limit) {
                direction = changeDirection(direction);
                temp = 0;
                if (direction == 0 || direction == 2) limit++;
            }
        }
    }

    public static void main(String[] args) {
        parseInput();
        moveTornado();
        printOutput();
    }
}
