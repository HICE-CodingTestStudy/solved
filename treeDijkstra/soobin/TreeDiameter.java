package soobin;

import java.io.*;
import java.util.*;

public class TreeDiameter {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static PriorityQueue<int[]> weights;
    private static List<int[]>[] graph;

    private static int maxLength = 0;
    private static int maxIdx = 0;

    private static void addEdge(int u, int v, int weight) {
        int[] parent = new int[] {u, weight};
        int[] child = new int[] {v, weight};
        graph[u].add(child);
        graph[v].add(parent);
        weights.add(child);
    }

    private static void sum(Set<Integer> visited, int v, int weight) {
        if (graph[v].size() == 1) {
            if (weight > maxLength) {
                maxLength = weight;
                maxIdx = v;
            }
//            maxLength = Math.max(weight, maxLength);
        }
        visited.add(v);

        for (int[] adj : graph[v])
            if (!visited.contains(adj[0])) sum(visited, adj[0], weight + adj[1]);
    }

    public static void main(String[] args) throws IOException {
        weights = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];

        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 1; i <= N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            addEdge(u, v, weight);
        }

        Set<Integer> visited = new HashSet<>();
        sum(visited, 0, 0);

        visited.clear();
        sum(visited, maxIdx, 0);

        // 왜 틀린 방법인지 모르겠음
        // 대부분의 반례는 통과함
        // 아이디어도 비슷한 것으로 생각됨
//        while (!weights.isEmpty()) {
//            int[] n = weights.poll();
//공
//            if (graph[n[0]].size() > 1) continue;
//
//            sum(visited, n[0], 0);
//            break;
//        }

        bw.write(String.valueOf(maxLength));
        bw.flush();
    }
}
