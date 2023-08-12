package soobin;

import java.io.*;
import java.util.*;

public class WateringRiceField {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static PriorityQueue<int[]> wateringFee;

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
        int N = Integer.parseInt(br.readLine());
        wateringFee = new PriorityQueue<>(Comparator.comparingInt(o1 -> o1[2]));
        parent = new int[N + 1]; height = new int[N + 1];

        parent[N] = -1;
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
            wateringFee.add(new int[]{N, i, Integer.parseInt(br.readLine())});
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int fee = Integer.parseInt(st.nextToken());
                if (i != j) wateringFee.add(new int[] {i, j, fee});
            }
        }

        int totalExpense = 0, watered = 0;
        while (!wateringFee.isEmpty() && watered < N) {
            int[] fee = wateringFee.poll();

            if (union(fee[0], fee[1])) totalExpense += fee[2];
        }

        bw.write(String.valueOf(totalExpense));
        bw.flush();
    }
}
