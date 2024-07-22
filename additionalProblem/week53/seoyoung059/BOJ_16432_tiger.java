package Ing.Week53.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16432_tiger {


    static int solve(int n, int[][] dp, boolean[][] arr) {

        int first, second;
        int check=-1;
        for (int i = 1; i < n + 1; i++) {
            first = 0;
            second = 0;
            check = -1;
            dp[i-1][0] = -1;
            for (int j = 1; j < 10; j++) {
                if (dp[i - 1][j] >= dp[i-1][first]) {
                    second = first;
                    first = j;
                } else if (dp[i - 1][j] >= dp[i-1][second]) {
                    second = j;
                }
            }

            System.out.println(first+" "+second);

            for (int j = 1; j < 10; j++) {
                // 이번에 j를 선택할 수 있을 때
                if (arr[i][j]) {
                    if (j == first) {
                        if (dp[i][second]!=0) {
                            dp[i][j] = second;
                            check = j;
                        }
                    } else if (first!=0) {
                        check = j;
                        dp[i][j] = first;
                    }
                }
            }
            System.out.println(Arrays.toString(dp[i]));
            if (check==-1) return -1;
        }
        return check;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        boolean[][] arr = new boolean[n + 1][10];
        int m;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                arr[i][Integer.parseInt(st.nextToken())] = true;
            }
        }

        int[][] dp = new int[n + 1][10];
        int tmp = solve(n, dp, arr);
        if(tmp != -1){
            ArrayDeque<Integer> ans = new ArrayDeque<>();
            for (int i = n; i > 0 ; i--) {
                ans.offerLast(tmp);
                tmp = dp[i][tmp];
            }
            StringBuilder sb = new StringBuilder();
            while(!ans.isEmpty()){
                sb.append(ans.pollLast()).append("\n");
            }
            System.out.print(sb);
        } else{
            System.out.println(-1);
        }
    }
}
