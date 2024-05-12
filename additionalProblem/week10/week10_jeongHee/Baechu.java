package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baechu {
    //https://www.acmicpc.net/problem/1012
    //유기농 배추
    static int count = 0;

    public static void bfs(int i, int j, int[][] map, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        if (visited[i][j]) return;
        count++;
        visited[i][j] = true;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            for (int k = 0; k < 4; k++) {
                if (x + dx[k] < 0 || x + dx[k] >= map.length || y + dy[k] < 0 || y + dy[k] >= map[i].length)
                    continue;
                if (visited[x + dx[k]][y + dy[k]]) continue;
                if (map[x + dx[k]][y + dy[k]] == 0) continue;
                visited[x + dx[k]][y + dy[k]] = true;
                queue.add(new int[]{x + dx[k], y + dy[k]});
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            count = 0;
            int M = sc.nextInt();
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[][] map = new int[M][N];
            boolean[][] visited = new boolean[M][N];
            for (int j = 0; j < K; j++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                map[a][b] = 1;
            }
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] == 0) continue;
                    bfs(j, k, map, visited);
                }
            }
            System.out.println(count);
        }
    }
}
