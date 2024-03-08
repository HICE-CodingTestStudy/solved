import java.io.*;

public class Train {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] carriage, passengers;
    private static int[][] memo;
    private static int N, nCarriage;

    public static void main(String[] args) {
        parseInput();
        countNumPassengers();
        int answer = dp(2, N - nCarriage);
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            carriage = new int[N];
            passengers = new int[N];
            memo = new int[3][N];

            String[] input = br.readLine().split(" ");
            for (int i = 0; i < N; i++)
                carriage[i] = Integer.parseInt(input[i]);

            nCarriage = Integer.parseInt(br.readLine());
        } catch (IOException ignored) {}
    }

    private static void countNumPassengers() {
        for (int i = 0; i < nCarriage; i++)
            passengers[0] += carriage[i];

        for (int i = 1; i < N - nCarriage + 1; i++)
            passengers[i] = passengers[i - 1] - carriage[i - 1] + carriage[i + nCarriage - 1];
    }

    private static int dp(int t, int n) {
        if (n < 0) return -1;
        if (memo[t][n] > 0) return memo[t][n];

        int prevCarriage = dp(t, n - 1);
        int currentCarriage = t == 0 ? passengers[n] : dp(t - 1, n - nCarriage) + passengers[n];

        return memo[t][n] = Math.max(prevCarriage, currentCarriage);
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
