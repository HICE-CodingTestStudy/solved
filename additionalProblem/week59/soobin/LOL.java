import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LOL {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int MOD = 1000000007;

    private static int N, M;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(dp());
    }

    private static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private static int dp() {
        int[] memo = new int[N + 1];
        for (int i = 0; i < Math.min(M, N + 1); i++) {
            memo[i] = 1;
        }

        for (int i = M; i <= N; i++) {
            memo[i] = (memo[i - 1] + memo[i - M]) % MOD;
        }

        return memo[N] % MOD;
    }
}
