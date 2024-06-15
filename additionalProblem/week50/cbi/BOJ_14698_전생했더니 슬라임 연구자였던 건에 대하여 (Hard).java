import java.io.*;
import java.util.*;

public class Main {
	final static long MOD = 1000000007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(--T >= 0) {
			int N = Integer.parseInt(br.readLine());
			long ans = 1;
			PriorityQueue<Long> pq = new PriorityQueue<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				long slime = Long.parseLong(st.nextToken());
				pq.offer(slime);
			}

			while(pq.size() > 1) {
				long A = pq.poll(), B = pq.poll(), C = A * B;
				pq.offer(C);
				ans = (ans * (C % MOD)) % MOD;
			}

			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}
}