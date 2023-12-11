package week23.soobin;

import java.io.*;
import java.util.StringTokenizer;

public class PlumTree {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[][][] memo;
    private static int[] plums;
    private static int T, W;

    public static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            memo = new int[T][W + 1][2];
            plums = new int[T];

            for (int i = 0; i < T; i++)
                plums[i] = Integer.parseInt(br.readLine()) - 1;
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int changeTree(int tree) {
        return tree == 0 ? 1 : 0;
    }

    private static int getPlums(int t, int tree, int w) {
        if (t == T - 1) {
            if (plums[t] == tree) return 1;
            else return w < W ? 1 : 0;
        }

        if (memo[t][w][tree] > 0) return memo[t][w][tree];

        if (tree == plums[t])
            return memo[t][w][tree] = getPlums(t + 1, tree, w) + 1;

        if (w < W) memo[t][w][tree] = getPlums(t + 1, changeTree(tree), w + 1) + 1;
        return memo[t][w][tree] = Math.max(memo[t][w][tree], getPlums(t + 1, tree, w));
    }

    public static void main(String[] args) {
        parseInput();
        int answer = getPlums(0, 0, 0);
        printOutput(answer);
    }
}
