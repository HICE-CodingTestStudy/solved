package week10.soobin;

import java.io.*;
import java.util.*;

public class Dating {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] parent;
    private static int[] height;
    private static char[] type;

    private static int find(int x) {
        if (parent[x] == -1) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v) return false;

        if (height[u] < height[v]) {
            int tmp = v;
            v = u;
            u = tmp;
        }

        if (height[u] == height[v]) height[u]++;
        parent[v] = u;
        height[v] = -1;
        return true;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        height = new int[N];
        type = new char[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
            type[i] = st.nextToken().charAt(0);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            pq.add(new int[] {u, v, w});
        }

        Set<Integer> connected = new HashSet<>();
        int dist = 0;
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int u = edge[0], v = edge[1];
            if (type[u] == type[v] || !union(u, v)) continue;

            dist += edge[2];
            connected.add(u);
            connected.add(v);
        }

        bw.write(String.valueOf(connected.size() == N ? dist : -1));
        bw.flush();
    }
}
