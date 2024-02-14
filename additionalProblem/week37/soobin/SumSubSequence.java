import java.io.*;

public class SumSubSequence {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX = 20000002;
    private static final boolean[] sums = new boolean[MAX];

    private static int[] S;
    private static int N;

    public static void main(String[] args) throws IOException {
        parseInput();
        makeSubSequences(0, 0);
        int answer = findAnswer();
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");

            S = new int[N];
            for (int i = 0; i < N; i++)
                S[i] = Integer.parseInt(input[i]);
        } catch (IOException ignored) {}
    }

    private static void makeSubSequences(int sum, int idx) {
        if (idx == N) {
            sums[sum] = true;
            return;
        }

        makeSubSequences(sum + S[idx], idx + 1);
        makeSubSequences(sum, idx + 1);
    }

    private static int findAnswer() {
        for (int i = 1; i < MAX; i++)
            if (!sums[i]) return i;
        return -1;
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
