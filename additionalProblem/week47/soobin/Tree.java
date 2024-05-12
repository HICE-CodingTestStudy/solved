import java.io.*;

public class Tree {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static boolean[] visited;
    private static int[] parent, height;
    private static int N;

    public static void main(String[] args) throws IOException {
        int testCase = 1;

        while (init()) {
            int tree = countTree();
            String type = convert(tree);
            bw.write("Case " + (testCase++) + ": " + type + "\n");
        }

        bw.flush();
    }

    private static boolean init() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        if (N == 0) return false;

        visited = new boolean[N + 1];
        parent = new int[N + 1];
        height = new int[N + 1];
        for (int i = 1; i <= N; i++)
            parent[i] = i;

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            union(u, v);
        }

        return true;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }

    private static void union(int x, int y) {
        int u = find(x);
        int v = find(y);

        if (u == v || visited[u] || visited[v]) {
            visited[u] = true;
            visited[v] = true;
            return;
        }

        if (height[v] > height[u]) {
            int tmp = v;
            v = u;
            u = tmp;
        }

        parent[v] = u;
        height[u] = height[v] + 1;
    }

    private static int countTree() {
        int tree = 0;

        for (int i = 1; i <= N; i++) {
            int parent = find(i);
            if (visited[parent]) continue;
            visited[parent] = true;
            tree++;
        }

        return tree;
    }

    private static String convert(int tree) {
        switch(tree) {
            case 0:
                return "No trees.";
            case 1:
                return "There is one tree.";
            default:
                return "A forest of " + tree + " trees.";
        }
    }
}
