package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Zizin {
    //https://www.acmicpc.net/problem/31863
    //내진 설계
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int N, M;
    static char[][] map;
    static int[] start;
    static int all, broken;

    static void solution() {
        int[][] visited = new int[N][M];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            int ni = start[0] + dx[i];
            int nj = start[1] + dy[i];
            if(ni<0 || nj<0 || ni>=N || nj>=M) continue;
            if (map[ni][nj] == '|') continue;
            visited[ni][nj]++;
            if (map[ni][nj] == '*') {
                queue.add(new int[]{ni, nj});
                map[ni][nj] = '.';
                broken++;
            }

            int nni = start[0] + 2 * dx[i];
            int nnj = start[1] + 2 * dy[i];
            if(nni<0 || nnj<0 || nni>=N || nnj>=M) continue;
            if (map[nni][nnj] == '|') continue;
            visited[nni][nnj]++;
            if (map[nni][nnj]=='*') {
                queue.add(new int[]{nni, nnj});
                map[nni][nnj] = '.';
                broken++;
            }
        }
        map[start[0]][start[1]] = '.';
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextI = now[0] + dx[i];
                int nextJ = now[1] + dy[i];
                if(nextI<0 || nextJ<0 || nextI>=N || nextJ>=M) continue;
                visited[nextI][nextJ]++;
                if(map[nextI][nextJ]=='|') continue;
                if ((map[nextI][nextJ] == '*' && visited[nextI][nextJ] >= 1) ||
                        map[nextI][nextJ] == '#' && visited[nextI][nextJ] >= 2) {
                    map[nextI][nextJ] = '.';
                    queue.add(new int[]{nextI, nextJ});
                    broken++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '@') start = new int[]{i, j};
                if (map[i][j] == '*' || map[i][j] == '#') all++;
            }
        }
        solution();
        System.out.println(broken + " " + (all - broken));
    }
}
