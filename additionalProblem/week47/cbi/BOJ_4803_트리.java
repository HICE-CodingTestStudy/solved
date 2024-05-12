import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = 1;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			if(n == 0 && m == 0) break;
			ArrayList<Integer>[] adj = new ArrayList[n];
			for(int i = 0; i < n; i++) {
				adj[i] = new ArrayList<>();
			}
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
				adj[a - 1].add(b - 1);
				adj[b - 1].add(a - 1);
			}
			int count = 0;
			boolean[] visited = new boolean[n];
			
			for(int i = 0; i < n; i++) {
				if(!visited[i]) {
					int vertex = 0, edges = 0;
					Queue<Integer> queue = new ArrayDeque<>();
					queue.add(i);
					visited[i] = true;
					while(!queue.isEmpty()) {
						int now = queue.poll();
						vertex++;
						for(int next : adj[now]) {
							edges++;
							if(visited[next]) continue;
							visited[next] = true;
							queue.add(next);
						}
					}
					if((edges / 2) == vertex - 1) count++;
				}
			}
			sb.append("Case ").append(test_case++).append(": ");
			if(count == 0) sb.append("No trees.\n");
			else if(count == 1) sb.append("There is one tree.\n");
			else sb.append("A forest of ").append(count).append(" trees.\n");
		}
		System.out.println(sb);
	}
}