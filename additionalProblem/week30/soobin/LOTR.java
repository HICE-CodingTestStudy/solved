package week30.soobin;

import java.io.*;
import java.util.Arrays;

public class LOTR {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static String pattern, devil, angel;
    private static long[][][] memo;
    private static int N, P;

    private static void parseInput() {
        try {
            pattern = br.readLine();
            devil = br.readLine();
            angel = br.readLine();

            N = devil.length();
            P = pattern.length();
            memo = new long[N][P][2]; // 돌다리의 몇 번째 칸인지, 패턴의 몇 번째 글자인지, 악마인지 천사인지
        } catch (IOException e) {}
    }

    private static void printOutput(long answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void init() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < P; j++)
                Arrays.fill(memo[i][j], -1);
    }

    private static long dp(int n, int p, int type) {
        if (n == 0 && p > 0 || n < 0) return 0; // 건너기 실패

        char character = type == 0 ? devil.charAt(n) : angel.charAt(n);
        if (p == 0) {
            memo[n][p][type] = dp(n - 1, p, type); // 예제 3처럼 지금 n에서 성공했는데 그 전의 돌다리에서도 성공할 수 있음
            if (character == pattern.charAt(p)) return memo[n][p][type] += 1; // 건너기 성공
        }

        if (memo[n][p][type] > -1) return memo[n][p][type];

        memo[n][p][type] = dp(n - 1, p, type); // 지금 다리 안 밟고 이전 다리 밟기
        if (character == pattern.charAt(p))
            memo[n][p][type] += dp(n - 1, p - 1, type == 0 ? 1 : 0); // 지금 다리 밟고 다른 돌다리로 건너가기
        return memo[n][p][type];
    }

    public static void main(String[] args) {
        parseInput();
        init();
        long answer = dp(N - 1, P - 1, 0) + dp(N - 1, P - 1, 1); // 악마에서 시작 + 천사에서 시작
        printOutput(answer);
    }
}
