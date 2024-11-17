package Ing.week59.seoyoung059;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_31863_내진설계 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n+2][m+2];
        String str;
        int cnt1 = 0;
        int cnt2 = 0;

        for (int i = 0; i < n + 2; i++) {
            map[i][0] = -1;
            map[i][m+1] = -1;
        }

        for (int i = 0; i < m + 2; i++) {
            map[0][i] = -1;
            map[n+1][i] = -1;
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                switch (str.charAt(j)) {
                    case '.':
                        map[i+1][j+1] = 0;
                        break;
                    case '*':
                        map[i+1][j+1] = 1;
                        cnt1++;
                        break;
                    case '#':
                        map[i+1][j+1] = 2;
                        cnt1++;
                        break;
                    case '|':
                        map[i+1][j+1] = -1;
                        break;
                    case '@':
                        map[i+1][j+1] = -2;
                        q.offer(new int[]{i+1, j+1});
                        break;
                }
            }
        }

        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        int[] curr = q.pollFirst();
        int qSize, ny, nx;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 2; j++) {
                ny = curr[0] + dy[i] * j;
                nx = curr[1] + dx[i] * j;

                if(map[ny][nx]<0) break;
                if(map[ny][nx]==0) continue;
                map[ny][nx]--;
                if(map[ny][nx]==0) {
                    cnt2++;
                    q.offer(new int[]{ny, nx});
                }
            }
        }

        while (!q.isEmpty()) {
            qSize = q.size();
            while (qSize-- > 0){
                curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    ny = curr[0] + dy[i];
                    nx = curr[1] + dx[i];

                    if(map[ny][nx]<=0) continue;
                    map[ny][nx]--;
                    if(map[ny][nx]==0) {
                        cnt2++;
                        q.offer(new int[]{ny, nx});
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cnt2).append(' ').append(cnt1-cnt2);
        System.out.println(sb);
    }
}