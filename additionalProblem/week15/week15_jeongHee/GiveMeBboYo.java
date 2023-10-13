package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GiveMeBboYo {
    //https://www.acmicpc.net/problem/20160
    //야쿠르트 아줌마 야쿠르트 주세요
    static long[] myDist;
    static int start;
    static ArrayList<ArrayList<Node>> graph;
    static long[][] dp;

    static class Node {
        int index;
        long weight;

        public Node(int index, long weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    public static void dijkstra(int N, int source) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingLong(o1 -> o1.weight)
        );
        long[] dist = new long[N + 1];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Long.MAX_VALUE;
        }
        dist[source] = 0;
        pq.add(new Node(source, 0));
        while (!pq.isEmpty()) {
            Node u = pq.poll();
            for (Node v : graph.get(u.index)) {
                if (dist[v.index] <= dist[u.index] + v.weight) continue;
                dist[v.index] = dist[u.index] + v.weight;
                pq.add(new Node(v.index, dist[v.index]));
            }
        }
        for (int i = 0; i < dist.length; i++) {
            dp[i][source] = dist[i];
            dp[source][i] = dist[i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        myDist = new long[V + 1];
        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, w));
            graph.get(b).add(new Node(a, w));
        }
        st = new StringTokenizer(bf.readLine());
        ArrayList<Integer> destination = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            destination.add(Integer.valueOf(st.nextToken()));
        }
        start = Integer.parseInt(bf.readLine());
        long time = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < graph.get(start).size(); i++) {
            if (destination.get(0) == graph.get(start).get(i).index
                    && graph.get(start).get(i).weight == 0) {
                ans = Math.min(ans, destination.get(0));
                break;
            }
            if (destination.get(0) == start) {
                ans = Math.min(ans, start);
                break;
            }
        }
        dp = new long[V + 1][V + 1];
        for (int i = 0; i < V + 1; i++) {
            for (int j = 0; j < V + 1; j++) {
                if (i == j) dp[i][j] = 0;
                else dp[i][j] = -1;
            }
        }
        dijkstra(V, start);
        int bStart = destination.get(0);
        for (int i = 0; i < 9; i++) {
            int bDest = destination.get(i + 1);
            if (dp[bStart][bDest] == -1) dijkstra(V, bStart);
            if (dp[bStart][bDest] == Long.MAX_VALUE) continue;
            long bTime = dp[bStart][bDest];
            bStart = bDest;
            time += bTime;
            if (dp[start][bDest] <= time) ans = Math.min(ans, bDest);
        }
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
