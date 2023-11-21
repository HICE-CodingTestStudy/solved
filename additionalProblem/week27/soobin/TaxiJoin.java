package week27.soobin;

import java.util.*;

public class TaxiJoin {
    private final int MAX = 1000000;
    private List<int[]>[] graph;
    private int[][] dist;

    private void addEdge(int u, int v, int w) {
        graph[u].add(new int[] {v, w});
        graph[v].add(new int[] {u, w});
    }

    private void initGraph(int n, int[][] fares) {
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] fare : fares) addEdge(fare[0] - 1, fare[1] - 1, fare[2]);
    }

    private void floyd(int n) {
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
            for (int[] edge : graph[i])
                dist[i][edge[0]] = edge[1];
        }

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
    }

    private int getMinFare(int n, int s, int a, int b) {
        int answer = dist[s][a] + dist[s][b];
        for (int i = 0; i < n; i++) {
            if (i == s) continue;
            int fare = dist[s][i] + dist[i][a] + dist[i][b];
            if (i == a || i == b) fare -= i == a ? dist[i][a] : dist[i][b];
            answer = Math.min(answer, fare);
        }

        return answer;
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        initGraph(n, fares);
        floyd(n);
        int answer = getMinFare(n, s - 1, a - 1, b - 1);
        return answer;
    }
}
