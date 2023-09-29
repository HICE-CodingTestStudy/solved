package week12.soobin;

import java.io.*;
import java.util.*;

public class Party {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<int[]>[] graph, reversedGraph;
    private static int N;

    private static int[] dijkstra(int start, List<int[]>[] graph) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];

            for (int[] edge : graph[u]) {
                if (dist[edge[0]] <= dist[u] + edge[1]) continue;

                dist[edge[0]] = dist[u] + edge[1];
                pq.add(new int[] {edge[0], dist[edge[0]]});
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;

        graph = new List[N];
        reversedGraph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            reversedGraph[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new int[] {v, w});
            reversedGraph[v].add(new int[] {u, w});
        }

        int[] dist = dijkstra(X, graph);
        int[] reversedDist = dijkstra(X, reversedGraph);

        int max = 0;
        for (int i = 0; i < N; i++) {
            if (i == X) continue;
            max = Math.max(max, dist[i] + reversedDist[i]);
        }

        bw.write(String.valueOf(max));
        bw.flush();
    }
}
