import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Job {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] jobs;
    private static int[] timeCost, memo, numChildJobs;
    private static int N;

    public static void main(String[] args) {
        parseInput();
        int answer = solution();
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            jobs = new List[N];
            numChildJobs = new int[N];
            timeCost = new int[N];
            memo = new int[N];

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                timeCost[i] = Integer.parseInt(input[0]);
                int numPrev = Integer.parseInt(input[1]);
                jobs[i] = new ArrayList<>();

                for (int j = 0; j < numPrev; j++) {
                    int prev = Integer.parseInt(input[j + 2]) - 1;
                    numChildJobs[prev]++;
                    jobs[i].add(prev);
                }
            }
        } catch (IOException ignored) {}
    }

    private static int solution() {
        int answer = 0;

        for (int i = 0; i < N; i++)
            if (numChildJobs[i] == 0)
                answer = Math.max(answer, dp(i));

        return answer;
    }

    private static int dp(int job) {
        if (jobs[job].isEmpty()) return timeCost[job];

        if (memo[job] > 0) return memo[job];

        for (int prev : jobs[job])
            memo[job] = Math.max(dp(prev) + timeCost[job], memo[job]);
        return memo[job];
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
