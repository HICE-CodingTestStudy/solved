import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	final static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		char[][] mat = new char[N][M];
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {
			mat[i] = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				if(mat[i][j] == '2') {
					queue.add(new int[]{i, j, 0});
					visited[i][j] = true;
				}
			}
		}
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int k = 0; k < 4; k++) {
				int nr = now[0] + dir[k][0], nc = now[1] + dir[k][1];
				if(nr < 0 || nc < 0 || nr >= N || nc >= M || mat[nr][nc] == '1' || visited[nr][nc]) continue;
				if(mat[nr][nc] >= '2') {
					System.out.println("TAK");
					System.out.println(now[2] + 1);
					return;
				}
				visited[nr][nc] = true;
				queue.add(new int[] {nr, nc, now[2] + 1});
			}
		}
		System.out.println("NIE");
	}
}