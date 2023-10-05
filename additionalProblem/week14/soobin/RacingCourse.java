package week14.soobin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RacingCourse {
    private final int[][] directions = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    private final int STRAIGHT = 100, CORNER = 600, MAX = Integer.MAX_VALUE - 1000;

    private int[][][] dp;
    private int[][] board;
    private int N, M;

    private boolean isValid(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < M && board[i][j] == 0;
    }

    private void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        // 원점은 위에서 내려오는 것과 왼쪽에서 오는 것만 초기화
        for (int i = 0; i < 2; i++) {
            dp[0][0][i] = 0;
            queue.add(new int[] {0, 0, i});
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], dir = curr[2];

            for (int i = 0; i < 4; i++) {
                int nr = r + directions[i][0];
                int nc = c + directions[i][1];
                if (!isValid(nr, nc) || i == (3 - dir)) continue;

                int newValue = dp[r][c][dir] + (i == dir ? STRAIGHT : CORNER);
                if (newValue < dp[nr][nc][i]) {
                    dp[nr][nc][i] = newValue;
                    queue.add(new int[] {nr, nc, i});
                }
            }
        }
    }

    public int solution(int[][] board) {
        this.board = board;
        N = board.length;
        M = board[0].length;
        dp = new int[N][M][4];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                Arrays.fill(dp[i][j], MAX);

        bfs();

        int answer = dp[N - 1][M - 1][0];
        for (int i = 1; i < 4; i++)
            answer = Math.min(dp[N - 1][M - 1][i], answer);

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = new int[][] {
                {0,0,0,0,0,0,0,0},
                {1,0,1,1,1,1,1,0},
                {1,0,0,1,0,0,0,0},
                {1,1,0,0,0,1,1,1},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,1,1,1,0},
                {1,1,1,1,1,1,1,0},
                {1,1,1,1,1,1,1,0}
        };

//        int[][] board = new int[][] {
//                {0,0,0,0,0},
//                {0,1,1,1,0},
//                {0,0,1,0,0},
//                {1,0,0,0,1},
//                {0,1,1,0,0}
//        };
        RacingCourse racing = new RacingCourse();
        System.out.println(racing.solution(board));
    }
}
