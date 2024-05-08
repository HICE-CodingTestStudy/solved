import java.io.*;
import java.util.*;

public class City {
    private static class Road {
        int from, to, cost;

        Road(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Queue<Road> roads;
    private static int[] parent, height;
    private static long totalCosts;
    private static int N, connected;

    public static void main(String[] args) throws IOException {
        parseInput();
        long cost = connectCities();
        print(cost);
    }

    private static void parseInput() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        roads = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        parent = new int[N + 1];
        height = new int[N + 1];
        for (int i = 1; i <= N; i++)
            parent[i] = i;

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);
            roads.add(new Road(u, v, w));
            totalCosts += w;
        }
    }

    private static long connectCities() {
        long costs = 0;

        while (!roads.isEmpty() && connected < N - 1) {
            Road road = roads.poll();
            if (union(road.from, road.to)) {
                costs += road.cost;
                connected++;
            }
        }

        return costs;
    }

    private static boolean union(int x, int y) {
        int u = find(x), v = find(y);

        if (u == v) return false;

        if (height[v] > height[u]) {
            int tmp = v;
            v = u;
            u = tmp;
        }

        parent[v] = u;
        height[u] = height[v] + 1;
        return true;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void print(long cost) throws IOException {
        long answer = connected == N - 1 ? totalCosts - cost : -1;
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
