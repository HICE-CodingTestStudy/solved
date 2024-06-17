import java.io.*;
import java.util.*;

public class Main {
	static class Pair implements Comparable<Pair> {
		int base, plus;
		Pair(int base, int plus) {
			this.base = base;
			this.plus = plus;
		}
		public int compareTo(Pair p) {
			return Integer.compare(p.plus, this.plus);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken()) * 24;
		int M = Integer.parseInt(st.nextToken());
		Pair[] subjects = new Pair[M];
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		int ans = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int base = Integer.parseInt(st.nextToken());
			subjects[i] = new Pair(base, 0);
			ans += base;
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			subjects[i].plus = Integer.parseInt(st.nextToken());
			if(100 - subjects[i].base < subjects[i].plus) {
				pq.add(new Pair(subjects[i].base,Math.max(1, 100 - subjects[i].base )));
			}
			else pq.add(new Pair(subjects[i].base, subjects[i].plus));
		}



		while(H > 0 && !pq.isEmpty()) {
			Pair p = pq.poll();
			int time = (100 - p.base) / p.plus;
			ans += Math.min(H, time) * p.plus;
			if(p.base + Math.min(H, time) * p.plus < 100) {
				pq.add(new Pair(p.base + Math.min(H, time) * p.plus, 100 - p.base - Math.min(H, time) * p.plus));
			}
			H -= Math.min(H, time);
		}
		System.out.println(ans);
	}
}