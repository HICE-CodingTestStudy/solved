package week50;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14698 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            long res = 1;
            for (int j = 0; j < N; j++) {
                pq.offer(Long.valueOf(st.nextToken()));
            }

            while (pq.size() != 1) {
                Long num1 = pq.poll();
                Long num2 = pq.poll();
                long mul = num1 * num2;
                res = ((mul % 1000000007) * res) % 1000000007;
                pq.offer(mul);
            }
            sb.append(res % 1000000007).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
