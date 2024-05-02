import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static class Info implements Comparable<Info>{
		int idx, val;
		Info(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
		@Override
		public int compareTo(Info i) {
			return this.val == i.val ? Integer.compare(this.idx, i.idx) : Integer.compare(this.val, i.val);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Info[] arr = new Info[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = new Info(i + 1, Integer.parseInt(br.readLine()));
		}
		
		Arrays.sort(arr);
		int answer = 0;
		for(int i = 0; i < N; i++) {
			answer = Math.max(answer, arr[i].idx - i);
		}
		System.out.println(answer);
	}
}