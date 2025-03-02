import java.io.BufferedReader;
import java.io.InputStreamReader;

class Snail {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int num = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        int[] coordinate = new int[2];
        int row = 0, col = 0, moveIdx = 0;

        for (int i = N*N; i >= 1; i--) {
            board[row][col] = i;
            if (i == num) {
                coordinate = new int[] {row+1, col+1};
            }

            int nr = row + moves[moveIdx][0];
            int nc = col + moves[moveIdx][1];
            if (isInvalid(nr, nc, N) || board[nr][nc] > 0) {
                moveIdx = (moveIdx+1) % 4;
                nr = row + moves[moveIdx][0];
                nc = col + moves[moveIdx][1];
            }

            row = nr;
            col = nc;
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer.append(board[i][j]).append(" ");
            }
            answer.append("\n");
        }
        answer.append(coordinate[0]).append(" ").append(coordinate[1]);
        System.out.print(answer);
    }

    private static boolean isInvalid(int r, int c, int N) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}