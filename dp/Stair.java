import java.io.*;

public class Stair {
    private static int[] scores = new int[301];
    private static int[][] memo = new int[301][2];

    private static int step(int n, int stepped) {
        if (n <= 0) {
            return memo[0][stepped] = stepped == 0 ? 0 : scores[0];
        }

        if (memo[n][stepped] > 0) return memo[n][stepped];

        memo[n][stepped] = stepped == 1
                ? Math.max(step(n-2,0) + scores[n-1] + scores[n], step(n-2, 1) + scores[n])
                : step(n-1, 1);
        return memo[n][stepped];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) scores[i] = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(step(N-1, 1)));
        bw.flush();
    }
}
