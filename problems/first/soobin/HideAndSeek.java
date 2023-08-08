package first.soobin;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HideAndSeek {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int minTime = Integer.MAX_VALUE;
    private static int possible;
    private static boolean[] visited = new boolean[100001];

    private static void solution(int N, int K) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {N, 0});
        visited[N] = true;

        while(!queue.isEmpty()) {
            int[] next = queue.poll();
            int position = next[0];
            int time = next[1];
            visited[position] = true;

            if (position == K) {
                if (time == minTime) {
                    possible++;
                }
                if (time < minTime) {
                    possible = 1;
                    minTime = time;
                }
                continue;
            }

            if (position > 0 && !visited[position - 1]) queue.add(new int[] {position - 1, time + 1});
            if (position < 100000 && !visited[position + 1]) queue.add(new int[] {position + 1, time + 1});
            if (position <= 50000 && !visited[position * 2]) queue.add(new int[] {position * 2, time + 1});
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        solution(N, K);

        bw.write(String.format("%d\n", minTime));
        bw.write(String.valueOf(possible));
        bw.flush();
    }
}
