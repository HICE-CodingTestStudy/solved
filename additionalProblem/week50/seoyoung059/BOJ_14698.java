package ing.week50.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14698 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int n; long a, b, sum, tmp;
        int mod = 1_000_000_007;

        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            sum = 1;

            while (pq.size() > 1) {
                a = pq.poll();
                b = pq.poll();
                tmp = a * b;
                sum = (sum * (tmp%mod)) % mod;
                pq.offer(tmp);
            }
            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}
