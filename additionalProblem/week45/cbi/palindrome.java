import java.util.*;

class Solution
{
    static int[][] dp;
    static char[] c;
    static int foo(int start, int end) {
        if(dp[start][end] != -1) return dp[start][end];
        if(end - start <= 1) {
            dp[start][end] = (c[start] == c[end]) ? end - start + 1 : 0;
            return dp[start][end];
        }
        if(c[start] != c[end]) dp[start][end] = 0;
        else {
            int next = foo(start + 1, end - 1);
            dp[start][end] = (next == 0) ? 0 : next + 2;
        }
        return dp[start][end];
    }
    public int solution(String s)
    {
        int answer = 1;
        c = s.toCharArray();
        dp = new int[c.length][c.length];
        for(int i = 0; i < c.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        for(int i = 0; i < c.length; i++) {
            for(int j = i + 1; j < c.length; j++) {
                foo(i, j);
                answer = Math.max(answer, dp[i][j]);
            }
        }
        // System.out.println("Hello Java");

        return answer;
    }
}