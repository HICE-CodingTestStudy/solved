import java.io.*;
import java.util.*;

public class Minjun {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX = 100000;

    private static List<int[]>[] roads;
    private static int N, K;

    public static void main(String[] args) throws IOException {
        parseInput();
        String answer = solution();
        print(answer);
    }

    private static void parseInput() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        roads = new List[N + 1];
        for (int i = 1; i <= N; i++)
            roads[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);
            roads[u].add(new int[] {v, w});
            roads[v].add(new int[] {u, w});
        }
    }

    private static String solution() {
        int[] fromMinjun = dijkstra(1);
        int[] fromKunwoo = dijkstra(K);
        return fromMinjun[N] == fromMinjun[K] + fromKunwoo[N] ? "SAVE HIM" : "GOOD BYE";
    }

    private static int[] dijkstra(int start) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] dists = new int[N + 1];
        Arrays.fill(dists, MAX);
        queue.add(new int[] {start, 0});
        dists[start] = 0;

        while (!queue.isEmpty()) {
            int node = queue.peek()[0], dist = queue.peek()[1];
            queue.poll();

            if (dists[node] < dist) continue;

            for (int[] next : roads[node]) {
                int alt = dist + next[1];
                if (alt >= dists[next[0]]) continue;

                dists[next[0]] = alt;
                queue.add(new int[] {next[0], alt});
            }
        }

        return dists;
    }

    private static void print(String answer) throws IOException {
        bw.write(answer);
        bw.flush();
    }
}
