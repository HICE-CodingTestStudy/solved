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

            //외부와 닿은 경우 maxWater만큼은 못채움
            if (now[0] < 0 || now[0] >= N || now[1] < 0 || now[1] >= M) return;

            //maxWater만큼의 벽을 만난 경우 더이상 뻗지 않음
            if (map[now[0]][now[1]] >= maxWater) continue;

            for (int k = 0; k < 4; k++) {
                int nextI = now[0] + dx[k];
                int nextJ = now[1] + dy[k];
                if (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < M
                        && visited[nextI][nextJ]) continue;
                queue.add(new int[]{nextI, nextJ});
                if (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < M)
                    visited[nextI][nextJ] = true;
            }
        }
        ans+=maxWater-map[i][j];
        map[i][j]=maxWater;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        int max = 0;
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                max = Math.max(max, map[i][j]);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = max; k > map[i][j]; k--) {
                    bfs(i, j, k);
                }
            }
        }
        System.out.println(ans);
    }

}
