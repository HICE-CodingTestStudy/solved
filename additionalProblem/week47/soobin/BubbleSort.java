import java.io.*;
import java.util.*;

public class BubbleSort {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            pq.add(new int[] {n, i});
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int[] pair = pq.poll();
            answer = Math.max(pair[1] - i, answer);
        }

        bw.write(String.valueOf(answer + 1));
        bw.flush();
    }
}
