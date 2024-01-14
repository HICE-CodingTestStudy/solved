package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GasPipe {
    //https://www.acmicpc.net/problem/2931
    //가스관
    static int R, C;
    static char[][] map;


    static boolean openUp(int i, int j) {
        if (i - 1 < 0) return false;
        switch (map[i - 1][j]) {
            case '|':
            case '+':
            case '1':
            case '4':
                return true;
            default:
                return false;
        }
    }

    static boolean openDown(int i, int j) {
        if (i + 1 >= R) return false;
        switch (map[i + 1][j]) {
            case '|':
            case '+':
            case '2':
            case '3':
                return true;
            default:
                return false;
        }
    }

    static boolean openLeft(int i, int j) {
        if (j - 1 < 0) return false;
        switch (map[i][j - 1]) {
            case '-':
            case '+':
            case '1':
            case '2':
                return true;
            default:
                return false;
        }
    }

    static boolean openRight(int i, int j) {
        if (j + 1 >= C) return false;
        switch (map[i][j + 1]) {
            case '-':
            case '+':
            case '3':
            case '4':
                return true;
            default:
                return false;
        }
    }

    static boolean isVertical(int i, int j) {
        if (openUp(i, j) && openDown(i, j) && !openLeft(i, j) && !openRight(i, j)) {
            System.out.println((i + 1) + " " + (j + 1) + " " + "|");
            return true;
        }
        return false;
    }

    static boolean isHorizon(int i, int j) {
        if (!openUp(i, j) && !openDown(i, j) && openLeft(i, j) && openRight(i, j)) {
            System.out.println((i + 1) + " " + (j + 1) + " " + "-");
            return true;
        }
        return false;
    }

    static boolean isCross(int i, int j) {
        if (openUp(i, j) && openDown(i, j) && openLeft(i, j) && openRight(i, j)) {
            System.out.println((i + 1) + " " + (j + 1) + " " + "+");
            return true;
        }
        return false;
    }

    static boolean isOne(int i, int j) {
        if (!openUp(i, j) && openDown(i, j) && !openLeft(i, j) && openRight(i, j)) {
            System.out.println((i + 1) + " " + (j + 1) + " " + "1");
            return true;
        }
        return false;
    }

    static boolean isTwo(int i, int j) {
        if (openUp(i, j) && !openDown(i, j) && !openLeft(i, j) && openRight(i, j)) {
            System.out.println((i + 1) + " " + (j + 1) + " " + "2");
            return true;
        }
        return false;
    }

    static boolean isThree(int i, int j) {
        if (openUp(i, j) && !openDown(i, j) && openLeft(i, j) && !openRight(i, j)) {
            System.out.println((i + 1) + " " + (j + 1) + " " + "3");
            return true;
        }
        return false;
    }

    static boolean isFour(int i, int j) {
        if (!openUp(i, j) && openDown(i, j) && openLeft(i, j) && !openRight(i, j)) {
            System.out.println((i + 1) + " " + (j + 1) + " " + "4");
            return true;
        }
        return false;
    }

    static void solution() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != '.') continue;
                if (isVertical(i, j) || isHorizon(i, j) || isCross(i, j)
                        || isOne(i, j) || isTwo(i, j) || isThree(i, j) || isFour(i, j))
                    return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = bf.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        solution();
    }
}
