import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //https://www.acmicpc.net/problem/2056
    //작업
    static int N, ans;
    static int[] before, cost, max;
    static List<Integer>[] after;
    static Queue<int[]> queue = new ArrayDeque<>();

    static void solution() {

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            ans = Math.max(ans, now[1]);
            for (int next : after[now[0]]) {
                max[next] = Math.max(max[next], now[1] + cost[next]);
                before[next]--;
                if (before[next] == 0) {
                    queue.offer(new int[]{next, max[next]});
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        before = new int[N];
        cost = new int[N];
        after = new List[N];
        max = new int[N];
        for (int i = 0; i < N; i++) {
            after[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            before[i] = x;
            if (x == 0) {
                queue.offer(new int[]{i, cost[i]});
                max[i] = cost[i];
            }
            for (int j = 0; j < x; j++) {
                int a = Integer.parseInt(st.nextToken()) - 1;
                after[a].add(i);
            }
        }
        solution();
        System.out.println(ans);
    }

}