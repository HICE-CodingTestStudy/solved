package week16.soobin;

import java.io.*;
import java.util.*;

public class Immigration {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    private static int[][] population;
    private static boolean[][][] isBorderLineOpened;
    private static boolean[][] visited;
    private static int N, L, R;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            population = new int[N][N];
            visited = new boolean[N][N];
            isBorderLineOpened = new boolean[N][N][4];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    population[i][j] = Integer.parseInt(st.nextToken());
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static boolean isOpeningCriteria(int r, int c, int or, int oc) {
        int populationGap = Math.abs(population[r][c] - population[or][oc]);
        return populationGap >= L && populationGap <= R;
    }

    private static List<int[]> findUnitedNations(int r, int c) {
        List<int[]> unitedNations = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        unitedNations.add(new int[] {r, c});
        queue.add(new int[] {r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            r = queue.peek()[0];
            c = queue.peek()[1];
            queue.poll();

            for (int i = 0; i < 4; i++) {
                int nr = r + moves[i][0], nc = c + moves[i][1];
                if (!isValid(nr, nc) || visited[nr][nc] || !isOpeningCriteria(r, c, nr, nc)) continue;

                visited[nr][nc] = true;
                unitedNations.add(new int[] {nr, nc});
                queue.add(new int[] {nr, nc});
            }
        }

        return unitedNations;
    }

    private static void immigrate(List<int[]> unitedNations) {
        int unitedPopulation = 0;
        for (int[] nation : unitedNations)
            unitedPopulation += population[nation[0]][nation[1]];

        unitedPopulation /= unitedNations.size();
        for (int[] nation : unitedNations)
            population[nation[0]][nation[1]] = unitedPopulation;
    }

    private static boolean processImmigration() {
        boolean canOpenBorderLine = false;

        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++) {
                if (visited[r][c]) continue;

                List<int[]> unitedNations = findUnitedNations(r, c);
                if (unitedNations.size() == 1) continue;

                canOpenBorderLine = true;
                immigrate(unitedNations);
            }

        return canOpenBorderLine;
    }

    private static void resetBorderLine() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
                Arrays.fill(isBorderLineOpened[i][j], false);
            }
    }

    public static void main(String[] args) {
        parseInput();
        int answer = 0;
        while (true) {
            boolean canImmigrate = processImmigration();
            if (!canImmigrate) break;

            answer++;
            resetBorderLine();
        }
        printOutput(answer);
    }
}
