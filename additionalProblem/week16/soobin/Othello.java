package week16.soobin;

import java.io.*;
import java.util.*;

public class Othello {
    private static class GameLog {
        int r, c;
        char type;

        GameLog(int r, int c, char type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }

        public char opposite() {
            return this.type == 'B' ? 'W' : 'B';
        }
    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}};    // ↑, ↖, ←, ↙, ↗, →, ↘, ↓
    private static final char[][] board = new char[6][6];

    private static Queue<GameLog> gameLogs = new LinkedList<>();

    private static void parseInput() {
        try {
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                gameLogs.add(new GameLog(r, c, i % 2 == 0 ? 'B' : 'W'));
            }
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < 6 && c >= 0 && c < 6;
    }

    private static void initializeBoard() {
        for (int i = 0; i < 6; i++)
            Arrays.fill(board[i], '.');
        board[2][2] = board[3][3] = 'W';
        board[2][3] = board[3][2] = 'B';
    }

    private static void repeatGameLogs() {
        while (!gameLogs.isEmpty()) {
            GameLog next = gameLogs.poll();
            board[next.r][next.c] = next.type;

            for (int[] move : moves) {
                int nr = next.r + move[0], nc = next.c + move[1];
                List<int[]> toBeTurned = new ArrayList<>();

                while (isValid(nr, nc) && board[nr][nc] == next.opposite()) {
                    toBeTurned.add(new int[] {nr, nc});
                    nr += move[0];
                    nc += move[1];
                }

                if (!isValid(nr, nc) || board[nr][nc] != next.type) continue;

                for (int[] t : toBeTurned) board[t[0]][t[1]] = next.type;
            }
        }
    }

    private static void printGameResult() {
        int black = 0, white = 0;

        try {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] == 'B') black++;
                    else if (board[i][j] == 'W') white++;
                    bw.write(String.valueOf(board[i][j]));
                }
                bw.write("\n");
            }

            String result = black > white ? "Black" : "White";
            bw.write(result);
            bw.flush();
        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        parseInput();
        initializeBoard();
        repeatGameLogs();
        printGameResult();
    }
}
