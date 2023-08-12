package dp;

public class GoToSchool {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42898
    //등굣길
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i < n+1; i++) {
            dp[i][1]=1;
        }
        for (int i = 1; i < m+1; i++) {
            dp[1][i]=1;
        }
        for (int[] puddle : puddles) {
            if (puddle[0] == 1&&dp[puddle[0]][1]!=-1)
                for (int j = puddle[0]; j < n + 1; j++) {
                    dp[j][1] = -1;
                }
            else if (puddle[1] == 1 && dp[1][puddle[0]]!=-1)
                for (int j = puddle[0]; j < m + 1; j++) {
                    dp[1][j] = -1;
                }
            dp[puddle[1]][puddle[0]] = -1;
        }
        for (int i = 2; i < n+1; i++) {
            for (int j = 2; j < m+1; j++) {
                if(dp[i][j]==-1) continue;
                if(dp[i][j-1]==-1&&dp[i-1][j]==-1) dp[i][j]=-1;
                else if(dp[i-1][j]==-1) dp[i][j]=(dp[i][j-1])%1000000007;
                else if(dp[i][j-1]==-1) dp[i][j]=(dp[i-1][j])%1000000007;
                else dp[i][j]=(dp[i-1][j]%1000000007+dp[i][j-1]%1000000007)%1000000007;
            }
        }
        if(dp[n][m]==-1) return 0;
        return dp[n][m];

    }

    public static void main(String[] args) {
        GoToSchool g = new GoToSchool();
        System.out.println(g.solution(2,2,new int[][]{{2,1},{1,2}}));
    }
}
