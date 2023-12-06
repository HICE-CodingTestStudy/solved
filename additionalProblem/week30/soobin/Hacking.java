package week30.soobin;

import java.io.*;
import java.util.*;

public class Hacking {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] computers;
    private static int[] nHackable;
    private static int N, M, max = Integer.MIN_VALUE;

    private static void countHackable(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int n = queue.remove();

            for (int next : computers[n]) {
                if (!visited[next]) {
                    nHackable[next]++;
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        computers = new List[N];
        nHackable = new int[N];
        for (int i = 0; i < N; i++) computers[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            computers[A].add(B);
        }

        for (int i = 0; i < N; i++) countHackable(i);
        for (int i = 0; i < N; i++)
            max = Math.max(max, nHackable[i]);

        for (int i = 0; i < N; i++)
            if (nHackable[i] == max)
                bw.write(String.format("%d ", i + 1));
        bw.flush();
    }
}
