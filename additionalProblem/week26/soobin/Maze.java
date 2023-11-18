package week26.soobin;

import java.io.*;
import java.util.Arrays;

public class Maze {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // ↓ → ↑ ← / L: ++ / R: --

    private static char[][] maze;
    private static char[] memo;
    private static int N, minR, minC, maxR, maxC;

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            maze = new char[2 * N + 1][2 * N + 1];
            memo = new char[N];

            String m = br.readLine();
            for (int i = 0; i < N; i++)
                memo[i] = m.charAt(i);
        } catch (IOException e) {}
    }

    private static void initialize() {
        for (int i = 0; i < 2 * N + 1; i++)
            Arrays.fill(maze[i], '#');
    }

    private static void setSpace(int row, int col) {
        minR = Math.min(minR, row);
        minC = Math.min(minC, col);
        maxR = Math.max(maxR, row);
        maxC = Math.max(maxC, col);
    }

    private static void drawMaze() {
        minR = minC = maxR = maxC = N;
        int row = N, col = N;
        maze[row][col] = '.';

        int dir = 0;
        for (char action : memo) {
            if (action == 'F') {
                row += moves[dir][0];
                col += moves[dir][1];
                setSpace(row, col);
                maze[row][col] = '.';
            }

            if (action == 'L') dir = dir == 3 ? 0 : dir + 1;
            if (action == 'R') dir = dir == 0 ? 3 : dir - 1;
        }
    }

    private static void printMaze() {
        try {
            for (int i = minR; i <= maxR; i++) {
                for (int j = minC; j <= maxC; j++)
                    bw.write(String.valueOf(maze[i][j]));
                bw.write("\n");
            }
            bw.flush();
        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        parseInput();
        initialize();
        drawMaze();
        printMaze();
    }
}
