import java.io.*;
import java.util.*;

public class Robot {
    private static class Cell {
        boolean isRobot;
        int durability;

        Cell(boolean isRobot, int durability) {
            this.isRobot = isRobot;
            this.durability = durability;
        }

        public void removeRobot() {
            this.isRobot = false;
        }

        public void addRobot() {
            this.isRobot = true;
            this.durability--;
            if (this.durability == 0) nonDurability++;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Cell> belts;
    private static int N, K, nonDurability;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            belts = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 2 * N; i++) {
                int durability = Integer.parseInt(st.nextToken());
                belts.add(new Cell(false, durability));
            }
        } catch(IOException e) {}
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void moveCell() {
        Cell last = belts.remove(2 * N - 1);
        belts.add(0, last);

        Cell off = belts.get(N - 1);
        if (off.isRobot) off.removeRobot();
    }

    private static void moveRobot() {
        for (int i = N - 1; i >= 0; i--) {
            Cell cell = belts.get(i);
            if (!cell.isRobot) continue;

            Cell next = belts.get(i + 1);
            if (next.isRobot || next.durability == 0) continue;

            cell.removeRobot();
            next.addRobot();
            if (i + 1 == N - 1) next.removeRobot();
        }
    }

    private static void addRobot() {
        Cell first = belts.get(0);
        if (first.durability > 0) first.addRobot();
    }

    private static int simulation() {
        int level = 0;

        while (nonDurability < K) {
            moveCell();
            moveRobot();
            addRobot();
            level++;
        }

        return level;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = simulation();
        printAnswer(answer);
    }
}
