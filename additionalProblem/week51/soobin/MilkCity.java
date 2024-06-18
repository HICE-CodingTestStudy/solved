import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MilkCity {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][][] memo;
    private static int[][] city;
    private static int N;

    public static void main(String[] args) throws Exception {
        parseInput();
        int answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        memo = new int[N + 1][N + 1][2];
        city = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 1; j <= N; j++)
                city[i][j] = Integer.parseInt(input[j - 1]);
        }
    }

    private static int solution() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int left = memo[i][j - 1][1], up = memo[i - 1][j][1];
                int milkType = left < up ? memo[i - 1][j][0] : memo[i][j - 1][0];
                int numMilk = Math.max(left, up);

                if (city[i][j] == milkType) {
                    milkType = (milkType + 1) % 3;
                    numMilk++;
                }
                memo[i][j] = new int[] {milkType, numMilk};
            }
        }

        return memo[N][N][1];
    }

    private static void printAnswer(int answer) throws Exception {
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
