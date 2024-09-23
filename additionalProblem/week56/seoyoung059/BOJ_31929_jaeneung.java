package fin.HICE.week56.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_31929_jaeneung {

    static int k;
    static int saveScore(int lastScore, int lossScore) {
        if (lastScore % k == 0) return lossScore;
        return Math.min(Math.floorMod(lastScore, k), lossScore);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] w = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] l = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            l[i] = Integer.parseInt(st.nextToken());
        }

        k = Integer.parseInt(br.readLine());


        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i < n+1; i++) {
            dp[i][0] = dp[i-1][0] + w[i-1];
        }
        for (int i = 1; i < m+1; i++) {
            dp[0][i] = dp[0][i-1] - saveScore(dp[0][i-1], l[i-1]);
        }

        for (int i = 1; i<n+1; i++) {
            for (int j = 1; j<m+1; j++) {
                dp[i][j] = Math.max(dp[i-1][j]+w[i-1], dp[i][j-1] - saveScore(dp[i][j-1], l[j-1]));
            }
        }

//        for (int i = 0; i < n + 1; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        System.out.println(dp[n][m]);

    }
}
