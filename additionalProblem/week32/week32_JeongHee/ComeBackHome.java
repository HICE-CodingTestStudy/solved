package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ComeBackHome {
    //https://www.acmicpc.net/problem/1189
    //컴백홈
    static int R, C, K;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int solution(int count, int i, int j, boolean[][] visited) {
        if (count == K) {
            if (i == 0 && j == C - 1)
                return 1;
            return 0;
        }
        int next = 0;
        for (int k = 0; k < 4; k++) {
            int nextI = i + dx[k];
            int nextJ = j + dy[k];
            if (nextI < 0 || nextJ < 0 || nextJ >= C || nextI >= R) continue;
            if (visited[nextI][nextJ]) continue;
            if (map[i][j] == 'T') continue;
            visited[i][j] = true;
            next += solution(count + 1, nextI, nextJ, visited);
            visited[i][j] = false;
        }
        return next;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = bf.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        System.out.println(solution(1, R - 1, 0, new boolean[R][C]));
    }
}
