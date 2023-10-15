package week17.soobin;

import java.io.*;
import java.util.*;

public class UnidentifiedDestination {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<int[]>[] graph;
    private static List<Integer> candidates;
    private static int n, s, g, h, gToh;

    private static void addEdge(int u, int v, int w) {
        graph[u].add(new int[] {v, w});
        graph[v].add(new int[] {u, w});
    }

    private static void parseInput() {
        try {
            // 교차로, 도로, 목적지 후보 개수
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph = new List[n + 1];
            for (int i = 1; i <= n; i++)
                graph[i] = new ArrayList<>();

            // 출발지, 지나간 교차로
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            // 그래프에 도로 추가
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                addEdge(u, v, w);

                if ((u == g && v == h) || (u == h && v == g)) gToh = w;
            }

            // 목적지 후보
            candidates = new ArrayList<>();
            for (int i = 0; i < t; i++)
                candidates.add(Integer.parseInt(br.readLine()));
        } catch (IOException e) {}
    }

    private static void printOutput(List<Integer> possibleCandidates) {
        try {
            for (int candidate : possibleCandidates)
                bw.write(String.format("%d ", candidate));
            bw.write("\n");
        } catch (IOException e) {}
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new int[] {start, 0});
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int u = pq.peek()[0], d = pq.peek()[1];
            pq.poll();

            if (dist[u] < d) continue;

            for (int[] adj : graph[u]) {
                int v = adj[0], w = adj[1];
                if (dist[v] <= dist[u] + w) continue;

                dist[v] = dist[u] + w;
                pq.add(new int[] {v, dist[v]});
            }
        }

        return dist;
    }

    private static List<Integer> getPossibleCandidates(int[] dijkstraFromStart, int[] dijkstraFromMid, int mid) {
        List<Integer> possibleCandidates = new ArrayList<>();

        for (int candidate : candidates) {
            if (dijkstraFromMid[candidate] == Integer.MAX_VALUE) continue;

            if (dijkstraFromStart[mid] + gToh + dijkstraFromMid[candidate]
                    == dijkstraFromStart[candidate])
                possibleCandidates.add(candidate);
        }

        Collections.sort(possibleCandidates);
        return possibleCandidates;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            parseInput();
            int[] dijkstraFromStart = dijkstra(s);
            int[] mid = dijkstraFromStart[g] < dijkstraFromStart[h]
                    ? new int[] {g, h}
                    : new int[] {h, g};
            int[] dijkstraFromMid = dijkstra(mid[1]);
            List<Integer> possibleCandidates = getPossibleCandidates(dijkstraFromStart, dijkstraFromMid, mid[0]);
            printOutput(possibleCandidates);
        }
        bw.flush();
    }
}
