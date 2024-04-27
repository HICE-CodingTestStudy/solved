import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][] mat;
	static int R, C;
	static boolean dfs(int row, int col) {
		if(row < 0 || row >= R || col < 0 || col >= C || mat[row][col] == 'x') return false;
		mat[row][col] = 'x';
		if(col == 0) return true;
		if(dfs(row - 1, col - 1) || dfs(row, col - 1) || dfs(row + 1, col - 1)) return true;
		return false;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		mat = new char[R][C];
		for(int i = 0; i < R; i++) {
			mat[i] = br.readLine().toCharArray();
		}
		
		int answer = 0;
		for(int i = 0; i < R; i++) {
			if(dfs(i, C - 1))answer++;
		}
		System.out.println(answer);
	}
}