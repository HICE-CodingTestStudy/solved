import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Snail {
    //https://www.acmicpc.net/problem/1913
    //달팽이
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int N, target;
    static int[] ans;
    static int[][] board;

    static class Info {
        int num, i, j, d, depth;

        public Info(int num, int i, int j, int d, int depth) {
            this.num = num;
            this.i = i;
            this.j = j;
            this.d = d;
            this.depth = depth;
        }
    }

    static void fill(Info info) {
        for (int i = 0; i < info.depth; i++) {
            info.i += dx[info.d];
            info.j += dy[info.d];
            board[info.i][info.j] = info.num++;
            if (board[info.i][info.j] == target) ans = new int[]{info.i + 1, info.j + 1};
        }
    }

    static void solution() {
        board[N / 2][N / 2] = 1;
        Info info = new Info(2, N / 2, N / 2, 0, 1);
        ans = new int[]{N / 2 + 1, N / 2 + 1};
        while (info.depth < N) {
            for (int i = 0; i < 2; i++) {
                fill(info);
                info.d = (info.d + 1) % 4;
            }
            if (info.depth == N - 1) fill(info);
            info.depth++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(ans[0]).append(" ").append(ans[1]);
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        target = Integer.parseInt(bf.readLine());
        board = new int[N][N];
        solution();
    }
}
