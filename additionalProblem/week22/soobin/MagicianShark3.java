package week22.soobin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MagicianShark3 {
    private static class FireBall {
        int row, col, mass, direction, speed;

        FireBall(int row, int col, int mass, int speed, int direction) {
            this.row = row;
            this.col = col;
            this.mass = mass;
            this.direction = direction;
            this.speed = speed;
        }

        public void setNextLocation(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    private static List<FireBall>[][] map;
    private static int N, K;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new List[N][N];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                int mass = Integer.parseInt(st.nextToken());
                int speed = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());

                if (map[r][c] == null) map[r][c] = new ArrayList<>();
                map[r][c].add(new FireBall(r, c, mass, speed, direction));
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int getNextPlace(FireBall fireBall, int type) {
        int prev = type == 0 ? fireBall.row : fireBall.col;
        int next = prev + moves[fireBall.direction][type] * (fireBall.speed % N);
        if (next < 0) return next + N;
        if (next >= N) return next - N;
        return next;
    }

    private static void moveFireBalls() {
        List[][] movedMap = new List[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null || map[i][j].size() == 0) continue;

                for (FireBall fireBall : map[i][j]) {
                    int nextRow = getNextPlace(fireBall, 0);
                    int nextCol = getNextPlace(fireBall, 1);
                    fireBall.setNextLocation(nextRow, nextCol);

                    if (movedMap[nextRow][nextCol] == null)
                        movedMap[nextRow][nextCol] = new ArrayList();
                    movedMap[nextRow][nextCol].add(fireBall);
                }
            }

        map = movedMap;
    }

    private static List<FireBall> splitFireBall(int row, int col, int mass, int speed, boolean isDirAllEvenOrOdd) {
        List<FireBall> newFireBalls = new ArrayList<>();
        int direction = isDirAllEvenOrOdd ? 0 : 1;
        for (int i = 0; i < 4; i++, direction += 2)
            newFireBalls.add(new FireBall(row, col, mass, speed, direction));

        return newFireBalls;
    }

    private static void mergeFireBalls() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null || map[i][j].size() < 2) continue;

                int totalMass = 0, totalSpeed = 0;
                boolean isDirAllEvenOrOdd = true;
                List<FireBall> loc = map[i][j];

                boolean isDirEven = loc.get(0).direction % 2 == 0;
                for (FireBall fireBall : loc) {
                    totalMass += fireBall.mass;
                    totalSpeed += fireBall.speed;

                    if (isDirEven != (fireBall.direction % 2 == 0)) isDirAllEvenOrOdd = false;
                }

                int nextMass = Math.floorDiv(totalMass, 5);
                int nextSpeed = Math.floorDiv(totalSpeed, loc.size());

                loc.clear();
                map[i][j] = nextMass == 0 ? loc : splitFireBall(i, j, nextMass, nextSpeed, isDirAllEvenOrOdd);
            }
    }

    private static int sumTotalMass() {
        int totalMass = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null) continue;
                for (FireBall fireBall : map[i][j])
                    totalMass += fireBall.mass;
            }
        return totalMass;
    }

    private static int simulation() {
        while (K-- > 0) {
            moveFireBalls();
            mergeFireBalls();
        }
        return sumTotalMass();
    }

    public static void main(String[] args) {
        parseInput();
        int answer = simulation();
        printOutput(answer);
    }
}
