package algoStudy.week1;

import java.util.*;

public class Party {
    //https://www.acmicpc.net/problem/1238
    //파티
    static int X;
    static int N;

    public static class Graph {
        int dest;
        int edge;

        public Graph(int dest, int edge) {
            this.dest = dest;
            this.edge = edge;
        }
    }

    public static int dijkstra(int source, int dest, ArrayList<ArrayList<Graph>> graph) {
        PriorityQueue<Graph> pq = new PriorityQueue<>(
                Comparator.comparingInt(o1 -> o1.edge)
        );
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        pq.add(new Graph(source, 0));
        while (!pq.isEmpty()) {
            Graph u = pq.poll();
            // u에서 갈 수 있는 모든 길(노드) 탐색
            for (Graph v : graph.get(u.dest)) {
                // u에서 v로 갈 수 있는 비용 갱신
                if (dist[v.dest] <= dist[u.dest] + v.edge) continue;
                dist[v.dest] = dist[u.dest] + v.edge;
                // 갱신되었다면 기존 v에서 갈 수 있는 길들의 비용을 다시 갱신하기 위해 큐에 삽입
                pq.add(new Graph(v.dest, dist[v.dest]));
            }
        }
        return dist[dest];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int M = sc.nextInt();
        X = sc.nextInt();
        ArrayList<ArrayList<Graph>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            graph.get(sc.nextInt()).add(new Graph(sc.nextInt(), sc.nextInt()));
        }
        int ans = 0;
        for (int i = 1; i < N + 1; i++) {
            ans = Math.max(ans, dijkstra(i, X, graph) + dijkstra(X, i, graph));
        }
        System.out.println(ans);
    }
}
