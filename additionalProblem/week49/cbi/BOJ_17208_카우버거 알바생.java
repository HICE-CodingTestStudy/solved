import java.io.*;
import java.util.*;

public class Main {
	static int[][][] dp;
	static int[][] orders;
	static int N;
	static int foo(int b, int f, int idx) {
		if(b <= 0 || f <= 0 || idx == N) return 0;
		if(dp[b][f][idx] != 0) return dp[b][f][idx];
		if(b >= orders[idx][0] && f >= orders[idx][1]) {
			dp[b][f][idx] = Math.max(dp[b][f][idx], foo(b - orders[idx][0], f - orders[idx][1], idx + 1) + 1);
		}
		dp[b][f][idx] = Math.max(dp[b][f][idx], foo(b, f, idx + 1));
		return dp[b][f][idx];
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		orders = new int[N][2];
		dp = new int[M + 1][K + 1][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			orders[i][0] = Integer.parseInt(st.nextToken());
			orders[i][1] = Integer.parseInt(st.nextToken());
		}

		System.out.println(foo(M, K, 0));
	}
}