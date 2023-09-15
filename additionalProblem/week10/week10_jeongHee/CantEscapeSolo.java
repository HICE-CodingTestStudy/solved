package queue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CantEscapeSolo {
    //https://www.acmicpc.net/problem/14621
    //나만 안되는 연애
    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int value;

        public Edge(int a, int b, int value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }

    public static int find(int a, int[] graph) {
        if (graph[a] == -1) return a;
        return graph[a] = find(graph[a], graph);
    }

    public static boolean union(int a, int b, int[] graph, int[] height) {
        int rootA = find(a, graph);
        int rootB = find(b, graph);
        if (rootB == rootA) return false;
        if (rootA >= rootB) {
            int tmp = rootA;
            rootA = rootB;
            rootB = tmp;
        }
        if (height[rootA] == height[rootB]) {
            graph[rootA] = rootB;
            height[rootA] = -1;
            height[rootB]++;
            return true;
        }
        graph[rootA] = rootB;
        height[rootA] = -1;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        HashMap<Integer, Character> hm = new HashMap<>();
        for (int i = 1; i < N + 1; i++) {
            hm.put(i, sc.next().charAt(0));
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            pq.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        int[] graph = new int[N + 1];
        int[] height = new int[N + 1];
        Arrays.fill(graph, -1);
        int ans = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (hm.get(e.a) == hm.get(e.b)) continue;
            if (union(e.a, e.b, graph, height))
                ans += e.value;
        }
        for (int i = 1; i < N; i++) {
            if(find(i,graph)!=find(i+1,graph)){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(ans);
    }
}
