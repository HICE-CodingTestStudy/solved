package week25.soobin;

import java.io.*;
import java.util.*;

public class RainDropOnTree {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] tree;
    private static int W;

    private static void addEdge(int u, int v) {
        tree[u].add(v);
        tree[v].add(u);
    }

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            tree = new List[V];
            for (int i = 0; i < V; i++) tree[i] = new ArrayList<>();

            for (int i = 0; i < V - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                addEdge(u, v);
            }
        } catch (IOException e) {}
    }

    private static void printAnswer(double answer) {
        try {
            bw.write(String.format("%.6f", answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int countLeaf() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[tree.length];
        queue.add(0);
        visited[0] = true;

        int leaf = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();

            boolean isLeaf = true;
            for (int child : tree[node]) {
                if (visited[child]) continue;

                isLeaf = false;
                queue.add(child);
                visited[child] = true;
            }

            if (isLeaf) leaf++;
        }

        return leaf;
    }

    public static void main(String[] args) {
        parseInput();
        int leaf = countLeaf();
        printAnswer((double) W / (double) leaf);
    }
}
