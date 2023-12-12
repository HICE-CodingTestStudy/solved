package soobin;

import java.util.*;

public class Covid {
    private final int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private char[][] room;

    private void init(String[] place) {
        room = new char[5][5];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                room[i][j] = place[i].charAt(j);
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }

    private int manhattan(int sr, int sc, int dr, int dc) {
        return Math.abs(sr - dr) + Math.abs(sc - dc);
    }

    private boolean isContinuable(int sr, int sc, int r, int c) {
        return !isValid(r, c) || room[r][c] == 'X' || manhattan(sr, sc, r, c) > 2;
    }

    private boolean isIsolated(int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        queue.add(new int[] {row, col});
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] move : moves) {
                int nr = cur[0] + move[0], nc = cur[1] + move[1];

                if (isContinuable(row, col, nr, nc) || visited[nr][nc]) continue;
                if (room[nr][nc] == 'P') return false;

                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }

        return true;
    }

    private boolean isAllIsolated() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (room[i][j] == 'P' && !isIsolated(i, j))
                    return false;
        return true;
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            init(places[i]);
            answer[i] = isAllIsolated() ? 1 : 0;
        }

        return answer;
    }
}
