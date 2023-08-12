package soobin;

import java.io.*;
import java.util.*;

public class Lie {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static Set<Integer>[] graph;
    private static List<Integer> whoKnowsTruth = new ArrayList<>();

    private static int[] parent;
    private static int[] height;

    private static void dfs(int x, Set<Integer> visited) {
        visited.add(x);
        union(whoKnowsTruth.get(0), x);

        for (int n : graph[x]) {
            if (!visited.contains(n)) dfs(n, visited);
        }
    }

    private static void addEdge(int u, int v) {
        if (u == v) return;
        graph[u].add(v);
        graph[v].add(u);
    }

    private static int find(int x) {
        if (parent[x] == -1) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int u, int v) {
        u = find(u); v = find(v);

        if (u == v) return;

        if (height[u] < height[v]) {
            int tmp = u;
            u = v; v = tmp;
        }

        if (height[u] == height[v]) height[u]++;
        parent[v] = u;
        height[v] = -1;
    }

    private static void setWhoKnowsTruth(StringTokenizer st) {
        int num = Integer.parseInt(st.nextToken());

        if (num == 0) return;

        int seedPerson = Integer.parseInt(st.nextToken()) - 1;
        whoKnowsTruth.add(seedPerson);
        while (st.hasMoreTokens()) {
            int n = Integer.parseInt(st.nextToken()) - 1;
            whoKnowsTruth.add(n);
            union(seedPerson, n);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N =  Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer>[] parties = new ArrayList[M];
        graph = new Set[N];
        parent = new int[N];
        height = new int[N];

        for (int i = 0; i < M; i++) parties[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph[i] = new HashSet<>();
            parent[i] = -1;
        }

        setWhoKnowsTruth(new StringTokenizer(br.readLine()));
        if (whoKnowsTruth.size() == 0) {
            bw.write(String.valueOf(M));
            bw.flush();
            return;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            while (st.hasMoreTokens()) {
                int person = Integer.parseInt(st.nextToken()) - 1;
                parties[i].add(person);
            }

            for (int j : parties[i]) {
                for (int k : parties[i])
                    addEdge(j, k);
            }
        }

        for (int t : whoKnowsTruth) {
            for (int neighbor : graph[t]) {
                dfs(neighbor, new HashSet<>());
            }
        }

        int answer = 0;
        for (int i = 0; i < M; i++) {
            boolean canLie = true;

            for (int p : parties[i])
                if (find(p) == find(whoKnowsTruth.get(0))) {
                    canLie = false;
                    break;
                }

            if (canLie) answer++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
