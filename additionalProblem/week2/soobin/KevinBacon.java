package week2.soobin;

import java.io.*;
import java.util.*;

public class KevinBacon {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] graph;

    private static void addEdge(int u, int v) {
        graph[u].add(v);
        graph[v].add(u);
    }

    private static int bfs(int start, int N) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        int[] kevins = new int[N];

        queue.add(start);
        kevins[start] = 0;
        visited[start] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int n : graph[u]) {
                if (visited[n]) continue;

                queue.add(n);
                visited[n] = true;
                kevins[n] = kevins[u] + 1;
            }
        }

        int answer = 0;
        for (int k : kevins) answer += k;
        return answer;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];

        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            addEdge(u, v);
        }

        int minKevin = Integer.MAX_VALUE, min = 0;
        for (int i = 0; i < N; i++) {
            int current = bfs(i, N);
            if (current < minKevin) {
                minKevin = current;
                min = i;
            }
        }

        bw.write(String.valueOf(min + 1));
        bw.flush();
    }
}
