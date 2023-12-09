package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class YeonSei {
    //https://www.acmicpc.net/problem/11562
    //백양로 브레이크
    static int n, m;
    static long[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cost = new long[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                cost[i][j] = Integer.MAX_VALUE;
                if (i == j) cost[i][j] = 0;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cost[u][v] = 0;
            cost[v][u] = Math.min(1, cost[v][u]);
            if (b == 1) cost[v][u] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                for (int k = 1; k < n + 1; k++) {
                    if (i == j) continue;
                    if (cost[i][j] > cost[i][k] + cost[k][j])
                        cost[i][j] = cost[i][k] + cost[k][j];
                }
            }
        }
        int k = Integer.parseInt(bf.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(bf.readLine());
            System.out.println(cost[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]);
        }
    }
}
