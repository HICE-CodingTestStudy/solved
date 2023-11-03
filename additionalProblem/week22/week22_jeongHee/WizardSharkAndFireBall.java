package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class WizardSharkAndFireBall {
    //https://www.acmicpc.net/problem/20056
    //마법사 상어와 파어어볼
    static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M, K;
    static int[][] map;
    static HashMap<String, List<Fireball>> hm = new HashMap<>();

    static class Fireball {
        int i, j, m, s, d;

        public Fireball(int i, int j, int m, int s, int d) {
            this.i = i;
            this.j = j;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public void setI(int i) {
            this.i = i;
        }

        public void setJ(int j) {
            this.j = j;
        }
    }

    static int[] getCoordinate(int i, int j, int d, int s) {
        int nextI = i + dx[d] * s;
        int nextJ = j + dy[d] * s;
        if (nextI < 0 || nextI >= N) {
            if (nextI < 0) {
                nextI = Math.abs(nextI) % N == 0 ? 0 : N - Math.abs(nextI) % N;
            } else nextI = nextI % N;
        }
        if (nextJ < 0 || nextJ >= N) {
            if (nextJ < 0) {
                nextJ = Math.abs(nextJ) % N == 0 ? 0 : N - Math.abs(nextJ) % N;
            } else nextJ = nextJ % N;
        }
        return new int[]{nextI, nextJ};
    }

    static void move() {
        HashMap<String, List<Fireball>> nextHm = new HashMap<>();
        for (String key : hm.keySet()) {
            List<Fireball> list = hm.get(key);
            for (Fireball fireball : list) {
                int[] next = getCoordinate(fireball.i, fireball.j, fireball.d, fireball.s);
                fireball.setI(next[0]);
                fireball.setJ(next[1]);
                nextHm.putIfAbsent(fireball.i + "," + fireball.j, new ArrayList<>());
                nextHm.get(fireball.i + "," + fireball.j).add(fireball);
            }
        }
        hm = nextHm;
    }

    static int getNextDirection(List<Fireball> list) {
        int d = list.get(0).d;
        for (Fireball fireball : list) {
            if (d % 2 != fireball.d % 2) return 1;
        }
        return 0;
    }

    static void separate() {
        HashMap<String, List<Fireball>> nextHm = new HashMap<>();
        for (String key : hm.keySet()) {
            if (hm.get(key).size() <= 1) {
                if (hm.get(key).size() == 1) {
                    nextHm.putIfAbsent(key, new ArrayList<>());
                    nextHm.get(key).addAll(hm.get(key));
                }
                continue;
            }
            List<Fireball> list = hm.get(key);
            List<Fireball> next = new ArrayList<>();
            int m = 0, s = 0;
            for (Fireball fireball : list) {
                m += fireball.m;
                s += fireball.s;
            }
            if (m / 5 == 0)
                continue;
            int i = getNextDirection(list);
            for (int j = 0; j < 4; j++, i += 2) {
                next.add(new Fireball(list.get(0).i, list.get(0).j, m / 5, s / list.size(), i));
            }
            nextHm.putIfAbsent(key, new ArrayList<>());
            nextHm.get(key).addAll(next);
        }
        hm = nextHm;
    }

    static int calcAns() {
        int ans = 0;
        for (List<Fireball> list : hm.values()) {
            for (Fireball fireball : list) {
                ans += fireball.m;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            hm.putIfAbsent((r + "," + c), new ArrayList<>());
            hm.get((r + "," + c)).add(new Fireball(r, c, m, s, d));
        }
        while (K-- > 0) {
            move();
            separate();
        }
        System.out.println(calcAns());
    }
}
