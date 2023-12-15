import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PuyoPuyo {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static List<Character>[] tempField = new List[6];
    private static char[][] field = new char[12][6];

    private static void parseInput() {
        try {
            for (int i = 0; i < 12; i++) {
                String line = br.readLine();
                for (int j = 0; j < 6; j++)
                    field[i][j] = line.charAt(j);
            }

            for (int i = 0; i < 6; i++) {
                tempField[i] = new ArrayList<>();
                for (int j = 11; j >= 0; j--)
                    tempField[i].add(field[j][i]);
            }
        } catch(IOException e) {}
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < 12 && c >= 0 && c < 6;
    }

    private static List<int[]> isPuyoable(int r, int c, char color) {
        List<int[]> chain = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[12][6];
        int[] start = new int[] {r, c};
        visited[r][c] = true;
        queue.add(start);
        chain.add(start);

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int[] move : moves) {
                int nr = cur[0] + move[0], nc = cur[1] + move[1];
                if (!isValid(nr, nc) || visited[nr][nc] || field[nr][nc] != color) continue;

                int[] next = new int[] {nr, nc};
                visited[nr][nc] = true;
                queue.add(next);
                chain.add(next);
            }
        }

        return chain;
    }

    private static void popPuyo(List<int[]> chain) {
        for (int[] puyo : chain) field[puyo[0]][puyo[1]] = '.';
    }

    private static boolean popAllPuyo() {
        boolean isPuyoable = false;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (field[i][j] == '.') continue;

                List<int[]> chain = isPuyoable(i, j, field[i][j]);
                if (chain.size() >= 4) {
                    isPuyoable = true;
                    popPuyo(chain);
                }
            }
        }

        return isPuyoable;
    }

    private static void downPuyos() {
        for (int i = 0; i < 6; i++) {
            Queue<Character> queue = new LinkedList<>();
            for (int j = 11; j >= 0; j--) {
                if (field[j][i] == '.') continue;
                queue.add(field[j][i]);
            }

            tempField[i].clear();
            while (!queue.isEmpty()) tempField[i].add(queue.poll());
            while (tempField[i].size() < 12) tempField[i].add('.');
        }
    }

    private static void sync() {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 12; j++)
                field[j][i] = tempField[i].get(11 - j);
    }

    private static int simulation() {
        int combo = 0;

        while (true) {
            boolean isPuyoable = popAllPuyo();
            if (!isPuyoable) break;

            combo++;
            downPuyos();
            sync();
        }

        return combo;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = simulation();
        printAnswer(answer);
    }
}
