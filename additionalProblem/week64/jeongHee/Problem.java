import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem {
    // https://www.acmicpc.net/problem/2854
    // 문제 출제
    static int N;
    static final int MOD = 1_000_000_007;
    static long[] fir, sec;
//    static long[] dp;
    static long[][] dp;
//    static int ans = MOD;

    static long mod(long origin) {
        return origin % MOD;
    }

    static long solution() {
        // 0=그냥, 1= i-1에서, 2=i에서 -> 한b 구역에서 양쪽에 출제하느건 어차피 n개, n-1 개 중에 고르는 꼴임을 주의
        dp = new long[N][3];
        dp[0][0] = fir[0];
        dp[0][2] = sec[0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = mod(fir[i] * mod(dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]));
            dp[i][1] = mod(mod((dp[i - 1][0] + dp[i - 1][1]) * sec[i-1]) + (dp[i - 1][2] * (sec[i-1] - 1)));
            dp[i][2] = mod(mod(dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) * sec[i]);
        }
        return mod(dp[N - 1][0] + dp[N - 1][1] + dp[N - 1][2]);
    }


//    static long solution(int index) {
//        if (dp[index] != -1) return dp[index];
//        long next = solution(index + 1);
//        long sum = mod(fir[index] * next);
//        sum += mod(mod(sec[index] * Math.max(0, sec[index] - 1)) * mod((solution(index + 2) + sec[index + 1])));
//        sum += mod(mod(fir[index] * sec[index]) * mod(solution(index + 2) + sec[index + 1]));
//        sum += mod(next * sec[index]);
//        sum = mod(sum);
//        if (sum == 0 && fir[index] != 0) ans = 0;
//        return dp[index] = sum;
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        fir = new long[N];
        sec = new long[N + 2];
//        dp = new long[N + 2];
//        Arrays.fill(dp, -1);
//        dp[N] = dp[N + 1] = 1L;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            fir[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N - 1; i++) {
            sec[i] = Long.parseLong(st.nextToken());
        }
//        System.out.println(Math.min(solution(0), ans));
        System.out.println(solution());
    }
}
