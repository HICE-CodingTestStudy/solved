import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/16202
    //MST 게임
    static int[] parent, height;
    static int N, M, K;
    static List<Info> list = new ArrayList<>();

    static class Info implements Comparable<Info> {
        int fir, sec;
        int d;

        public Info(int fir, int sec, int d) {
            this.fir = fir;
            this.sec = sec;
            this.d = d;
        }

        @Override
        public int compareTo(Info o) {
            return d - o.d;
        }
    }

    static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return false;
        if (height[px] < height[py]) {
            int tmp = px;
            px = py;
            py = tmp;
        }
        if (height[px] == height[py]) height[px]++;
        parent[px] += parent[py];
        parent[py] = px;
        height[py] = 0;
        return true;
    }

    static void init() {
        parent = new int[N];
        height = new int[N];
        Arrays.fill(parent, -1);
    }

    static void solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int mCount = 0;
            int cost = 0;
            for (int j = i; j < M && mCount != N - 1; j++) {
                Info now = list.get(j);
                if (union(now.fir, now.sec)) {
                    cost += now.d;
                    mCount++;
                }

            }
            if (mCount != N - 1) sb.append(0)
                                   .append(" ");
            else sb.append(cost)
                   .append(" ");
            init();
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            list.add(new Info(a, b, i + 1));
        }
        solution();
    }
}