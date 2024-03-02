import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Fire {
    //https://www.acmicpc.net/problem/4179
    //불!
    static int N, M;
    static char[][] map;
    static List<int[]> fire = new ArrayList<>();
    static int[] me;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static boolean isValid(int i, int j) {
        if (i < 0 || j < 0 || j >= M || i >= N) return false;
        if (map[i][j] == '#') return false;
        return true;
    }

    static void solution() {
        boolean[][][] visited = new boolean[N][M][2]; //0은 나 1은 불
        for (int[] f : fire) {
            visited[f[0]][f[1]][1] = true;
        }
        visited[me[0]][me[1]][0] = true;
        Queue<int[]> queueFire = new ArrayDeque<>(fire);
        Queue<int[]> queueMe = new ArrayDeque<>();
        queueMe.add(me);
        int count = 1;
        while (true) {
            if (!queueFire.isEmpty()) {
                int size = queueFire.size();
                while (size-- > 0) {
                    int[] now = queueFire.poll();
                    for (int i = 0; i < 4; i++) {
                        int nextI = now[0] + dx[i];
                        int nextJ = now[1] + dy[i];
                        if (!isValid(nextI, nextJ)) continue;
                        if (visited[nextI][nextJ][1]) continue;
                        visited[nextI][nextJ][1] = true;
                        map[nextI][nextJ] = 'F';
                        queueFire.offer(new int[]{nextI, nextJ});
                    }
                }
            }
            if (queueMe.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }
            int size = queueMe.size();
            while (size-- > 0) {
                int[] now = queueMe.poll();
                if (now[0] == 0 || now[1] == 0 || now[0] == N - 1 || now[1] == M - 1) {
                    System.out.println(count);
                    return;
                }
                for (int i = 0; i < 4; i++) {
                    int nextI = now[0] + dx[i];
                    int nextJ = now[1] + dy[i];
                    if (!isValid(nextI, nextJ)) continue;
                    if (visited[nextI][nextJ][0]) continue;
                    if (map[nextI][nextJ] == 'F') continue;
                    visited[nextI][nextJ][0] = true;
                    map[nextI][nextJ] = 'J';
                    queueMe.offer(new int[]{nextI, nextJ});
                }
            }
            count++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'J') me = new int[]{i, j};
                if (map[i][j] == 'F') fire.add(new int[]{i, j});
            }
        }
        solution();
    }
}
