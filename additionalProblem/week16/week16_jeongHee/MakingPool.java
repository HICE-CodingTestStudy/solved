package additional;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MakingPool {
    //https://www.acmicpc.net/problem/1113
    //수영장 만들기
    static int N;
    static int M;
    static int[][] map;
    static int ans = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs(int i, int j, int maxWater) {
        if (map[i][j] >= maxWater) return;
        boolean[][] visited = new boolean[N][M];
        visited[i][j] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nextI = now[0] + dx[k];
                int nextJ = now[1] + dy[k];

                //외부와 닿은 경우 maxWater만큼은 못채움
                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) return;
                if (visited[nextI][nextJ]) continue;

                //maxWater만큼의 벽을 만난 경우 더이상 뻗지 않음
                if (map[nextI][nextJ] >= maxWater) continue;
                if (nextI == 0 || nextJ == 0 || nextI == N - 1 || nextJ == M - 1) return;
                queue.add(new int[]{nextI, nextJ});
                visited[nextI][nextJ] = true;
            }
        }
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < M; l++) {
                ans += maxWater - map[i][j];
                map[i][j] = maxWater;
            }
        }
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
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                for (int k = max; k > map[i][j]; k--) {
                    int before = ans;
                    bfs(i, j, k);
                    if(ans!=before) break;
                }
            }
        }
        System.out.println(ans);
    }
}
