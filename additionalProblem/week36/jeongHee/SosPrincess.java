package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SosPrincess {
    //https://www.acmicpc.net/problem/17836
    //공주님을 구해라!
    static int N, M, T;
    static int[] gram;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void solution() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == N - 1 && now[1] == M - 1) {
                ans = Math.min(ans, now[2]);
                return;
            }
            if (now[0] == gram[0] && now[1] == gram[1]) {
                ans = Math.min(ans, now[2] + (N - 1 - now[0]) + (M - 1 - now[1]));
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextI = now[0] + dx[i];
                int nextJ = now[1] + dy[i];
                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) continue;
                if (visited[nextI][nextJ]) continue;
                if (map[nextI][nextJ] == 1) continue;
                visited[nextI][nextJ] = true;
                queue.add(new int[]{nextI, nextJ, now[2] + 1});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 2) gram = new int[]{i, j};
                map[i][j] = a;
            }
        }
        solution();
        if (ans > T) {
            System.out.println("Fail");
            return;
        }
        System.out.println(ans);
    }

}
