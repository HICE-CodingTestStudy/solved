package soobin;

import java.io.*;
import java.util.StringTokenizer;

public class SameSet {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] parent;
    private static int[] height;

    private static int find(int x) {
        if (parent[x] == -1) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v) return false;

        if (height[u] < height[v]) {
            int tmp = u;
            u = v; v = tmp;
        }

        if (height[u] == height[v]) height[u]++;
        parent[v] = u;
        height[v] = -1;
        return true;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        height = new int[n + 1];

        for (int i = 0; i <= n; i++) parent[i] = -1;

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (operation == 0) {
                union(a, b);
            } else if (operation == 1) {
                String isInSameSet = find(a) == find(b) ? "YES" : "NO";
                bw.write(isInSameSet);
                bw.newLine();
            }
        }
        bw.flush();
    }
}
