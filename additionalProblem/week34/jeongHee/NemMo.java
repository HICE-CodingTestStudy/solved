package stack;

import java.util.Scanner;

public class NemMo {
    //https://www.acmicpc.net/problem/14712
    //넴모넴모
    static int N, M;
    static int ans = 0;

    static boolean isValid(int i, int j, int[][] map) {
        int count = map[i][j];
        if (i - 1 >= 0) {
            if (j - 1 >= 0)
                count += map[i - 1][j - 1];
            count += map[i - 1][j];
        }
        if (j - 1 >= 0)
            count += map[i][j - 1];
        return count < 4;
    }

    static int[] getNextCoordinate(int i, int j) {
        int nextI = i;
        int nextJ = j + 1;
        if (j + 1 >= M) {
            nextJ = 0;
            nextI = i + 1;
        }
        return new int[]{nextI, nextJ};
    }

    static void solution(int i, int j, int[][] map) {
        if (i == N-1 && j == M-1) {
            ans++;
            return;
        }
        int[] next = getNextCoordinate(i, j);
        if (isValid(next[0], next[1], map)) solution(next[0], next[1], map);
        map[next[0]][next[1]] = 1;
        if (isValid(next[0], next[1], map)) solution(next[0], next[1], map);
        map[next[0]][next[1]] = 0;
//        for (int k = 0; k < N; k++) {
//            for (int l = 0; l < M; l++) {
//                if (k < i || (k==i && l <= j)) continue;
//
//            }
//        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        solution(0, 0, new int[N][M]);
        int[][] map = new int[N][M];
        map[0][0] = 1;
        solution(0, 0, map);
        System.out.println(ans);
    }
}
