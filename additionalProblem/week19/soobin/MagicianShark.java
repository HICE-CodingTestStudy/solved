package week19.soobin;

import java.io.*;
import java.util.*;

public class MagicianShark {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    // 대각선 : 2 * i + 1 (0 <= i <= 3)
    private static final int[][] moves = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

    private static Queue<int[]> cloudMoves;
    private static List<int[]> clouds;
    private static boolean[][] wasCloud;
    private static int[][] field;
    private static int N;

    private static void parseInput() {
        cloudMoves = new LinkedList<>();
        clouds = new ArrayList<>();

        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            wasCloud = new boolean[N][N];
            field = new int[N][N];

            // 격자 초기화
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    field[i][j] = Integer.parseInt(st.nextToken());
            }

            // 구름 움직임
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int direction = Integer.parseInt(st.nextToken()) - 1;
                int length = Integer.parseInt(st.nextToken());
                cloudMoves.add(new int[] {direction, length});
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void initializeClouds() {
        for (int i = N - 1; i >= N - 2; i--)
            for (int j = 0; j < 2; j++)
                clouds.add(new int[] {i, j});
    }

    private static int getNextPlace(int prev, int direction, int length, int type) {
        int next = prev + moves[direction][type] * (length % N);
        if (next < 0) return next + N;
        if (next >= N) return next - N;
        return next;
    }

    private static void moveCurrentClouds(int[] move) {
        for (int[] cloud : clouds) {
            int nextRow = getNextPlace(cloud[0], move[0], move[1], 0);
            int nextCol = getNextPlace(cloud[1], move[0], move[1], 1);
            field[nextRow][nextCol]++;
            wasCloud[nextRow][nextCol] = true;
            cloud[0] = nextRow;
            cloud[1] = nextCol;
        }
    }

    private static int doWaterCopyBug(int[] cloud) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int dir = 2 * i + 1;
            int row = cloud[0] + moves[dir][0];
            int col = cloud[1] + moves[dir][1];
            if (row < 0 || row >= N || col < 0 || col >= N) continue;

            if (field[row][col] > 0) count++;
        }
        return count;
    }

    private static void doWaterCopyBugForAllClouds() {
        List<Integer> rainAmounts = new ArrayList<>();

        for (int[] cloud : clouds) {
            int numCrossClouds = doWaterCopyBug(cloud);
            rainAmounts.add(numCrossClouds);
        }

        for (int i = 0; i < clouds.size(); i++) {
            int[] cloud = clouds.get(i);
            field[cloud[0]][cloud[1]] += rainAmounts.get(i);
        }

        clouds.clear();
    }

    private static void createNewClouds() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                if (wasCloud[i][j]) {
                    wasCloud[i][j] = false;
                    continue;
                }

                if (field[i][j] >= 2) {
                    clouds.add(new int[] {i, j});
                    field[i][j] -= 2;
                }
            }
    }

    private static void simulation() {
        while (!cloudMoves.isEmpty()) {
            int[] move = cloudMoves.poll();
            moveCurrentClouds(move);
            doWaterCopyBugForAllClouds();
            createNewClouds();
        }
    }

    private static int sumTotalWater() {
        int sum = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                sum += field[i][j];
        return sum;
    }

    public static void main(String[] args) {
        parseInput();
        initializeClouds();
        simulation();
        int answer = sumTotalWater();
        printOutput(answer);
    }
}
