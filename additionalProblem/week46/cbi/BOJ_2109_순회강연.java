import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Lecture implements Comparable<Lecture>{
		int pay, day;
		Lecture(int pay, int day) {
			this.pay = pay;
			this.day = day;
		}
		@Override
		public int compareTo(Lecture l) {
			if(this.day == l.day) return Integer.compare(l.pay, this.pay);
 			return Integer.compare(l.day, this.day);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()), maxDay = 1;
		Lecture[] L = new Lecture[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
			L[i] = new Lecture(p, d);
			maxDay = Math.max(maxDay, d);
		}
		Arrays.sort(L);
		
		int index = 0, ans = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
		while(maxDay > 0 || index < N) {
			while(index < N && L[index].day >= maxDay) {
				pq.add(L[index].pay);
				index++;
			}
			if(!pq.isEmpty()) {
				ans += pq.poll();
			}
			maxDay--;
		}
		System.out.println(ans);
	}
}