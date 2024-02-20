import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/14267
    //회사 문화 1
    static int n, m;
    static int[] dp, up, good;

    static int solution(int p) {
        if (dp[p] != -1) return dp[p];
        dp[p] = 0;
        if (p == 1) return dp[p];
        return dp[p] += solution(up[p]) + good[p];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        up = new int[n + 1];
        good = new int[n + 1];
        for (int i = 0; i < n; i++) {
            up[i + 1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            good[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            solution(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n ; i++) {
            sb.append(dp[i]).append(" ");
        }
        System.out.println(sb);
    }
}