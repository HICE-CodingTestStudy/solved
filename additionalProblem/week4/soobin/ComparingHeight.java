package week4.soobin;

import java.io.*;
import java.util.*;

public class ComparingHeight {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] graph;
    private static int[][] compare; // 0: 나보다 작은 애들 수, 1: 나보다 큰 애들 수

    private static void addEdge(int u, int v) {
        graph[u].add(v);
    }


    private static void findMyOrder(int i, int N) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        visited[i] = true;
        queue.add(i);

        int count = 0;
        while (!queue.isEmpty()) {
            int n = queue.poll();

            for (int s : graph[n]) {
                if (visited[s]) continue;

                queue.add(s);
                visited[s] = true;
                compare[s][0]++;
                count++;
            }
        }

        compare[i][1] = count;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        compare = new int[N][2];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            addEdge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        }

        for (int i = 0; i < N; i++) findMyOrder(i, N);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (compare[i][0] + compare[i][1] == N - 1) answer++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
