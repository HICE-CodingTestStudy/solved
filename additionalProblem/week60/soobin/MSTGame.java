import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MSTGame {
    private static class Edge {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public boolean equals(Object other) {
            if (!(other instanceof Edge)) return false;
            return this.from == ((Edge) other).from && this.to == ((Edge) other).to;
        }

        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static Set<Edge> edges;
    private static int[] parent, height;
    private static int N, K;

    public static void main(String[] args) throws Exception {
        parseInput();
        solution();
    }

    private static void parseInput() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        edges = new HashSet<>();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, i));
        }
    }

    private static void solution() {
        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            init();
            int weight = makeMST();
            sb.append(weight).append(" ");
            if (weight == 0) break;
        }

        while (K-- > 0) sb.append("0 ");
        System.out.println(sb);
    }

    private static void init() {
        parent = new int[N + 1];
        height = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;
    }

    private static int makeMST() {
        Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        pq.addAll(edges);
        Edge minEdge = null;
        int weights = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (!union(edge.from, edge.to)) continue;

            weights += edge.weight;
            if (minEdge == null || minEdge.weight > edge.weight) minEdge = edge;
        }

        edges.remove(minEdge);
        return isValidMST() ? weights : 0;
    }

    private static boolean isValidMST() {
        int count = 0;
        for (int i = 1; i <= N; i++)
            if (parent[i] == i) count++;

        return count == 1;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return find(parent[x]);
    }

    private static boolean union(int x, int y) {
        int u = find(x);
        int v = find(y);

        if (u == v) return false;
        if (height[v] > height[u]) {
            int tmp = v;
            v = u;
            u = tmp;
        }

        parent[v] = u;
        height[u]++;
        return true;
    }
}
