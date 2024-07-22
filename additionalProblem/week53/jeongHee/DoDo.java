package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DoDo {
    //https://www.acmicpc.net/problem/22955
    //고양이 고고의 탈출기
    static int N, M;
    static int[] cat;
    static char[][] map;

    static class Info implements Comparable<Info> {
        int i, j;
        int cost;

        public Info(int i, int j, int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return cost - o.cost;
        }
    }

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < M && map[i][j] != 'D';
    }

    static int solution() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(cat[0], cat[1], 0));
        int[][] visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        visited[cat[0]][cat[1]] = 0;
        while (!pq.isEmpty()) {
            Info now = pq.poll();
            if (map[now.i][now.j] == 'E') return now.cost;
            // 뚫림
            if (map[now.i][now.j] == 'X') {
                int i = now.i;
                while (map[i][now.j] == 'X') i++;
                if (!isValid(i, now.j) || visited[i][now.j] <= now.cost + 10) continue;
                visited[i][now.j] = now.cost + 10;
                pq.add(new Info(i, now.j, now.cost + 10));
                continue;
            }
            // 좌우
            for (int i = -1; i < 2; i += 2) {
                int nextI = now.i;
                int nextJ = now.j + i;
                if (!isValid(nextI, nextJ) || visited[nextI][nextJ] <= now.cost + 1) continue;
                visited[nextI][nextJ] = now.cost + 1;
                pq.add(new Info(nextI, nextJ, now.cost + 1));
            }
            //사다리 (위)
            if (isValid(now.i - 1, now.j) && map[now.i][now.j] == 'L' && map[now.i - 1][now.j] != 'X') {
                int nextI = now.i - 1;
                int nextJ = now.j;
                if (isValid(nextI, nextJ) && visited[nextI][nextJ] > now.cost + 5) {
                    visited[nextI][nextJ] = now.cost + 5;
                    pq.add(new Info(nextI, nextJ, now.cost + 5));
                }
            }
            //사다리 (아래)
            if (isValid(now.i + 1, now.j) && map[now.i + 1][now.j] == 'L') {
                int nextI = now.i + 1;
                int nextJ = now.j;
                if (!isValid(nextI, nextJ) || visited[nextI][nextJ] <= now.cost + 5) continue;
                visited[nextI][nextJ] = now.cost + 5;
                pq.add(new Info(nextI, nextJ, now.cost + 5));
            }
        }
        return -1;
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
                if (map[i][j] == 'C') cat = new int[]{i, j};
            }
        }
        int ans = solution();
        System.out.println(ans == -1 ? "dodo sad" : ans);
    }
}
