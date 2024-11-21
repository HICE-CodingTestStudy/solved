import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/17271
    //리그 오브 레전설 small
    static final int MOD = 1_000_000_007;
    static int N, M;

    static int solution() {
        int[] dp = new int[N + 1];
        dp[0] = 1;
        for (int i = 1; i <= N ; i++) {
            int sum = dp[i - 1];
            if(i-M>=0) sum += dp[i - M];
            sum %= MOD;
            dp[i] = sum;
        }
        return dp[N];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        System.out.println(solution());
    }
}