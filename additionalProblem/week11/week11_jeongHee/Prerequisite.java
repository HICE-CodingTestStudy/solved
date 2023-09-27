package additional;

import java.util.*;

public class Prerequisite {
    //https://www.youtube.com/watch?v=cdck7qqwRzY
    //선수과목
    public static int[] dp;

    public static int bfs(ArrayList<ArrayList<Integer>> graph, int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        boolean[] visited = new boolean[graph.size()];
        visited[startNode] = true;
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int next = queue.poll();
                if (dp[next] != -1) {
                    ans = Math.max(ans,dp[next]);
                    continue;
                }
                for (int j = 0; j < graph.get(next).size(); j++) {
                    int nextNode = graph.get(next).get(j);
                    if (visited[nextNode]) continue;
                    queue.add(nextNode);
                    visited[nextNode] = true;
                }
            }
        }
        return ans+1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(b).add(a);
        }
        int ans = 0;
        for (int i = 0; i < N+1; i++) {
            if (graph.get(i).size() == 0)
                dp[i] = 0;
        }
        for (int i = 0; i < N; i++) {
            int n = bfs(graph, i + 1);
            dp[i + 1] = n;
            System.out.print(n + " ");
        }
    }
}
