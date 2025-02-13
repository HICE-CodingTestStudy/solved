import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Pipe {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int[][] pipes, memo;
    private static int N, goal;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dp(0, 0));
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());
        pipes = new int[N][2];
        memo = new int[N][goal+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pipes[i][0] = Integer.parseInt(st.nextToken());
            pipes[i][1] = Integer.parseInt(st.nextToken());
            Arrays.fill(memo[i], -1);
        }
    }

    private static int dp(int idx, int length) {
        if (length == goal) return 1;
        if (length > goal || idx == N) return 0;

        if (memo[idx][length] > -1) return memo[idx][length];

        memo[idx][length] = 0;
        for (int c = 0; c <= pipes[idx][1]; c++) {
            memo[idx][length] += dp(idx+1, length + c * pipes[idx][0]);
        }
        return memo[idx][length];
    }
}