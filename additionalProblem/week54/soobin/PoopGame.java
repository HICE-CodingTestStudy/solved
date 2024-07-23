import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PoopGame {
    private static class Choice {
        char type;
        int amount;

        Choice(char type, int amount) {
            this.type = type;
            this.amount = amount;
        }

        public int calc(int prev) {
            if (prev == 0) return 0;
            if (type == '+') return prev + amount;
            if (type == '-') return prev - amount;
            if (type == '*') return prev * amount;
            return prev / amount;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Choice[][] choices;
    private static int[][] memo;
    private static int N;

    public static void main(String[] args) throws IOException {
        parseInput();
        int answer = dp();
        System.out.println(answer == 0 ? "ddong game" : answer);
    }

    private static void parseInput() throws IOException {
        N = Integer.parseInt(br.readLine());
        memo = new int[N + 1][2];
        choices = new Choice[N + 1][2];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(memo[i], -1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                String s = st.nextToken();
                char type = s.charAt(0);
                int amount = s.charAt(1) - '0';
                choices[i][j] = new Choice(type, amount);
            }
        }
    }

    private static int dp() {
        memo[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            memo[i][0] = selectChoice(i, memo[i - 1][0]); // 전부터 지금까지 skip 없이 쭉
            memo[i][1] = Math.max(memo[i - 1][0], selectChoice(i, memo[i - 1][1])); // 지금 꺼 skip 혹은 이전에 skip 한 적 있음
        }
        return Math.max(memo[N][0], memo[N][1]);
    }

    private static int selectChoice(int n, int prev) {
        int left = choices[n][0].calc(prev);
        int right = choices[n][1].calc(prev);
        int max = Math.max(left, right);
        return Math.max(max, 0);
    }
}
