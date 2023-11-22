package stack;

import java.util.*;

public class Taxi {
    //https://school.programmers.co.kr/learn/courses/30/lessons/72413
    //합승 택시 요금
    static class Node {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    static List<List<Node>> graph = new ArrayList<>();
    static int[][] cost;

    static void dijkstra(int source) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(o1 -> o1.weight)
        );
        Arrays.fill(cost[source], Integer.MAX_VALUE);
        cost[source][source] = 0;
        pq.add(new Node(source, 0));
        while (!pq.isEmpty()) {
            Node u = pq.poll();
            if (cost[source][u.index] < u.weight) continue;
            for (Node v : graph.get(u.index)) {
                if (cost[source][v.index] <= cost[source][u.index] + v.weight) continue;
                cost[source][v.index] = cost[source][u.index] + v.weight;
                pq.add(new Node(v.index, cost[source][v.index]));
            }
        }
    }


    public int solution(int n, int s, int a, int b, int[][] fares) {
        cost = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < fares.length; i++) {
            graph.get(fares[i][0]).add(new Node(fares[i][1], fares[i][2]));
            graph.get(fares[i][1]).add(new Node(fares[i][0], fares[i][2]));
        }
        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }
        int ans = cost[s][a] + cost[s][b];
        for (int i = 1; i <= n; i++) {
            ans = Math.min(cost[s][i] + cost[i][b] + cost[i][a], ans);
        }
        return ans;

    }

    public static void main(String[] args) {
        Taxi t = new Taxi();
        int[][] fares = new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        t.solution(6, 4, 6, 2, fares);
    }
}
