package queue;

import java.util.Scanner;

public class MicroDust {
    //https://www.acmicpc.net/problem/17144
    //미세먼지 안녕
    static int[][] map;
    static int R;
    static int C;
    static int T;
    static int fresher;

    public static void diffusion() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int[][] afterDiffusion = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] <= 0) continue;
                int diffusionCount = 0;
                int diffusionAmount = map[i][j] / 5;
                for (int k = 0; k < 4; k++) {
                    int nextI = i + dx[k];
                    int nextJ = j + dy[k];
                    if (nextI < 0 || nextJ < 0 || nextI >= R || nextJ >= C) continue;
                    if (map[nextI][nextJ] == -1) continue;
                    diffusionCount++;
                    afterDiffusion[nextI][nextJ] += diffusionAmount;
                }
                map[i][j] -= diffusionCount * diffusionAmount;
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) continue;
                map[i][j] += afterDiffusion[i][j];
            }
        }
    }

    public static void moveDown(int j, int startI, int endI) {
        for (int i = endI; i > startI; i--) {
            map[i][j] = map[i - 1][j];
            map[i - 1][j] = 0;
        }
    }

    public static void moveUp(int j, int startI, int endI) {
        for (int i = endI; i < startI; i++) {
            map[i][j] = map[i + 1][j];
            map[i + 1][j] = 0;
        }
    }

    public static void moveLeft(int i, int startJ, int endJ) {
        for (int j = endJ; j < startJ; j++) {
            map[i][j] = map[i][j + 1];
            map[i][j + 1] = 0;
        }
    }

    public static void moveRight(int i, int startJ, int endJ) {
        for (int j = endJ; j > startJ; j--) {
            map[i][j] = map[i][j - 1];
            map[i][j - 1] = 0;
        }
    }

    public static void circular() {
        int upFresher = fresher - 1;
        int downFresher = fresher;
        moveDown(0, 0, upFresher - 1);
        moveUp(0, R - 1, downFresher + 1);
        moveLeft(0, C - 1, 0);
        moveLeft(R - 1, C - 1, 0);
        moveDown(C - 1, downFresher, R - 1);
        moveUp(C - 1, upFresher, 0);
        moveRight(upFresher, 1, C - 1);
        moveRight(downFresher, 1, C - 1);
    }

    public static int solution() {
        while (T > 0) {
            T--;
            diffusion();
            circular();
        }
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) continue;
                ans += map[i][j];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        T = sc.nextInt();
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == -1) fresher = i;
            }
        }
        System.out.println(solution());
    }
}
