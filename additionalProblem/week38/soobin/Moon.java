import java.io.*;
import java.util.Arrays;

public class Moon {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int LEFT = 0, STRAIGHT = 1, RIGHT = 2;
    private static final int MAX = 1000000;

    private static int[][][] fuelCost;
    private static int N, M;

    public static void main(String[] args) {
        parseInput();
        calcFuelCosts();
        int minCost = findMinCost();
        printAnswer(minCost);
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);

            fuelCost = new int[N + 1][M][3];
            for (int i = 0; i < N; i++) {
                input = br.readLine().split(" ");
                for (int j = 0; j < M; j++) {
                    int fuel = Integer.parseInt(input[j]);
                    Arrays.fill(fuelCost[i][j], fuel);
                }
            }
        } catch (IOException ignored) {}
    }

    private static void calcFuelCosts() {
        for (int n = 1; n < N; n++) {
            for (int m = 0; m < M; m++) {
                fuelCost[n][m][LEFT] += m - 1 >= 0
                        ? Math.min(fuelCost[n - 1][m][STRAIGHT], fuelCost[n - 1][m - 1][RIGHT])
                        : fuelCost[n - 1][m][STRAIGHT];
                fuelCost[n][m][STRAIGHT] += Math.min(
                        m + 1 < M ? fuelCost[n - 1][m + 1][LEFT] : MAX,
                        m - 1 >= 0 ? fuelCost[n - 1][m - 1][RIGHT] : MAX
                );
                fuelCost[n][m][RIGHT] += m + 1 < M
                        ? Math.min(fuelCost[n - 1][m][STRAIGHT], fuelCost[n - 1][m + 1][LEFT])
                        : fuelCost[n - 1][m][STRAIGHT];
            }
        }
    }

    private static int findMinCost() {
        int minCost = MAX;
        for (int m = 0; m < M; m++) {
            int tempMin = Math.min(fuelCost[N - 1][m][LEFT], fuelCost[N - 1][m][STRAIGHT]);
            tempMin = Math.min(tempMin, fuelCost[N - 1][m][RIGHT]);
            minCost = Math.min(minCost, tempMin);
        }

        return minCost;
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
