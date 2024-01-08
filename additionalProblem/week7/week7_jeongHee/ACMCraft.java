package queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ACMCraft {
    //https://www.acmicpc.net/problem/1005
    //ACM Craft
    public static long solution(ArrayList<ArrayList<Integer>> graph, int target, int[] cost, long[] dp) {
        long tmpAns = 0;
        if (graph.get(target).isEmpty()) {
            dp[target] = cost[target];
            return cost[target];
        }
        for (int i = 0; i < graph.get(target).size(); i++) {
            if (dp[graph.get(target).get(i)] != -1) {
                tmpAns = Math.max(tmpAns, dp[graph.get(target).get(i)]);
                continue;
            }
            tmpAns = Math.max(tmpAns, solution(graph, graph.get(target).get(i), cost, dp));
        }
        dp[target] = tmpAns + cost[target];
        return tmpAns + cost[target];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[] cost = new int[N + 1];
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            graph.add(new ArrayList<>());
            for (int j = 0; j < N; j++) {
                graph.add(new ArrayList<>());
                cost[j + 1] = sc.nextInt();
            }
            for (int j = 0; j < K; j++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                graph.get(b).add(a);
            }
            long[] dp = new long[N + 1];
            Arrays.fill(dp, -1);
            System.out.println(solution(graph, sc.nextInt(), cost, dp));

        }
    }
}
