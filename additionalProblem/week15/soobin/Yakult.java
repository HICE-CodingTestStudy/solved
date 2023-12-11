package week15.soobin;

import java.io.*;
import java.util.*;

public class Yakult {
    private static class Point {
        int idx, weight;

        Point(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Point>[] graph;
    private static int[] salesPoints;
    private static int V, E, myStartingPoint;

    private static void addEdge(int u, int v, int w) {
        graph[u].add(new Point(v, w));
        graph[v].add(new Point(u, w));
    }

    private static void parseInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new List[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            addEdge(u, v, w);
        }

        salesPoints = new int[10];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++)
            salesPoints[i] = Integer.parseInt(st.nextToken()) - 1;

        myStartingPoint = Integer.parseInt(br.readLine()) - 1;
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        int[] costs = new int[V];
        Arrays.fill(costs, Integer.MAX_VALUE);
        pq.add(new Point(start, 0));
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Point u = pq.poll();

            if (costs[u.idx] < u.weight) continue;

            for (Point n : graph[u.idx]) {
                if (costs[n.idx] <= costs[u.idx] + n.weight) continue;

                costs[n.idx] = costs[u.idx] + n.weight;
                pq.add(new Point(n.idx, costs[n.idx]));
            }
        }

        return costs;
    }

    private static int followEachPoint(int[] myCosts) {
        int prev = salesPoints[0];
        int answer = myStartingPoint == prev ? prev : Integer.MAX_VALUE;
        long prevCost = 0;

        for (int nextIdx = 1; nextIdx < 10; nextIdx++) {
            int[] costs = dijkstra(prev);

            while (nextIdx < 10 && costs[salesPoints[nextIdx]] == Integer.MAX_VALUE && nextIdx < 10) nextIdx++;

            if (nextIdx >= 10) break;
            int next = salesPoints[nextIdx];

            if (myCosts[next] <= costs[next] + prevCost)
                answer = Math.min(answer, next);
            prevCost += costs[next];
            prev = next;
        }

        return answer == Integer.MAX_VALUE ? -1 : answer + 1;
    }

    public static void main(String[] args) throws IOException {
        parseInput();
        int[] myCosts = dijkstra(myStartingPoint);
        int answer = followEachPoint(myCosts);
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
