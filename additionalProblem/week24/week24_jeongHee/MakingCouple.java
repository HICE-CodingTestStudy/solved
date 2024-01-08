package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MakingCouple {
    //https://www.acmicpc.net/problem/1727
    //커플 만들기
    static int n, m;
    static List<Integer> boys = new ArrayList<>();
    static List<Integer> girls = new ArrayList<>();
    static int[][] dp;

    static long solution() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == j) dp[i][j] = dp[i - 1][j - 1] + Math.abs(boys.get(i) - girls.get(j));
                else if (i > j)
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1] + Math.abs(boys.get(i) - girls.get(j)));
                else dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j - 1] + Math.abs(boys.get(i) - girls.get(j)));
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][m + 1];
        st = new StringTokenizer(bf.readLine());
        boys.add(0);
        girls.add(0);
        for (int i = 0; i < n; i++) {
            boys.add(Integer.valueOf(st.nextToken()));
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < m; i++) {
            girls.add(Integer.valueOf(st.nextToken()));
        }
        Collections.sort(boys);
        Collections.sort(girls);
        System.out.println(solution());
    }

}
