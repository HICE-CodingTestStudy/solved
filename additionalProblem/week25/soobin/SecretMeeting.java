package week25.soobin;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SecretMeeting {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int MAX = 1000000;

    private static List<int[]>[] graph;
    private static int[][] dist;
    private static int[] friends;
    private static int N;

    private static void addEdge(int u, int v, int w) {
        graph[u].add(new int[] {v, w});
        graph[v].add(new int[] {u, w});
    }

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            graph = new List[N];
            for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken());
                addEdge(u, v, w);
            }

            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            friends = new int[K];
            for (int i = 0; i < K; i++)
                friends[i] = Integer.parseInt(st.nextToken()) - 1;
        } catch (IOException e) {}
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.format("%d\n", answer));
        } catch (IOException e) {}
    }

    private static void fw() {
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], MAX);

            dist[i][i] = 0;
            for (int[] edge : graph[i])
                dist[i][edge[0]] = edge[1];
        }

        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
    }

    private static int getMeetingSpace() {
        int answer = 0, sum = MAX;
        for (int i = 0; i < N; i++) {
            int temp = 0;
            for (int friend : friends) temp += dist[friend][i];
            if (temp < sum) {
                sum = temp;
                answer = i;
            }
        }

        return answer + 1;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            parseInput();
            fw();
            int answer = getMeetingSpace();
            printAnswer(answer);
        }
        bw.flush();
    }
}
