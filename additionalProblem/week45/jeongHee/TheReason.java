import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class TheReason {
    //https://www.acmicpc.net/problem/17129
    //윌리암슨수액빨이딱따구리가 정보섬에 올라온 이유
    static final String TRUE = "TAK", FALSE = "NIE";
    static int N, M;
    static int[][] map;
    static int[] start;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < M && map[i][j] != 1;
    }

    static int solution() {
        boolean[][] visited = new boolean[N][M];
        visited[start[0]][start[1]] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(start);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] now = queue.poll();
                if (map[now[0]][now[1]] > 2) return count;
                for (int i = 0; i < 4; i++) {
                    int nextI = now[0] + dx[i];
                    int nextJ = now[1] + dy[i];
                    if (!isValid(nextI, nextJ)) continue;
                    if (visited[nextI][nextJ]) continue;
                    visited[nextI][nextJ] = true;
                    queue.add(new int[]{nextI, nextJ});
                }
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
                if (map[i][j] == 2) {
                    start = new int[]{i, j};
                }
            }
        }
        int result = solution();
        if (result == -1) {
            System.out.println(FALSE);
            return;
        }
        System.out.println(TRUE);
        System.out.println(result);
    }
}
