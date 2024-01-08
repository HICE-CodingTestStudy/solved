package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FishingKing {
    //https://www.acmicpc.net/problem/17143
    //낚시왕
    static int R, C, M;
    static Shark[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int total = 0;

    static class Shark {
        int s;
        int d;
        int z;

        public Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }

        public void setD(int d) {
            this.d = d;
        }
    }

    static void fishing(int sec) {
        int r = 0;
        while (r < R && map[r][sec] == null) r++;
        if (r == R) return;
        total += map[r][sec].z;
        map[r][sec] = null;
    }

    static void move() {
        Shark[][] nextMap = new Shark[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == null) continue;
                int[] next = getNextLocation(i, j);
                //상어가 같은 칸에 도달하는 경우
                if (nextMap[next[0]][next[1]] != null) {
                    nextMap[next[0]][next[1]] =
                            nextMap[next[0]][next[1]].z > map[i][j].z ?
                                    nextMap[next[0]][next[1]] : map[i][j];
                    continue;
                }
                nextMap[next[0]][next[1]] = map[i][j];
            }
        }
        map = nextMap;
    }

    static int[] getNextLocation(int r, int c) {
        Shark s = map[r][c];
        int nextR = r + dx[s.d] * s.s;
        int nextC = c + dy[s.d] * s.s;

        //좌표가 안넘칠때까지 조정
        //넘친다면 방향도 바꿔줘야 함 -> 상<->하 (도합이 2), 좌<->우 (도합이 5)
        while (true) {
            if (nextR >= 0 && nextR < R) break;
            map[r][c].setD(map[r][c].d < 2 ? 1 - map[r][c].d : 5 - map[r][c].d);
            nextR = calcOutOfIndex(nextR, R);
        }
        while (true) {
            if (nextC >= 0 && nextC < C) break;
            map[r][c].setD(map[r][c].d < 2 ? 1 - map[r][c].d : 5 - map[r][c].d);
            nextC = calcOutOfIndex(nextC, C);
        }
        return new int[]{nextR, nextC};
    }

    //넘칠시 좌표 계산
    //왼쪽으로 넘침 -> 시작인덱스 + 넘친것 + 1
    //오른쪽으로 넘침 -> 끝인덱스 - (넘친것 -1)
    static int calcOutOfIndex(int next, int dimension) {
        if (next < 0) return -next;
        else return dimension - 1 - (next - (dimension - 1));
    }

    static void solution(int sec) {
        fishing(sec);
        move();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Shark[R][C];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1]
                    = new Shark(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < C; i++) {
            solution(i);
        }
        System.out.println(total);
    }

}
