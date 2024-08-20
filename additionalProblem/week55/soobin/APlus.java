import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class APlus {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[] dx = {1, 0, -1, 0, 1, 1, -1, -1};
    private static final int[] dy = {0, -1, 0, 1, 1, -1, 1, -1};

    private abstract static class Person {
        int r, c, d;

        Person(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        abstract void move();
        abstract void turn();
    }

    private static class Arie extends Person {
        private char move;

        public Arie(int r, int c, int d) {
            super(r, c, d);
        }

        public void setMove(char move) {
            this.move = move;
        }

        public void doSomething() {
            if (move == 'F') move();
            else turn();
        }

        public void move() {
            int nr = r + dx[d], nc = c + dy[d];
            if (isInvalid(nr, nc)) return;

            r = nr;
            c = nc;
        }

        public void turn() {
            d = move == 'L' ? (d + 3) % 4 : (d + 1) % 4;
        }
    }

    private static class Zombie extends Person {
        Zombie(int r, int c, int d) {
            super(r, c, d);
        }

        public void move() {
            int nr = r + dx[d], nc = c + dy[d];
            if (isInvalid(nr, nc)) turn();
            else {
                r = nr;
                c = nc;
            }
        }

        public void turn() {
            d = (d + 2) % 4;
        }
    }

    private static boolean[][] isLightOn, isSwitch, isZombie;
    private static Queue<Character> order;
    private static List<Zombie> zombies;
    private static Arie arie;
    private static int N;

    public static void main(String[] args) throws Exception {
        parseInput();
        String answer = solution();
        System.out.println(answer);
    }

    private static void parseInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        order = new ArrayDeque<>();
        for (char move : br.readLine().toCharArray()) {
            order.add(move);
        }
        arie = new Arie(0, 0, 0);

        isLightOn = new boolean[N][N];
        isSwitch = new boolean[N][N];
        isZombie = new boolean[N][N];
        zombies = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (line[j] == 'S') isSwitch[i][j] = true;
                if (line[j] == 'Z') {
                    isZombie[i][j] = true;
                    zombies.add(new Zombie(i, j, 0));
                }
            }
        }
    }

    private static String solution() {
        while (!order.isEmpty()) {
            char move = order.poll();
            arie.setMove(move);

            // 아리 행동
            arie.doSomething();
            int ar = arie.r, ac = arie.c;
            if (isSwitch[ar][ac]) {
                turnLightOn(ar, ac);
            }
            if (isZombie[ar][ac] && !isLightOn[ar][ac]) {
                return "Aaaaaah!";
            }

            // 좀비 이동
            moveZombies();
            if (isZombie[ar][ac] && !isLightOn[ar][ac]) {
                return "Aaaaaah!";
            }
        }
        return "Phew...";
    }

    private static void turnLightOn(int r, int c) {
        isLightOn[r][c] = true;
        for (int i = 0; i < 8; i++) {
            int nr = r + dx[i], nc = c + dy[i];
            if (isInvalid(nr, nc)) continue;
            isLightOn[nr][nc] = true;
        }
    }

    private static void moveZombies() {
        boolean[][] isZombieTemp = new boolean[N][N];

        for (Zombie zombie : zombies) {
            zombie.move();
            isZombieTemp[zombie.r][zombie.c] = true;
        }

        isZombie = isZombieTemp;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}
