package week24.soobin;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumOfRoads {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static long[][] memo;
    private static int[][] roadsUnderConstruction;
    private static int N, M;

    private static void addUnderConstructionRoad(int i, int x1, int y1, int x2, int y2) {
        roadsUnderConstruction[i][0] = x1;
        roadsUnderConstruction[i][1] = y1;
        roadsUnderConstruction[i][2] = x2;
        roadsUnderConstruction[i][3] = y2;
    }

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            memo = new long[N + 1][M + 1];
            for (int i = 0; i <= N; i++) Arrays.fill(memo[i], -1);

            int K = Integer.parseInt(br.readLine());
            roadsUnderConstruction = new int[K][4];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                addUnderConstructionRoad(i, x1, y1, x2, y2);
            }
        } catch (IOException e) {}
    }

    private static void printOutput(long answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isUnderConstruction(int x1, int y1, int x2, int y2) {
        for (int[] road : roadsUnderConstruction) {
            if (road[0] == x1 && road[1] == y1 && road[2] == x2 && road[3] == y2) return true;
            if (road[0] == x2 && road[1] == y2 && road[2] == x1 && road[3] == y1) return true;
        }
        return false;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x <= N && y >= 0 && y <= M;
    }

    private static long solution(int x, int y) {
        if (x == 0 && y == 0) return 1;

        if (memo[x][y] > -1) return memo[x][y];

        memo[x][y] = 0;
        if (isValid(x - 1, y) && !isUnderConstruction(x, y, x - 1, y)) // 왼쪽에서 오는 길
            memo[x][y] += solution(x - 1, y);
        if (isValid(x, y - 1) && !isUnderConstruction(x, y, x, y - 1)) // 아래에서 오는 길
            memo[x][y] += solution(x, y - 1);
        return memo[x][y];
    }

    public static void main(String[] args) {
        parseInput();
        long answer = solution(N, M);
        printOutput(answer);
    }
}
