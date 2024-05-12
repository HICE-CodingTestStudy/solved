import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	final static int MOD = 10007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), H = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] block = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			block[i] = new ArrayList<>();
			for(int j = 0; j < s.length; j++) {
				block[i].add(Integer.parseInt(s[j]));
			}
		}
		int[][] dp = new int[H + 1][N + 1];
		dp[0][0] = 1;
		for(int i = 0; i <= H; i++) {
			for(int j = 0; j < N; j++) {
				dp[i][j + 1] += dp[i][j];
				for(int k = 0; k < block[j].size(); k++) {
					int now = block[j].get(k);
					if(i >= now) {
						dp[i][j + 1] += dp[i - now][j] % MOD;
					} 
				}
				dp[i][j + 1] %= MOD;
			}
		}
		System.out.println(dp[H][N]);
	}
}