import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][2]; //0 : 건너 뛴적 X, 1 : 건너 뛴적 O, 2 : 현재껄 건너뛴 경우 최대
        //이전에 이미 불가능한지 체크해야 한다.
        //dp[i][0] : 모든 것을 선택한 경우를 사용해서 다음 경우를 업데이트해야하는 경우
        //현재 값을 계산했을 떄 0이상이되면 가능하다고 보게 되는데 이러면 안됨
        //즉, dp[i][0]이 이미 0보다 작은 경우 이 뒤로는 이 값을 사용해서 갱신하면 안됨
        // dp[i][1]도 마찬가지
        // 이미 이전에 최대로 갱신해서 만든 값이 0보다 작으면
        //해당 값을 통해 갱신되는 다음 값들은 해당 값을 사용해서 갱신되면 안되므로 사용할 수 있는지를 체크해둔다.
        boolean[][] flags = new boolean[N + 1][2];
        Arrays.fill(flags[0], true);
        dp[0][0] = 1;
        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            Arrays.fill(flags[i], true);
            for (int j = 0; j < 2; j++) {
                String str = st.nextToken();
                char oper = str.charAt(0);
                int num = str.charAt(1) - '0';
                //이전 값에 현재 값 계산 만약 이전에 이미 0이하라면 계산 안함 (이미 죽었으므로)
                if (flags[i - 1][0]) {
                    dp[i][0] = Math.max(dp[i][0], calc(oper, num, dp[i - 1][0]));
                }
                //건너 뛴 적 있는 경우
                //앞이든 현재든 건너뛴 값에 사용된 값이 (바로 이전을 건너뛰었든, 그 이전에 건너뛰었든 건너뛴 적 있는 값 중 최대)
                //이 값이 이미 0이하인 경우 이를 이용해서 갱신하면 안되기 때문에 이미 죽었으면 0을 값으로 사용
                // 현재를 건너뛰는 경우
                int temp1 = flags[i - 1][0] ? dp[i - 1][0] : 0;
                //이전에 건너뛴 적 있는 경우에 현재 선택한 경우
                int temp2 = flags[i - 1][1] ? calc(oper, num, dp[i - 1][1]) : 0;
                dp[i][1] = Math.max(dp[i][1], Math.max(temp1, temp2));

            }
            //위의 로직에 따라 이전이든 이번이든 이미 죽은 경우는 false로 세팅
            if (dp[i][0] <= 0) flags[i][0] = false;
            if (dp[i][1] <= 0) flags[i][1] = false;
        }
        //2가지 경우 중 하나라도 가능한 경우가 나오면 살아남은 것
        for (int i = 0; i < 2; i++) {
            flag |= flags[N][i];
        }
        //둘 중 최대 값을 가지고 있는 값을 출력해준다.
        System.out.println(flag ? Math.max(dp[N][0], dp[N][1]) : "ddong game");
    }

    private static int calc(char oper, int num, int pre) {
        if (oper == '+') return pre + num;
        else if (oper == '-') return pre - num;
        else if (oper == '*') return pre * num;
        else return pre / num;
    }
}