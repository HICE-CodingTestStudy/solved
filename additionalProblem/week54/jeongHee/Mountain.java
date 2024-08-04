package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class Mountain {
    //https://www.acmicpc.net/problem/1486
    //등산
    static int N, M, T, D;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int[][] map;
    static int[][] dist;

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
        return i >= 0 && j >= 0 && i < N && j < M;
    }

    static int change(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 26;
        }
        return c - 'A';
    }

    static int calcDiff(int from, int to) {
        if (from >= to) return 1;
        return (to - from) * (to - from);
    }

    static int[][] calcDist(int startI, int startJ) {
        Queue<Info> queue = new ArrayDeque<>();
        queue.add(new Info(startI, startJ, 0));
        int[][] visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        visited[startI][startJ] = 0;
        while (!queue.isEmpty()) {
            Info now = queue.poll();
            if (visited[now.i][now.j] < now.cost) continue;
            for (int i = 0; i < 4; i++) {
                int nextI = now.i + dx[i];
                int nextJ = now.j + dy[i];
                if (!isValid(nextI, nextJ)) continue;
                if (Math.abs(map[now.i][now.j] - map[nextI][nextJ]) > T) continue;
                int time = calcDiff(map[now.i][now.j], map[nextI][nextJ]);
                if (visited[nextI][nextJ] <= time + now.cost) continue;
                if (time + now.cost > D) continue;
                visited[nextI][nextJ] = time + now.cost;
                queue.add(new Info(nextI, nextJ, time + now.cost));
            }
        }
        return visited;
    }

    static int solution() {
        int[][] start = calcDist(0, 0);
        int ans = map[0][0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 && j == 0) continue;
                if (start[i][j] == Integer.MAX_VALUE) continue;
                int[][] end = calcDist(i, j);
                if (end[0][0] == Integer.MAX_VALUE) continue;
                if (end[0][0] + start[i][j] > D) continue;
                ans = Math.max(ans, map[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = change(s.charAt(j));
            }
        }
        System.out.println(solution());

    }
}
