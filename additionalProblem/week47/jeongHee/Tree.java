import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Tree {
    // https://www.acmicpc.net/problem/4803
    // 트리
    static int N, M;
    static int[] parent, height;

    static int find(int x) {
        if (parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py)
            return false;
        if (height[py] > height[px]) {
            int tmp = px;
            px = py;
            py = tmp;
        }
        if (height[px] == height[py])
            height[px]++;
        parent[px] += parent[py];
        parent[py] = px;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0) return;
            height = new int[N + 1];
            parent = new int[N + 1];
            HashSet<Integer> cycle = new HashSet<>();
            HashSet<Integer> cycleP = new HashSet<>();
            Arrays.fill(parent, -1);
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(bf.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (!union(a, b)) cycle.add(find(a));
            }
            for (Integer i : cycle) {
                cycleP.add(find(i));
            }
            int ans = 0;
            for (int i = 1; i <= N; i++) {
                if (parent[i] < 0 && !cycleP.contains(i)) ans++;
            }
            System.out.print("Case " + t + ": ");
            switch (ans) {
                case 0:
                    System.out.println("No trees.");
                    break;
                case 1:
                    System.out.println("There is one tree.");
                    break;
                default:
                    System.out.printf("A forest of %d trees.\n", ans);
                    break;
            }
            t++;
        }

    }
}
