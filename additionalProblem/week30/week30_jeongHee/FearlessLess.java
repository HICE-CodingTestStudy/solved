package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FearlessLess {
    //https://www.acmicpc.net/problem/16507
    //어두운건 무서워
    static int R, C, Q;
    static long[][] dp;
    static long[][] pic;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        dp = new long[R][C];
        pic = new long[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < C; j++) {
                pic[i][j] = Long.parseLong(st.nextToken());
            }
        }
        dp[0][0]=pic[0][0];
        for (int i = 1; i < R; i++) {
            dp[i][0] = dp[i-1][0]+pic[i][0];
        }
        for (int i = 1; i < C; i++) {
            dp[0][i] = dp[0][i-1]+pic[0][i];
        }
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]-dp[i-1][j-1] +pic[i][j];
            }
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken())-1;
            long sum = dp[c][d];
            if(b!=0)
                sum-=dp[c][b-1];
            if(a!=0)
                sum-=dp[a-1][d];
            if(a!=0&&b!=0)
                sum+=dp[a-1][b-1];
            System.out.println(sum / ((long) (c - a + 1) * (long) (d - b + 1)));
        }
    }
}