package additional;

import java.util.ArrayList;
import java.util.Scanner;

public class fiveMock {
    //https://www.acmicpc.net/problem/2615
    //오목
    static int[][] map = new int[19][19];
    static ArrayList<Coordinate> black = new ArrayList<>();
    static ArrayList<Coordinate> white = new ArrayList<>();
    static boolean winWhite = false;
    static boolean winBlack = false;
    static Coordinate winCoordinate;


    //위 아래 왼 오 왼위 오위 왼아 오아
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    static class Coordinate {
        int i;
        int j;

        public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void dfs(Coordinate start, int count, int direction) {
        int nextI = start.i + dx[direction];
        int nextJ = start.j + dy[direction];
        if (count == 5) {
            // 육목 제거 : 이전에 이어진게 5개 이상
            if (start.i - 5 * dx[direction] >= 0 && start.i - 5 * dx[direction] < 19
                    && start.j - 5 * dy[direction] >= 0 && start.j - 5 * dy[direction] < 19
                    && map[start.i - 5 * dx[direction]][start.j - 5 * dy[direction]] == map[start.i][start.j])
                return;

            // 가생이일때
            if (nextI < 0 || nextJ < 0 || nextJ >= 19 || nextI >= 19) {
                if (map[start.i][start.j] == 1)
                    winBlack = true;
                else
                    winWhite = true;
                winCoordinate = new Coordinate(start.i, start.j);
            }
            // 다음은 이어지지 않을때 (육목제거)
            else if (map[nextI][nextJ] != map[start.i][start.j]) {
                if (map[start.i][start.j] == 1)
                    winBlack = true;
                else winWhite = true;
                winCoordinate = new Coordinate(start.i, start.j);
            }
            return;
        }
        if (nextI < 0 || nextJ < 0 || nextJ >= 19 || nextI >= 19) return;
        if (map[nextI][nextJ] != map[start.i][start.j]) return;
        dfs(new Coordinate(nextI, nextJ), count + 1, direction);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) black.add(new Coordinate(i, j));
                if (map[i][j] == 2) white.add(new Coordinate(i, j));
            }
        }
        for (Coordinate coordinate : black) {
            if (winBlack || winWhite) break;
            dfs(coordinate, 1, 0);
            dfs(coordinate, 1, 2);
            dfs(coordinate, 1, 4);
            dfs(coordinate, 1, 6);
        }
        for (Coordinate coordinate : white) {
            if (winBlack || winWhite) break;
            dfs(coordinate, 1, 0);
            dfs(coordinate, 1, 2);
            dfs(coordinate, 1, 4);
            dfs(coordinate, 1, 6);
        }
        if (!winWhite && !winBlack) {
            System.out.println(0);
            return;
        }
        if (winWhite) System.out.println(2);
        else System.out.println(1);
        System.out.println((winCoordinate.i + 1) + " " + (winCoordinate.j + 1));
    }
}
