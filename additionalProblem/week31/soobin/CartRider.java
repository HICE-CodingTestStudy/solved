package soobin;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CartRider {
    private static class Status {
        int r, c, boost, item;

        Status(int r, int c, int boost, int item) {
            this.r = r;
            this.c = c;
            this.boost = boost;
            this.item = item;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {0, 1}};

    private static int[][] map;
    private static int N, M;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }
        } catch (IOException e) {}
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static int solution() {
        Queue<Status> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        queue.add(new Status(0, 0, map[0][0], 1));
        visited[0][0] = true;

        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Status cur = queue.poll();

            for (int b = 1; b <= cur.boost; b++) {
                for (int[] move : moves) {
                    int nr = cur.r + move[0] * b, nc = cur.c + move[1] * b;
                    if (!isValid(nr, nc) || visited[nr][nc]) continue;

                    if (nr == N - 1 && nc == M - 1) answer = Math.min(answer, cur.item);

                    visited[nr][nc] = true;
                    queue.add(new Status(nr, nc, map[nr][nc],cur.item + 1));
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = solution();
        printAnswer(answer);
    }
}
