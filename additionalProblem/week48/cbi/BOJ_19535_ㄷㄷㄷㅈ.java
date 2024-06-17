import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] cnt = new long[N + 1];
		int[][] edges = new int[N - 1][2];
		
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken()); 
			edges[i][1] = Integer.parseInt(st.nextToken());
			cnt[edges[i][0]]++;
			cnt[edges[i][1]]++;
		}
		
		long D = 0, G = 0;
		for(int[] node : edges) {
			D += (cnt[node[0]] - 1) * (cnt[node[1]] - 1);
		}
		for(int i = 1; i <= N; i++) {
			G += cnt[i] * (cnt[i] - 1) * (cnt[i] - 2) / 6;
		}
		System.out.println(D == 3 * G ? "DUDUDUNGA" : (D > 3 * G ? "D" : "G"));
	} 
}