package soobin;

import java.io.*;
import java.util.*;

public class Alphabet {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static char[][] board;
    private static boolean[][] visited;
    private static int max;

    private static boolean canMove(StringBuilder trace, int row, int col) {
        return row >= 0 && row < board.length
                && col >= 0 && col < board[0].length
                && trace.indexOf(String.valueOf(board[row][col])) == -1;
    }

    private static void dfs(StringBuilder trace, int row, int col) {
        trace.append(board[row][col]);
        visited[row][col] = true;

        if (canMove(trace, row-1, col) && !visited[row-1][col])
            dfs(trace, row-1, col);
        if (canMove(trace, row, col+1) && !visited[row][col+1])
            dfs(trace, row, col+1);
        if (canMove(trace, row+1, col) && !visited[row+1][col])
            dfs(trace, row+1, col);
        if (canMove(trace, row, col-1) && !visited[row][col-1])
            dfs(trace, row, col-1);

        if (trace.length() >= max) max = trace.length();
        trace.delete(trace.length() - 1, trace.length());
        visited[row][col] = false;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) board[i][j] = line.charAt(j);
        }

        StringBuilder trace = new StringBuilder();
        max = 0;
        dfs(trace, 0, 0);
        bw.write(String.valueOf(max));
        bw.flush();
    }
}
