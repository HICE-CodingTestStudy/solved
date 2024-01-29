import java.io.*;
import java.util.*;

public class JumpingLog {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Queue<Integer> heights = new PriorityQueue<>();

    private static int N;

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            String[] nums = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                int height = Integer.parseInt(nums[i]);
                heights.add(height);
            }
        } catch (IOException ignored) {}
    }

    private static void print(int complexity) {
        try {
            bw.write(complexity + "\n");
        } catch (IOException ignored) {}
    }

    private static int[] makeLogArray() {
        int[] aligned = new int[N];
        aligned[0] = heights.poll();

        int right = 1, left = N - 1;
        while (!heights.isEmpty()) {
            aligned[right++] = heights.poll();
            if (!heights.isEmpty()) aligned[left--] = heights.poll();
        }

        return aligned;
    }

    private static int calcComplexity(int[] logs) {
        int maxDiff = Math.abs(logs[0] - logs[N - 1]);

        for (int i = 0; i < N - 1; i++)
            maxDiff = Math.max(maxDiff, Math.abs(logs[i] - logs[i + 1]));

        return maxDiff;
    }

    private static int solution() {
        int[] aligned = makeLogArray();
        return calcComplexity(aligned);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            parseInput();
            int complexity = solution();
            print(complexity);
        }
        bw.flush();
    }
}
