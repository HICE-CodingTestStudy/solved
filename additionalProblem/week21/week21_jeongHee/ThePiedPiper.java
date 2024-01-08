package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ThePiedPiper {
    //https://www.acmicpc.net/problem/16724
    //피리 부는 사나이
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;

    static int charToIndex(char c) {
        if (c == 'U') return 0;
        if (c == 'D') return 1;
        if (c == 'L') return 2;
        return 3;
    }

    //해당 칸을 밟기 직전에 어느 칸을 밟고 올 수 있었는지를 확인함
    static boolean move(int i, int j, int from) {
        int beforeI = i + dx[from];
        int beforeJ = j + dy[from];
        return map[beforeI][beforeJ] + from == 1 || map[beforeI][beforeJ] + from == 5;
    }

    static int solution(int i, int j) {
        //이전에 이미 찾은 길에 속한 경우
        if (visited[i][j]) return 0;

        int[] now = new int[]{i, j};
        visited[i][j] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(now);
        //길을 찾아냄
        while (!queue.isEmpty()) {
            int[] next = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nextI = next[0] + dx[k];
                int nextJ = next[1] + dy[k];
                if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M)
                    continue;
                if (visited[nextI][nextJ]) continue;
                if (move(next[0], next[1], k)){
                    queue.add(new int[]{nextI, nextJ});
                    visited[nextI][nextJ] = true;
                }
                else if (k == map[next[0]][next[1]]) {
                    queue.add(new int[]{nextI, nextJ});
                    visited[nextI][nextJ] = true;
                }
            }
        }
        //새롭게 길을 찾은 경우이므로
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = charToIndex(s.charAt(j));
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans +=solution(i,j);
            }
        }
        System.out.println(ans);
    }

}
