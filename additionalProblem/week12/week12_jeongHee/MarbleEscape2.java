package algoStudy.week1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MarbleEscape2 {
    //https://www.acmicpc.net/problem/13460
    //구슬 탈출2
    static int N;
    static int M;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][][][] visited;

    static class Coordinate {
        int redI = -1;
        int redJ = -1;
        int blueI = -1;
        int blueJ = -1;
        boolean valid = true;
        int lastDirection = -1;

        public Coordinate(int redI, int redJ, int blueI, int blueJ) {
            this.redI = redI;
            this.redJ = redJ;
            this.blueI = blueI;
            this.blueJ = blueJ;
        }

        public Coordinate() {
            this.valid = false;
        }
    }

    public static boolean isValid(int[] blue, int direction) {
        while (true) {
            if (map[blue[0]][blue[1]] == '#') return true;
            if (map[blue[0]][blue[1]] == 'O') return false;
            blue[0] += dx[direction];
            blue[1] += dy[direction];
        }
    }

    public static Coordinate move(Coordinate start, int direction) {
        Coordinate c = new Coordinate(start.redI, start.redJ, start.blueI, start.blueJ);
        c.lastDirection = direction;
        while (true) {
            //성공
            if (map[c.redI + dx[direction]][c.redJ + dy[direction]] == 'O') {
                if (!isValid(new int[]{c.blueI,c.blueJ},direction))
                    return new Coordinate();
                return new Coordinate(c.redI + dx[direction], c.redJ + dy[direction], c.blueI + dx[direction], c.blueJ + dy[direction]);
            }
            //실패 : 파란공 탈출
            if (map[c.blueI + dx[direction]][c.blueJ + dy[direction]] == 'O')
                return new Coordinate();
            //더이상 이동불가 (둘다 벽)
            if (map[c.redI + dx[direction]][c.redJ + dy[direction]] == '#' && map[c.blueI + dx[direction]][c.blueJ + dy[direction]] == '#')
                return c;

            //이동
            Coordinate tmpC = new Coordinate(c.redI, c.redJ, c.blueI, c.blueJ);
            if (map[c.redI + dx[direction]][c.redJ + dy[direction]] != '#') {
                tmpC.redI += dx[direction];
                tmpC.redJ += dy[direction];
            }
            if (map[c.blueI + dx[direction]][c.blueJ + dy[direction]] != '#') {
                tmpC.blueI += dx[direction];
                tmpC.blueJ += dy[direction];
            }

            // 이동했는데 겹침
            if (tmpC.redI == tmpC.blueI && tmpC.redJ == tmpC.blueJ)
                return c;
            c = tmpC;
        }
    }

    public static int solution(Coordinate start) {
        Queue<Coordinate> queue = new LinkedList<>();
        visited[start.redI][start.redJ][start.blueI][start.blueJ] = true;
        queue.add(start);
        int ans = 0;
        while (!queue.isEmpty()) {
            if (ans > 10) return -1;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Coordinate c = queue.poll();
                if (map[c.redI][c.redJ] == 'O') return ans;
                for (int j = 0; j < 4; j++) {
                    if (c.lastDirection == j) continue;
                    Coordinate next = move(c, j);
                    if (!next.valid) continue;
                    if (visited[next.redI][next.redJ][next.blueI][next.blueJ]) continue;
                    queue.add(next);
                    visited[next.redI][next.redJ][next.blueI][next.blueJ] = true;
                }
            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        Coordinate c = new Coordinate(-1, -1, -1, -1);
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'B') {
                    c.blueI = i;
                    c.blueJ = j;
                }
                if (map[i][j] == 'R') {
                    c.redI = i;
                    c.redJ = j;
                }
            }
        }
        System.out.println(solution(c));
    }
}
