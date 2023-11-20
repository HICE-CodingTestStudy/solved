package week27.soobin;

import java.io.*;
import java.util.*;

public class PentaPort {
    private static class Location {
        int x, y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] graph;
    private static Location[] locations;
    private static int N;

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine()) + 2;
            locations = new Location[N];
            graph = new List[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                locations[i] = new Location(x, y);
                graph[i] = new ArrayList<>();
            }
        } catch (IOException e) {}
    }

    private static void printOutput(boolean isHappy) {
        try {
            bw.write(isHappy ? "happy\n" : "sad\n");
        } catch (IOException e) {}
    }

    private static void addEdge(int u, int v) {
        graph[u].add(v);
        graph[v].add(u);
    }

    private static boolean isConnectable(Location l1, Location l2) {
        return Math.abs(l1.x - l2.x) + Math.abs(l1.y - l2.y) <= 1000;
    }

    private static void initialize() {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                Location il = locations[i], jl = locations[j];
                if (isConnectable(il, jl)) addEdge(i, j);
            }
        }
    }

    private static boolean goToPentaPort() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        visited[0] = true;
        queue.add(0);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph[current]) {
                if (visited[next]) continue;
                if (next == N - 1) return true;

                visited[next] = true;
                queue.add(next);
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            parseInput();
            initialize();
            boolean isHappy = goToPentaPort();
            printOutput(isHappy);
        }
        bw.flush();
    }
}
