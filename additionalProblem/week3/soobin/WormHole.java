package week3.soobin;

import java.io.*;
import java.util.*;

public class WormHole {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int INF = 1000000000;

    private static List<int[]> edges;

    private static boolean bellman(int source, int N) {
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                dist[v] = Math.min(dist[v], dist[u] + w);
            }
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (dist[v] > dist[u] + w)
                return true;
        }

        return false;
    }

    private static boolean solution(int N, int M, int W) throws IOException {
        edges = new ArrayList<>();

        // 도로 초기화
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            edges.add(new int[] {u, v, w});
            edges.add(new int[] {v, u, w});
        }

        // 웜홀 초기화
        for (int i = 0; i < W; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            edges.add(new int[] {u, v, -w});
        }

        return bellman(0, N);
    }

    public static void main(String[] args) throws IOException {
        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            bw.write(solution(N, M, W) ? "YES" : "NO");
            bw.newLine();
        }

        bw.flush();
    }
}
