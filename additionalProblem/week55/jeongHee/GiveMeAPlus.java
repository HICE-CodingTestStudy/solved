package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class GiveMeAPlus {
    //https://www.acmicpc.net/problem/23031
    //으어어 에이쁠주세요
    static final String YES = "Phew...", NO = "Aaaaaah!";
    static final int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    static char[][] map;
    static boolean[][] light;
    static Queue<int[]> zombie = new ArrayDeque<>();
    static int N;
    static String S;

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < N;
    }

    static int getDirection(int i) {
        return (i + 4) % 4;
    }

    static void turnOn(int i, int j) {
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (!isValid(k, l)) continue;
                light[k][l] = true;
            }
        }
    }

    static boolean moveZombie(int[] me) {
        int T = zombie.size();
        for (int i = 0; i < T; i++) {
            int[] z = zombie.poll();
            if(!light[z[0]][z[1]] && me[0] == z[0] && me[1] == z[1]) return false;
            int nextI = z[0] + dx[z[2]];
            int nextJ = z[1] + dy[z[2]];
            if (!isValid(nextI, nextJ)) {
                zombie.add(new int[]{z[0], z[1], getDirection(z[2] - 2)});
                if (!light[z[0]][z[1]] && me[0] == z[0] && me[1] == z[1])
                    return false;
                continue;
            }
            zombie.add(new int[]{nextI, nextJ, z[2]});
            if (!light[nextI][nextJ] && me[0] == nextI && me[1] == nextJ)
                return false;
        }
        return true;
    }

    static boolean solution() {
        int[] me = {0, 0, 0};
        for (int i = 0; i < S.length(); i++) {
            switch (S.charAt(i)) {
                case 'F':
                    int nextI = me[0] + dx[me[2]];
                    int nextJ = me[1] + dy[me[2]];
                    if(isValid(nextI,nextJ)) me = new int[]{nextI, nextJ, me[2]};
                    if(map[me[0]][me[1]]=='S') {
                        turnOn(me[0], me[1]);
                        map[me[0]][me[1]] = 'O';
                    }
                    if(!moveZombie(me)) return false;
                    break;
                case 'R':
                    me = new int[]{me[0], me[1], getDirection(me[2] + 1)};
                    if(!moveZombie(me)) return false;
                    break;
                case 'L':
                    me = new int[]{me[0], me[1], getDirection(me[2] - 1)};
                    if(!moveZombie(me)) return false;
                    break;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        S = bf.readLine();
        map = new char[N][N];
        light = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'Z') zombie.add(new int[]{i, j, 0});
            }
        }
        System.out.println(solution() ? YES : NO);
    }
}
