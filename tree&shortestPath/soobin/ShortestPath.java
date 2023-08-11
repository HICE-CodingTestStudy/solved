package soobin;

import java.io.*;
import java.util.*;

public class ShortestPath {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<int[]>[] graph;
    private static PriorityQueue<int[]> pq;

    private static void addEdge(int s, int d, int w) {
        graph[s - 1].add(new int[] {d - 1, w});
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        int[] dist = new int[V];
        pq = new PriorityQueue<>(Comparator.comparingInt(o1 -> o1[1]));
        graph = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            graph[i] = new ArrayList<>();
        }

        int start = Integer.parseInt(br.readLine()) - 1;
        pq.add(new int[] {start, 0});
        dist[start] = 0;

        while (E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            addEdge(s, d, w);
        }

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];

            for (int[] v : graph[u]) {
                if (dist[v[0]] <= dist[u] + v[1]) continue;

                dist[v[0]] = dist[u] + v[1];
                pq.add(new int[] {v[0], dist[v[0]]});
            }
        }

        for (int d : dist) {
            String distance = d != Integer.MAX_VALUE ? String.valueOf(d) : "INF";
            bw.write(distance);
            bw.newLine();
        }
        bw.flush();
    }
}
