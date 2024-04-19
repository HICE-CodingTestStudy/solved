import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GameDev {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] parents;
    private static int[] time, memo;

    public static void main(String[] args) throws IOException {
        parseInput();
        solution();
    }

    private static void parseInput() throws IOException {
        int N = Integer.parseInt(br.readLine());
        parents = new List[N];
        time = new int[N];
        memo = new int[N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            time[i] = Integer.parseInt(input[0]);
            parents[i] = new LinkedList<>();
            for (int j = 1; !input[j].equals("-1"); j++)
                parents[i].add(Integer.parseInt(input[j]) - 1);
        }
    }

    private static void solution() throws IOException {
        for (int i = 0; i < memo.length; i++)
            bw.write(dp(i) + "\n");
        bw.flush();
    }

    private static int dp(int n) {
        if (parents[n].isEmpty()) return time[n];
        if (memo[n] > 0) return memo[n];

        int maxParentTime = -1;
        for (int parent : parents[n])
            maxParentTime = Math.max(maxParentTime, dp(parent));

        return memo[n] = maxParentTime + time[n];
    }
}
