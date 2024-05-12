import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Block {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MOD = 10007;

    private static List<Integer>[] blocks;
    private static int[][][] memo;
    private static int N, M, H;

    public static void main(String[] args) throws IOException {
        parseInput();
        int answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);
        memo = new int[N][M + 1][H + 1];
        blocks = new List[N];

        for (int i = 0; i < N; i++) {
            blocks[i] = new ArrayList<>();
            blocks[i].add(0);

            input = br.readLine().split(" ");
            for (String s : input) blocks[i].add(Integer.parseInt(s));
        }
    }

    private static int solution() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j <= M; j++)
                Arrays.fill(memo[i][j], -1);

        int answer = 0;
        for (int i = 0; i < blocks[N - 1].size(); i++)
            answer += dp(N - 1, i, H) % MOD;
        return answer % MOD;
    }

    private static int dp(int n, int m, int h) {
        if (h < 0) return 0;
        if (n == 0) return blocks[n].get(m) == h ? 1 : 0;

        if (memo[n][m][h] > -1) return memo[n][m][h] % MOD;

        memo[n][m][h] = 0;
        for (int i = 0; i < blocks[n - 1].size(); i++)
            memo[n][m][h] += dp(n - 1, i, h - blocks[n].get(m)) % MOD;
        return memo[n][m][h] % MOD;
    }

    private static void printAnswer(int answer) throws IOException {
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
