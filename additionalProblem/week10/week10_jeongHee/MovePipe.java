package queue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MovePipe {
    //https://www.acmicpc.net/problem/17069
    //파이프 옮기기 2
    static int ans = 0;
    static int[][] map;
    static int N;

    //
//    static class Pipe {
//        int[] start;
//        int[] end;
//        //0 가로 1 세로 2 대각선
//        int status;
//
//        public Pipe(int[] start, int[] end) {
//            this.start = start;
//            this.end = end;
//            if (start[0] == end[0]) this.status = 0;
//            else if (start[1] == end[1]) this.status = 1;
//            else this.status = 2;
//        }
//    }
//
//    public static void bfs() {
//        Queue<Pipe> queue = new LinkedList<>();
//        queue.add(new Pipe(new int[]{0, 0}, new int[]{0, 1}));
//        boolean[][][][] visited = new boolean[N][N][N][N];
//        boolean[][][][] dp = new boolean[N][N][N][N];
//        visited[0][0][0][1] = true;
//        while (!queue.isEmpty()) {
//            Pipe p = queue.poll();
//            if (p.status == 0) {
//                if (p.end[1] + 1 < N) {
//                    if (map[p.end[0]][p.end[1] + 1] == 1) continue;
//                    if (p.end[0] == N - 1 && p.end[1] + 1 == N - 1) {
//                        ans++;
//                        continue;
//                    }
//                    if (visited[p.end[0]][p.end[1]][p.end[0]][p.end[1] + 1]) continue;
//                    visited[p.end[0]][p.end[1]][p.end[0]][p.end[1] + 1] = true;
//                    queue.add(new Pipe(new int[]{p.end[0], p.end[1]}, new int[]{p.end[0], p.end[1] + 1}));
//                }
//                if (p.end[1] + 1 < N && p.end[0] + 1 < N) {
//                    if (map[p.end[0] + 1][p.end[1] + 1] == 1) continue;
//                    if (p.end[0] + 1 == N - 1 && p.end[1] + 1 == N - 1) {
//                        ans++;
//                        continue;
//                    }
//                    if (visited[p.end[0]][p.end[1]][p.end[0] + 1][p.end[1] + 1]) continue;
//                    visited[p.end[0]][p.end[1]][p.end[0] + 1][p.end[1] + 1] = true;
//                    queue.add(new Pipe(new int[]{p.end[0], p.end[1]}, new int[]{p.end[0] + 1, p.end[1] + 1}));
//                }
//            } else if (p.status == 1) {
//                if (p.end[0] + 1 < N) {
//                    if (map[p.end[0] + 1][p.end[1]] == 1) continue;
//                    if (p.end[1] == N - 1 && p.end[0] + 1 == N - 1) {
//                        ans++;
//                        continue;
//                    }
//                    if (visited[p.end[0]][p.end[1]][p.end[0] + 1][p.end[1]]) continue;
//                    visited[p.end[0]][p.end[1]][p.end[0] + 1][p.end[1]] = true;
//                    queue.add(new Pipe(new int[]{p.end[0], p.end[1]}, new int[]{p.end[0] + 1, p.end[1]}));
//                }
//                if (p.end[1] + 1 < N && p.end[0] + 1 < N) {
//                    if (map[p.end[0] + 1][p.end[1] + 1] == 1) continue;
//                    if (p.end[0] + 1 == N - 1 && p.end[1] + 1 == N - 1) {
//                        ans++;
//                        continue;
//                    }
//                    if (visited[p.end[0]][p.end[1]][p.end[0] + 1][p.end[1] + 1]) continue;
//                    visited[p.end[0]][p.end[1]][p.end[0] + 1][p.end[1] + 1] = true;
//                    queue.add(new Pipe(new int[]{p.end[0], p.end[1]}, new int[]{p.end[0] + 1, p.end[1] + 1}));
//                }
//            } else {
//                if (p.end[1] + 1 < N) {
//                    if (map[p.end[0]][p.end[1] + 1] == 1) continue;
//                    if (p.end[0] == N - 1 && p.end[1] + 1 == N - 1) {
//                        ans++;
//                        continue;
//                    }
//                    if (visited[p.end[0]][p.end[1]][p.end[0]][p.end[1] + 1]) continue;
//                    visited[p.end[0]][p.end[1]][p.end[0]][p.end[1] + 1] = true;
//                    queue.add(new Pipe(new int[]{p.end[0], p.end[1]}, new int[]{p.end[0], p.end[1] + 1}));
//                }
//                if (p.end[0] + 1 < N) {
//                    if (map[p.end[0] + 1][p.end[1]] == 1) continue;
//                    if (p.end[1] == N - 1 && p.end[0] + 1 == N - 1) {
//                        ans++;
//                        continue;
//                    }
//                    if (visited[p.end[0]][p.end[1]][p.end[0] + 1][p.end[1]]) continue;
//                    visited[p.end[0]][p.end[1]][p.end[0] + 1][p.end[1]] = true;
//                    queue.add(new Pipe(new int[]{p.end[0], p.end[1]}, new int[]{p.end[0] + 1, p.end[1]}));
//                }
//
//                if (p.end[1] + 1 < N && p.end[0] + 1 < N) {
//                    if (map[p.end[0] + 1][p.end[1] + 1] == 1) continue;
//                    if (p.end[0] + 1 == N - 1 && p.end[1] + 1 == N - 1) {
//                        ans++;
//                        continue;
//                    }
//                    if (visited[p.end[0]][p.end[1]][p.end[0] + 1][p.end[1] + 1]) continue;
//                    visited[p.end[0]][p.end[1]][p.end[0] + 1][p.end[1] + 1] = true;
//                    queue.add(new Pipe(new int[]{p.end[0], p.end[1]}, new int[]{p.end[0] + 1, p.end[1] + 1}));
//                }
//            }
//        }
//    }
    public static long dp(long[][][][] pipe, int startI, int startJ, int endI, int endJ) {
        if (startI == 0 && startJ == 0 && endI == 0 && endJ == 1) return 1;
        if (startI < 0 || startJ < 0 || endI < 0 || endJ < 0) return 0;
        if (pipe[startI][startJ][endI][endJ] != -1) return pipe[startI][startJ][endI][endJ];
        if (startI == endI && startJ != endJ) {
            if (map[endI][endJ] == 1) {
                pipe[startI][startJ][endI][endJ] = 0;
                return 0;
            }
            pipe[startI][startJ][endI][endJ] = dp(pipe, startI, startJ - 1, endI, endJ - 1) +
                    dp(pipe, startI - 1, startJ - 1, endI, endJ - 1);
        } else if (startI != endI && startJ == endJ) {
            if (map[endI][endJ] == 1) {
                pipe[startI][startJ][endI][endJ] = 0;
                return 0;
            }
            pipe[startI][startJ][endI][endJ] = dp(pipe, startI - 1, startJ, endI - 1, endJ) +
                    dp(pipe, startI - 1, startJ - 1, endI - 1, endJ);
        } else {
            if (map[endI][endJ] == 1 || map[startI][endJ] == 1 || map[endI][startJ] == 1) {
                pipe[startI][startJ][endI][endJ] = 0;
                return 0;
            }
            pipe[startI][startJ][endI][endJ] = dp(pipe, startI, startJ - 1, endI - 1, endJ - 1) +
                    dp(pipe, startI - 1, startJ, endI - 1, endJ - 1) +
                    dp(pipe, startI - 1, startJ - 1, endI - 1, endJ - 1);
        }
        return pipe[startI][startJ][endI][endJ] == -1 ? 0 : pipe[startI][startJ][endI][endJ];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
//        int[][] dp = new int[N][N];
//        Arrays.fill(dp[0], 1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        long[][][][] dp = new long[N][N][N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        long fist = dp(dp, N - 2, N - 2, N - 1, N - 1);
        dp = new long[N][N][N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        long second = dp(dp, N - 2, N - 1, N - 1, N - 1);
        dp = new long[N][N][N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        long third = dp(dp, N - 1, N - 2, N - 1, N - 1);
        System.out.println(fist + second + third);
    }
}
