package queue;

import java.util.Arrays;
import java.util.Scanner;

public class StampStar {
    //https://www.acmicpc.net/problem/10997
    //별 찍기
    static int N;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static void init() {
        Arrays.fill(map[0], '*');
        for (int i = 0; i < map.length; i++) {
            map[i][0] = '*';
        }
    }

    static void solution() {
        int startI = map.length - 1;
        int startJ = 0;
        int direction = 2;
        int loop = map[0].length;
        while (loop >= 3) {
            for (int i = 0; i < loop; i++) {
                map[startI][startJ] = '*';
                if (i != loop - 1) {
                    startI += dx[direction];
                    startJ += dy[direction];
                }
            }
            direction = direction == 3 ? 0 : direction + 1;
            for (int i = 0; i < loop; i++) {
                map[startI][startJ] = '*';
                if (i != loop - 1) {
                    startI += dx[direction];
                    startJ += dy[direction];
                }
            }
            direction = direction == 3 ? 0 : direction + 1;
            loop -= 2;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        if (N == 1) {
            System.out.print("*");
            return;
        }
        map = new char[4 * N - 1][4 * N - 3];
        init();
        solution();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != '*') {
                    if (i == 1) continue;
                    sb.append(" ");
                } else sb.append(map[i][j]);
            }
            if (i != map.length - 1) sb.append("\n");
        }
        System.out.println(sb);

    }
}
