package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class Umbrella {
    //https://www.acmicpc.net/problem/17244
    //아맞다 우산
    static int N, M, count;
    static char[][] map;
    static int[] start = new int[2];
    static Map<String, Integer> hm = new HashMap<>();
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    static int solution() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start[0], start[1], 0});
        boolean[][][] visited = new boolean[M][N][1 << count];
        visited[start[0]][start[1]][0] = true;
        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] now = queue.poll();
                if (map[now[0]][now[1]] == 'E' && visited[now[0]][now[1]][(1 << count) - 1]) return cnt;
                for (int i = 0; i < 4; i++) {
                    int nextI = now[0] + dx[i];
                    int nextJ = now[1] + dy[i];
                    if(nextI<0||nextJ<0||nextI>=M||nextJ>=N) continue;
                    int vInfo = map[nextI][nextJ] == 'X' ? now[2] | (1 << hm.get(nextI + " " + nextJ)) : now[2];
                    if (visited[nextI][nextJ][vInfo]) continue;
                    if (map[nextI][nextJ] == '#') continue;
                    visited[nextI][nextJ][vInfo] = true;
                    queue.add(new int[]{nextI, nextJ, vInfo});
                }

            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        for (int i = 0; i < M; i++) {
            String s = bf.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') start = new int[]{i, j};
                if (map[i][j] == 'X') hm.put(i + " " + j, count++);
            }
        }
        System.out.println(solution());
    }
}
