package soobin;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MakingConstellation {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static double[][] stars;
    private static int[] parent;
    private static int[] height;
    private static double answer = 0.0;

    private static class Distance {
        int from, to;
        double distance;

        public Distance(int from, int to, double distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }

    private static double calculateDistance(double[] star1, double[] star2) {
        double xTerm = Math.pow(Math.abs(star1[0] - star2[0]), 2);
        double yTerm = Math.pow(Math.abs(star1[1] - star2[1]), 2);

        return Math.sqrt(xTerm + yTerm);
    }

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
        int n = Integer.parseInt(br.readLine());
        stars = new double[n][2];
        parent = new int[n];
        height = new int[n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i] = new double[] {Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())};
            parent[i] = -1;
        }

        PriorityQueue<Distance> edges = new PriorityQueue<>(Comparator.comparingDouble(o1 -> o1.distance));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                edges.add(new Distance(i , j, calculateDistance(stars[i], stars[j])));
            }
        }

        int connected = 0;
        while (connected < n - 1 && !edges.isEmpty()) {
            Distance edge = edges.poll();
            if (union(edge.from, edge.to)) {
                answer += edge.distance;
            }
        }

        bw.write(String.format("%.2f", answer));
        bw.flush();
    }
}
