import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Ball implements Comparable<Ball> {
		int index, color, size;
		Ball(int index, int color, int size) {
			this.index = index;
			this.color = color;
			this.size = size;
		}
		@Override
		public int compareTo(Ball b) {
			return Integer.compare(this.size, b.size);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Ball[] balls = new Ball[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken()), size = Integer.parseInt(st.nextToken());
			balls[i] = new Ball(i, color, size);
		}
		Arrays.sort(balls);
		int[] total = new int[N], same = new int[N], last = new int[N + 1];
		int[][] count = new int[2][N + 1];
		Arrays.fill(last, -1);
		Arrays.fill(count[0], 1);
		Arrays.fill(count[1], 1);
		
		last[balls[0].color] = 0;
		for(int i = 1; i < N; i++) {
			int prev = balls[i - 1].index, now = balls[i].index;
			
			if(balls[i - 1].size == balls[i].size) {
				total[now] = total[prev];
				count[0][now] = count[0][prev] + 1;
			}
			else {
				total[now] = total[prev] + count[0][prev] * balls[i - 1].size;
				count[0][prev] = 1;
			}
			
			if(last[balls[i].color] != -1) {
				int lastIndex = balls[last[balls[i].color]].index;
				if(balls[last[balls[i].color]].size == balls[i].size) {
					same[now] = same[lastIndex];
					count[1][now] = count[1][lastIndex] + 1;
				}
				else {
					same[now] = same[lastIndex] + count[1][lastIndex] * balls[last[balls[i].color]].size;
					count[1][lastIndex] = 1;
				}
			}
			
			last[balls[i].color] = i;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(total[i] - same[i]).append('\n');
		}
		System.out.println(sb);
	}
}