package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class InfiniteBooster {
    //https://www.acmicpc.net/problem/17391
    //무한 부스터
    static int N, M;
    static int[][] map;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    static class Coordinate {
        int i, j;
        int booster;
        int cost;

        public Coordinate(int i, int j, int booster, int cost) {
            this.i = i;
            this.j = j;
            this.booster = booster;
            this.cost = cost;
        }
    }

    static int solution() {
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(0, 0, map[0][0], 0));
        while (!queue.isEmpty()) {
            Coordinate c = queue.poll();
            if (c.i == N - 1 && c.j == M - 1)
                return c.cost;
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j <= c.booster ; j++) {
                    int nextI = c.i + j * dx[i];
                    int nextJ = c.j + j * dy[i];
                    if(nextI>=N || nextJ>=M){
                        if(nextI>=N) nextI = N-1;
                        if(nextJ>=M) nextJ = M-1;
                    }
                    if(visited[nextI][nextJ]) continue;
                    visited[nextI][nextJ] = true;
                    queue.add(new Coordinate(nextI,nextJ,map[nextI][nextJ],c.cost+1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution());

    }
}
