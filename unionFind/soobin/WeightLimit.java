package soobin;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class WeightLimit {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static PriorityQueue<int[]> bridges;
    private static int[] parent;
    private static int[] height;

    private static void swap(int u, int v) {
        int tmp = u;
        u = v; v = tmp;
    }

    private static int find(int x) {
        if (parent[x] == -1) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int u, int v) {
        u = find(u); v = find(v);

        if (u == v) return false;

        if (height[u] < height[v]) swap(u, v);

        if (height[u] == height[v]) height[u]++;
        parent[v] = u;
        height[v] = -1;
        return true;
    }

    public static void main(String[] args) throws IOException {
        bridges = new PriorityQueue<>((o1, o2) -> o2[2] - o1[2]);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N]; height = new int[N];

        for (int i = 0; i < N; i++) parent[i] = -1;

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int[] bridge = new int[] {
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken())
            };
            bridges.add(bridge);
        }

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken()) - 1;
        int to = Integer.parseInt(st.nextToken()) - 1;

        int minWeight = 0;
        while (find(from) != find(to)) {
            int[] bridge = bridges.poll();

            if (union(bridge[0], bridge[1])) minWeight = bridge[2];
        }

        bw.write(String.valueOf(minWeight));
        bw.flush();
    }
}
