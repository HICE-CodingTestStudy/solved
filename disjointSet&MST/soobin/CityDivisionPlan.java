package soobin;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CityDivisionPlan {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static PriorityQueue<int[]> edges;
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
        edges = new PriorityQueue<>(Comparator.comparingInt(o1 -> o1[2]));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        height = new int[N];

        for (int i = 0; i < N; i++) parent[i] = -1;

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int[] edge = new int[] {
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken())
            };
            edges.add(edge);
        }

        int count = 0, expense = 0, lastExpense = 0;
        while (!edges.isEmpty() || count < N - 1) {
            int[] edge = edges.poll();

            if (union(edge[0], edge[1])) {
                count++;
                lastExpense = edge[2];
                expense += edge[2];
            }
        }

        expense -= lastExpense;
        bw.write(String.valueOf(expense));
        bw.flush();
    }
}
