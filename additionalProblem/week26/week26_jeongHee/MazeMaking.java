package stack;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MazeMaking {
    //https://www.acmicpc.net/problem/1347
    //미로 만들기
    static int N;
    static char[][] map;
    static ArrayList<Character> memo = new ArrayList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static void init() {
        map = new char[2 * N + 1][2 * N + 1];
        for (char[] chars : map) {
            Arrays.fill(chars, '#');
        }
    }

    static void move() {
        int direction = 0;
        int nextI = N;
        int nextJ = N;
        map[nextI][nextJ] = '.';
        for (Character next : memo) {
            if (next == 'L') {
                direction = direction == 0 ? 3 : direction - 1;
                continue;
            }
            if (next == 'R') {
                direction = direction == 3 ? 0 : direction + 1;
                continue;
            }
            nextI = nextI + dx[direction];
            nextJ = nextJ + dy[direction];
            map[nextI][nextJ] = '.';
        }
    }

    static void print() {
        int minI = 2 * N, minJ = 2 * N, maxI = 0, maxJ = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '.') {
                    minI = Math.min(minI, i);
                    minJ = Math.min(minJ, j);
                    maxI = Math.max(maxI, i);
                    maxJ = Math.max(maxJ, j);
                }
            }
        }
        for (int i = minI; i <= maxI; i++) {
            for (int j = minJ; j <= maxJ; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        String s = sc.next();
        for (int i = 0; i < N; i++) {
            memo.add(s.charAt(i));
        }
        init();
        move();
        print();
    }
}
