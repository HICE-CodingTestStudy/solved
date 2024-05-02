import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] balls = br.readLine().toCharArray();
		int r = 0, b = 0;
		
		for(char ball : balls) {
			if(ball == 'R') r++;
			else b++;
		}
		int answer = Math.min(r, b);
		int tr = r, tb = b;
		
		for(int i = 0; i < N; i++) {
			if(balls[i] == 'R') tr--;
			else tb--;
			if(i == N - 1 || balls[i] != balls[i + 1]) break;
		}
		answer = Math.min(answer, Math.min(tr, tb));
		
		tr = r; 
		tb = b;
		for(int i = N - 1; i >= 0; i--) {
			if(balls[i] == 'R') tr--;
			else tb--;
			if(i == 0 || balls[i] != balls[i - 1]) break;
		}
		answer = Math.min(answer, Math.min(tr, tb));
		
		System.out.println(answer);
	}
}