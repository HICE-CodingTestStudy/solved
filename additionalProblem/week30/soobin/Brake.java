package week30.soobin;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Brake {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX = 100000;

    private static int[][] dist;
    private static int N;

    private static void addRoad(int u, int v, int w) {
        dist[u][v] = 0;
        dist[v][u] = w;
    }

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            dist = new int[N][N];
            init();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken()) == 0 ? 1 : 0;
                addRoad(u, v, w);
            }
        } catch (IOException e) {}
    }

    private static void init() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
        }
    }

    private static void floyd() {
        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }

    private static void printAnswer() {
        try {
            int K = Integer.parseInt(br.readLine());
            while (K-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                bw.write(String.format("%d\n", dist[u][v]));
            }
            bw.flush();
        } catch (IOException e) {}
    }

    public static void main(String[] args) {
        parseInput();
        floyd();
        printAnswer();
    }
}
