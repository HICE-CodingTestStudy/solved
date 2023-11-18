package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Kong2Pat2 {
    //https://www.acmicpc.net/problem/1245
    //농장 관리
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[][] map;
    static boolean[][] isPeak;
    static int N, M;

    static int solution() {
        int ans = 0;
        isPeak = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isPeak[i][j]) continue;
                List<int[]> peak = new ArrayList<>();
                Queue<int[]> queue = new LinkedList<>();
                boolean[][] visited = new boolean[N][M];
                queue.add(new int[]{i, j});
                visited[i][j] = true;
                boolean isValid = true;
                while (!queue.isEmpty() && isValid) {
                    int[] now = queue.poll();
                    peak.add(new int[]{now[0], now[1]});
                    for (int k = 0; k < 8 && isValid; k++) {
                        int nextI = now[0] + dx[k];
                        int nextJ = now[1] + dy[k];
                        if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M)
                            continue;
                        if (visited[nextI][nextJ]) continue;
                        visited[nextI][nextJ] = true;
                        if (map[nextI][nextJ] == map[i][j])
                            queue.add(new int[]{nextI, nextJ});
                        if (map[i][j] < map[nextI][nextJ]) {
                            isValid = false;
                            break;
                        }
                    }
                }
                if (isValid) {
                    ans++;
                    for (int[] ints : peak) {
                        isPeak[ints[0]][ints[1]] = true;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());
    }
}
