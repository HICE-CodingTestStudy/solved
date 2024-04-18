import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/13565
    //침투
    static int N, M;
    static Queue<int[]> queue = new ArrayDeque<>();
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static boolean solution() {
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextI = now[0] + dx[i];
                int nextJ = now[1] + dy[i];
                if (nextI < 0 || nextJ < 0 || nextJ >= M || nextI >= N) continue;
                if (visited[nextI][nextJ]) continue;
                if (map[nextI][nextJ] == 1) continue;
                visited[nextI][nextJ] = true;
                if (nextI == N - 1) return true;
                queue.add(new int[]{nextI, nextJ});
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
                if (i == 0 && map[i][j] == 0) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        System.out.println(solution() ? "YES" : "NO");
    }
}