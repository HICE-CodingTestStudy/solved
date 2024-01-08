package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SecretParty {
    //https://www.acmicpc.net/problem/13424
    //비밀 모임
    static int N, M;

    static class Node {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    static List<List<Node>> graph;
    static int[][] dp;

    public static void dijkstra(int source) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt(o1 -> o1.weight)
        );
        Arrays.fill(dp[source], Integer.MAX_VALUE);
        dp[source][source] = 0;
        pq.add(new Node(source, 0));
        while (!pq.isEmpty()) {
            Node u = pq.poll();
            if (dp[source][u.index] < u.weight) continue;
            for (Node v : graph.get(u.index)) {
                if (dp[source][v.index] <= dp[source][u.index] + v.weight) continue;
                dp[source][v.index] = dp[source][u.index] + v.weight;
                pq.add(new Node(v.index, dp[source][v.index]));
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- > 0) {
            graph = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dp = new int[N + 1][N + 1];
            for (int i = 0; i < N + 1; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, c));
                graph.get(b).add(new Node(a, c));
            }
            int people = Integer.parseInt(bf.readLine());
            List<Integer> target = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < people; i++) {
                int next = Integer.parseInt(st.nextToken());
                target.add(next);
                dijkstra(next);
            }
            int[] ans = new int[N + 1];
            for (Integer integer : target) {
                for (int j = 1; j < N + 1; j++) {
                    ans[j] += dp[integer][j];
                }
            }
            int ret = 1;
            for (int i = 1; i < ans.length; i++) {
                if (ans[ret] > ans[i]) ret = i;
            }
            System.out.println(ret);
        }
    }
}
