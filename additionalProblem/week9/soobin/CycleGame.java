package week9.soobin;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CycleGame {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] parent, height;

    private static int find(int x) {
        if (parent[x] == -1) return x;

        return parent[x] = find(parent[x]);
    }

    private static boolean union(int u, int v) {
        u = find(u);
        v= find(v);

        if (u == v) return false;

        if (height[u] < height[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        if (height[u] == height[v]) height[u]++;
        parent[v] = u;
        height[v] = -1;
        return true;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        height = new int[N];
        Arrays.fill(parent, -1);

        int answer = 0;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            answer++;
            if (!union(u, v)) break;
        }

        answer = M < 0 ? 0 : answer;

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
