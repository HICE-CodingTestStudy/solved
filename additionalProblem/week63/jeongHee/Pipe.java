import java.util.*;
import java.io.*;

public class Main {
    static int N, X;
    static int[][] info;

    static int solution() {
        int[][] dp = new int[N][X + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i != 0) dp[i][j] = dp[i - 1][j];
                for (int k = 1; k <= info[i][1]; k++) {
                    int length = k * info[i][0];
                    if (length <= j) {
                        dp[i][j] += (j == length ? 1 : (i == 0 ? 0 : dp[i - 1][j - length]));
                    }
                }
            }
        }
        return dp[N - 1][X];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        info = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            info[i][0] = a;
            info[i][1] = b;
        }
        System.out.println(solution());
    }
}