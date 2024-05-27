import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Masan {
    //https://www.acmicpc.net/problem/18223
    //민준이와 마산 그리고 건우
    static int V, E, P;
    static List<List<int[]>> graph = new ArrayList<>();

    static long solution(int start, int end) {
        long[] dist = new long[V + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
        pq.add(new long[]{start, 0});
        while (!pq.isEmpty()) {
            long[] now = pq.poll();
            if (now[1] > dist[(int) now[0]]) continue;
            for (int[] next : graph.get((int) now[0])) {
                if (dist[next[0]] <= dist[(int) now[0]] + next[1]) continue;
                dist[next[0]] = dist[(int) now[0]] + next[1];
                pq.add(new long[]{next[0], dist[next[0]]});
            }
        }
        return dist[end];

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        long minDist = solution(1, V);
        long kDist = solution(1, P) + solution(P, V);
        System.out.println(minDist == kDist ? "SAVE HIM" : "GOOD BYE");
    }
}
