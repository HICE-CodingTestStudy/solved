package soobin;

import java.io.*;
import java.util.*;

public class Puzzle {
    private static class Status {
        int[][] puzzle;
        int zeroR, zeroC, count;

        Status(int zeroR, int zeroC, int count, int[][] puzzle) {
            this.zeroR = zeroR;
            this.zeroC = zeroC;
            this.count = count;
            copy(puzzle);
        }

        private void copy(int[][] source) {
            this.puzzle = new int[3][3];
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    this.puzzle[i][j] = source[i][j];
        }

        public void swap(int sr, int sc, int dr, int dc) {
            puzzle[sr][sc] = puzzle[dr][dc];
            puzzle[dr][dc] = 0;
        }

        public String puzzleToString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    sb.append(puzzle[i][j]);
            return sb.toString();
        }

        public boolean isAligned() {
            return puzzleToString().equals(aligned);
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static final String aligned = "123456780";

    private static int[][] puzzle;
    private static int sr, sc;

    private static void parseInput() {
        try {
            puzzle = new int[3][3];
            for (int i = 0; i < 3; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    puzzle[i][j] = Integer.parseInt(st.nextToken());
                    if (puzzle[i][j] == 0) {
                        sr = i; sc = j;
                    }
                }
            }
        } catch (IOException e) {}
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < 3 && c >= 0 && c < 3;
    }

    private static int swapNumbers() {
        Queue<Status> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Status startStatus = new Status(sr, sc, 0, puzzle);
        if (startStatus.isAligned()) return 0;

        visited.add(startStatus.puzzleToString());
        queue.add(startStatus);

        while (!queue.isEmpty()) {
            Status cur = queue.poll();
            if (cur.isAligned()) return cur.count;

            for (int[] move : moves) {
                int nr = cur.zeroR + move[0], nc = cur.zeroC + move[1];
                if (!isValid(nr, nc)) continue;

                Status nextStatus = new Status(nr, nc, cur.count + 1, cur.puzzle);
                nextStatus.swap(cur.zeroR, cur.zeroC, nr, nc);
                if (visited.contains(nextStatus.puzzleToString())) continue;

                visited.add(nextStatus.puzzleToString());
                queue.add(nextStatus);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = swapNumbers();
        printAnswer(answer);
    }
}
