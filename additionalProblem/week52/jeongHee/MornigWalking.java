import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //아침산책
    //https://www.acmicpc.net/problem/21606
    static int N, ans;
    static boolean[] out;
    static int[] parent, height, connected;

    static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if(px==py) return false;
        if(height[px]>height[py]) {
            int tmp = px;
            px = py;
            py = tmp;
        }
        if (height[px] == height[py]) {
            height[px]++;
        }
        parent[px] += parent[py];
        parent[py] = px;
        height[py] = 0;
        return true;
    }
    static void solution() {
        int[] count = new int[N];
        Set<Integer> hs = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int pi = find(i);
            count[pi] += connected[i];
            hs.add(pi);
        }
        for (Integer i : hs) {
            ans += count[i] * (count[i] - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        out = new boolean[N];
        connected = new int[N];
        height = new int[N];
        parent = new int[N];
        Arrays.fill(parent, -1);
        String s = bf.readLine();
        for (int i = 0; i < s.length(); i++) {
            out[i] = s.charAt(i) == '0';
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (!out[a] && !out[b]) ans += 2;
            else if (out[a] && out[b]) {
                union(a, b);
            }
            else {
                if(out[a]) connected[a]++;
                else connected[b]++;
            }
        }
        solution();
        System.out.println(ans);
    }
}