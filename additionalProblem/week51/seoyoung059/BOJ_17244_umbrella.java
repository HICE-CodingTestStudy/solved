package fin.HICE.week51.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_17244_umbrella {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] home = new int[n][m];
        String str;
        int cntX = 0, sy = -1, sx = -1, ey = -1, ex = -1;
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                switch (str.charAt(j)) {
                    case '#':
                        home[i][j] = -1;
                        break;
                    case 'X':
                        cntX++;
                        home[i][j] = cntX;
                        break;
                    case 'S':
                        sy = i;
                        sx = j;
                        break;
                    case 'E':
                        ey = i;
                        ex = j;
                }
            }
        }


        boolean[][][] visited = new boolean[(1 << cntX)][n][m];
        int[] dy = {-1, 1, 0, 0}, dx = {0, 0, 1, -1};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sy, sx, 0});
        visited[0][sy][sx] = true;
        int qSize, ny, nx, ans = 0, tmp;
        int[] curr;
        loop:
        while (!q.isEmpty()) {
            qSize = q.size();
            while (qSize-- > 0) {
                curr = q.pollFirst();

                if (curr[0] == ey && curr[1] == ex && (curr[2] == (1 << cntX) - 1)) break loop;
                for (int i = 0; i < 4; i++) {
                    ny = curr[0] + dy[i];
                    nx = curr[1] + dx[i];
                    if (ny < 0 || n <= ny || nx < 0 || m <= nx || home[ny][nx] == -1) continue;
                    else if (home[ny][nx] == 0) {
                        tmp = curr[2];
                    } else {
                        tmp = curr[2] | (1 << (home[ny][nx] - 1));
                    }
                    if (visited[tmp][ny][nx]) continue;
                    visited[tmp][ny][nx] = true;
                    q.offerLast(new int[]{ny, nx, tmp});
                }
            }
            ans++;

        }
        System.out.println(ans);
    }
}
