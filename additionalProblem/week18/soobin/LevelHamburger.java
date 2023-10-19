package week18.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class LevelHamburger {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static long[] burgerSize, totalPatty;
    private static int N;
    private static long X;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Long.parseLong(st.nextToken());

            burgerSize = new long[N + 1];
            totalPatty = new long[N + 1];
        } catch (IOException e) {}
    }

    private static void printOutput(long answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void initialize() {
        burgerSize[0] = 1;
        totalPatty[0] = 1;

        for (int i = 1; i <= N; i++) {
            burgerSize[i] = 2 * burgerSize[i - 1] + 3;
            totalPatty[i] = 2 * totalPatty[i - 1] + 1;
        }
    }

    private static long countPatty(int level, long layer) {
        if (level == 0) return 1;
        if (layer == 1) return 0;

        if (layer <= burgerSize[level - 1] + 1)
            return countPatty(level - 1, layer - 1);

        if (layer == burgerSize[level - 1] + 2)
            return countPatty(level - 1, layer - 1) + 1;

        if (layer <= 2 * burgerSize[level - 1] + 2) {
            layer -= burgerSize[level - 1] + 2;
            return totalPatty[level - 1] + countPatty(level - 1, layer) + 1;
        }

        return 2 * totalPatty[level - 1] + 1;
    }

    public static void main(String[] args) {
        parseInput();
        initialize();
        long totalPatty = countPatty(N, X);
        printOutput(totalPatty);
    }
}
