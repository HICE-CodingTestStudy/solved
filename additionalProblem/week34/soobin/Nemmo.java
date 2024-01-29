import java.io.*;

public class Nemmo {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static boolean[][] isNemmo;
    private static int N, M, numNonCombos;

    public static void main(String[] args) {
        parseInput();
        countNonCombos(0, 0);
        printAnswer();
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            isNemmo = new boolean[N][M];
        } catch (IOException ignored) {}
    }

    private static void countNonCombos(int row, int col) {
        if (row == N) {
            numNonCombos++;
            return;
        }

        int nextRow = col + 1 == M ? row + 1 : row;
        int nextCol = col + 1 == M ? 0 : col + 1;

        // (row, col) 칸에 넴모 O
        // 넴모를 올렸을 때 콤보가 안 만들어질 때만
        if (!isComboPossible(row, col)) {
            isNemmo[row][col] = true;
            countNonCombos(nextRow, nextCol);
            isNemmo[row][col] = false;
        }
        // (row, col) 칸에 넴모 X
        countNonCombos(nextRow, nextCol);
    }

    private static boolean isComboPossible(int row, int col) {
        return isBoxValid(row, col) && isBoxNemmo(row, col);
    }

    private static boolean isBoxValid(int row, int col) {
        return isValid(row - 1, col) && isValid(row, col - 1) && isValid(row - 1, col - 1);
    }

    private static boolean isBoxNemmo(int row, int col) {
        return isNemmo[row - 1][col]
                && isNemmo[row][col - 1]
                && isNemmo[row - 1][col - 1];
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }

    private static void printAnswer() {
        try {
            bw.write(String.valueOf(numNonCombos));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
