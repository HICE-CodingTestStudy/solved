import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int[][] S = new int[2][];
		StringTokenizer st = new StringTokenizer(br.readLine());
		S[0] = new int[N + 1];
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			S[0][i + 1] = S[0][i] + num;
		}
		int M = Integer.parseInt(br.readLine());
		S[1] = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			S[1][i + 1] = S[1][i] + num;
		}
		int sel = S[0].length < S[1].length ? 0 : 1, inv = S[0].length < S[1].length ? 1 : 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 1; i < S[sel].length; i++) {
			for(int j = 0; j < i; j++) {
				map.put(S[sel][i] - S[sel][j], map.getOrDefault(S[sel][i] - S[sel][j], 0) + 1);
			}
		}

		long ans = 0;
		for(int i = 1; i < S[inv].length; i++) {
			for(int j = 0; j < i; j++) {
				int diff = S[inv][i] - S[inv][j];
				ans += (long) map.getOrDefault(T - diff, 0);
			}
		}
		System.out.println(ans);
	}
}