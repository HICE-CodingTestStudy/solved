package soobin;

import java.util.*;

public class Delivery {
    private static List<int[]>[] graph;

    private static void addEdge(int[] edge) {
        int u = edge[0] - 1, v = edge[1] - 1;
        graph[u].add(new int[] {v, edge[2]});
        graph[v].add(new int[] {u, edge[2]});
    }

    public int solution(int N, int[][] roads, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o1 -> o1[1]));
        graph = new ArrayList[N];
        int[] dist = new int[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }
        dist[0] = 0;

        for (int[] road : roads) addEdge(road);
        pq.add(new int[] {0, 0});

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];

            for (int[] v : graph[u]) {
                if (dist[v[0]] <= dist[u] + v[1]) continue;

                dist[v[0]] = dist[u] + v[1];
                pq.add(new int[] {v[0], dist[v[0]]});
            }
        }

        int answer = 0;
        for (int distance : dist)
            if (distance <= K) answer++;

        return answer;
    }

    public static void main(String[] args) {
        int[][] road1 = {{1,2,1}, {2,3,3}, {5,2,2}, {1,4,2}, {5,3,1}, {5,4,2}};
        int[][] road2 = {{1,2,1}, {1,3,2}, {2,3,2}, {3,4,3}, {3,5,2}, {3,5,3}, {5,6,1}};

        Delivery delivery = new Delivery();
        System.out.println(delivery.solution(5, road1, 3));
        System.out.println(delivery.solution(6, road2, 4));
    }
}
