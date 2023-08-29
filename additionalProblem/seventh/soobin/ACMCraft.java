package seventh.soobin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ACMCraft {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] cost;
    private static int[] memo;
    private static List<Integer>[] order;

    private static void addOrder(int x, int y) {
        order[y].add(x);
    }

    private static int dfs(int n) {
        if (memo[n] > -1) return memo[n];

        int costs = Integer.MIN_VALUE;
        for (int preBuilding : order[n]) {
            costs = Math.max(dfs(preBuilding) + cost[n], costs);
        }

        return memo[n] = Math.max(costs, cost[n]);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            memo = new int[N + 1];
            cost = new int[N + 1];
            order = new List[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                memo[i] = -1;
                cost[i] = Integer.parseInt(st.nextToken());
                order[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                addOrder(X, Y);
            }

            int W = Integer.parseInt(br.readLine());
            bw.write(String.format("%d\n", dfs(W)));
        }

        bw.flush();
    }
}
