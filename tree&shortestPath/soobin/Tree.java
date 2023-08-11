package soobin;

import java.io.*;
import java.util.*;

public class Tree {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Set<Integer>[] tree;
    private static int[] parents;
    private static int root;
    private static int totalLeaf = 0;

    private static void addEdge(int parent, int child) {
        if (parent == -1) {
            root = child;
            return;
        }

        tree[parent].add(child);
        parents[child] = parent;
    }

    private static void dfs(boolean[] visited, int v) {
        if (tree[v].size() == 0) totalLeaf++;
        visited[v] = true;

        for (int child : tree[v]) {
            if (!visited[child]) dfs(visited, child);
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        parents = new int[N];
        tree = new HashSet[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) tree[i] = new HashSet<>();
        for (int i = 0; i < N; i++) addEdge(Integer.parseInt(st.nextToken()), i);

        int remove = Integer.parseInt(br.readLine());
        if (remove == root) {
            bw.write(String.valueOf(0));
            bw.flush();
            return;
        }

        int parentForRemove = parents[remove];
        tree[parentForRemove].remove(remove);

        boolean[] visited = new boolean[N];
        dfs(visited, root);

        bw.write(String.valueOf(totalLeaf));
        bw.flush();
    }
}
