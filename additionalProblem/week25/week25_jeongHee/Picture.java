package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Picture {
    //https://www.acmicpc.net/problem/1926
    //그림
    static int ans = 0;
    static int count = 0;
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static void bfs(int i, int j) {
        if (visited[i][j] || map[i][j] == 0) return;
        visited[i][j] = true;
        count++;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        int tmpAns = 1;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nextI = now[0] + dx[k];
                int nextJ = now[1] + dy[k];
                if (nextI < 0 || nextJ < 0 || nextI >= n || nextJ >= m) continue;
                if (visited[nextI][nextJ]) continue;
                if (map[nextI][nextJ] == 0) continue;
                visited[nextI][nextJ] = true;
                queue.add(new int[]{nextI, nextJ});
                tmpAns++;
            }
        }
        ans = Math.max(tmpAns, ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bfs(i, j);
            }
        }
        System.out.println(count+"\n"+ans);
    }
}
