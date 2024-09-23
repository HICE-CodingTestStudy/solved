import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class YouGood {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][] memo;
    private static int[] win, lose;
    private static int N, M, K;

    public static void main(String[] args) throws Exception {
        parseInput();
        int answer = dp();
        System.out.println(answer);
    }

    private static void parseInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        win = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            win[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        lose = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            lose[i] = Integer.parseInt(st.nextToken());
        }

        K = Integer.parseInt(br.readLine());
        memo = new int[N + 1][M + 1];
    }

    private static int dp() {
        for (int n = 1; n <= N; n++) {
            memo[n][0] = memo[n - 1][0] + win[n];
        }
        for (int m = 1; m <= M; m++) {
            int point = getLosingPoint(memo[0][m - 1], m);
            memo[0][m] = memo[0][m - 1] - point;
        }

        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                int point = getLosingPoint(memo[n][m - 1], m);
                memo[n][m] = Math.max(memo[n - 1][m] + win[n], memo[n][m - 1] - point);
            }
        }

        return memo[N][M];
    }

    private static int getLosingPoint(int prev, int m) {
        int mod = (prev % K + K) % K;  // 자바 나머지 연산 알못 이슈,,,
        return mod == 0 ? lose[m] : Math.min(lose[m], mod);
    }
}
