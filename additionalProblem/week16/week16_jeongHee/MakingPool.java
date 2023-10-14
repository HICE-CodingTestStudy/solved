package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MakingPool {
    //https://www.acmicpc.net/problem/1113
    //수영장 만들기
    static int N;
    static int M;
    static int[][] map;
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int lastAns = 1;

    static void bfs(int i, int j, int maxWater) {
        boolean isValid = true;
        lastAns = 1;
        visited[i][j] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nextI = now[0] + dx[k];
                int nextJ = now[1] + dy[k];

                //외부와 닿은 경우 maxWater만큼은 못채움
                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) {
                    isValid = false;
                    continue;
                }
                if (visited[nextI][nextJ]) continue;

                //maxWater만큼의 벽을 만난 경우 더이상 뻗지 않음
                if (map[nextI][nextJ] >= maxWater) continue;
                queue.add(new int[]{nextI, nextJ});
                visited[nextI][nextJ] = true;
                lastAns++;
            }
        }
        if(isValid) ans += lastAns;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int max = 0;
        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                max = Math.max(max, map[i][j]);
            }
        }
        visited = new boolean[N][M];
        for (int k = 2; k <= max; k++) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (!visited[i][j] && map[i][j] < k)
                        bfs(i, j, k);
                }
            }
        }
        System.out.println(ans);
    }
}
