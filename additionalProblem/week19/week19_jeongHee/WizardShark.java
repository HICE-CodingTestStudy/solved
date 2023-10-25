package additional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class WizardShark {
    //https://www.acmicpc.net/problem/21610
    //마법사 상어와 비바라기
    static int N, M;
    static int[][] map;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static HashSet<Cloud> clouds = new HashSet<>();

    static class Cloud {
        int i;
        int j;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Cloud) {
                return i == ((Cloud) obj).i && j == ((Cloud) obj).j;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i,j);
        }

        public Cloud(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static int correctCoordinate(int i) {
        while (true) {
            if (i >= 0 && i < N) return i;
            else if (i < 0) {
                i = N + i;
            } else {
                i = i - N;
            }
        }
    }

    static void raining(int i, int j) {
        map[i][j]++;
    }

    static void duplicateWaterBug(Cloud c) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int d = 2 * i + 1;
            int nextI = c.i + dx[d];
            int nextJ = c.j + dy[d];
            if (nextI < 0 || nextJ < 0 || nextJ >= N || nextI >= N) continue;
            if (map[nextI][nextJ] != 0) count++;
        }
        map[c.i][c.j] += count;
    }

    static Cloud move(int d, int s, Cloud c) {
        int nextI = c.i + dx[d] * s;
        int nextJ = c.j + dy[d] * s;
        return new Cloud(correctCoordinate(nextI), correctCoordinate(nextJ));
    }

    static void solution(int d, int s) {
        HashSet<Cloud> nextClouds = new HashSet<>();
        for (Cloud cloud : clouds) {
            Cloud c = move(d, s, cloud);
            nextClouds.add(c);
            raining(c.i, c.j);
        }
        for (Cloud nextCloud : nextClouds) {
            duplicateWaterBug(nextCloud);
        }
        clouds.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Cloud c = new Cloud(i, j);
                if (nextClouds.contains(c)) continue;
                if (map[i][j] >= 2) {
                    clouds.add(c);
                    map[i][j]-=2;
                }
            }
        }
    }

    static int countAns() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans+=map[i][j];
            }
        }
        return ans;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        clouds.add(new Cloud(N-1,0));
        clouds.add(new Cloud(N-1,1));
        clouds.add(new Cloud(N-2,0));
        clouds.add(new Cloud(N-2,1));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            solution(d - 1, s);
        }
        System.out.println(countAns());
    }
}
