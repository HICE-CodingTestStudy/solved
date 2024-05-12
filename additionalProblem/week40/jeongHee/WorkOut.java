import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WorkOut {
    //https://www.acmicpc.net/problem/1956
    //운동
    static int V, E;
    static int ans = Integer.MAX_VALUE;
    static List<List<int[]>> graph = new ArrayList<>();

    static void solution(int i) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[i] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{i, 0});
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            if (dist[now[0]] < now[1]) continue;
            for (int[] next : graph.get(now[0])) {
                if (next[0] == i) {
                    ans = Math.min(ans, now[1] + next[1]);
                }
                if (dist[next[0]] <= now[1] + next[1]) continue;
                dist[next[0]] = now[1] + next[1];
                pq.add(new int[]{next[0], dist[next[0]]});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b, c});
        }
        for (int i = 1; i <= V; i++) {
            solution(i);
        }
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
