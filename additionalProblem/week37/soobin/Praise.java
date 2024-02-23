import java.io.*;

public class Praise {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] boss, finalPraiseScore, originPraiseScore;
    private static int N, M;

    public static void main(String[] args) {
        parseInput();
        printResult();
    }

    private static void parseInput() {
        try {
            init();
            makeRelationship();
            praise();
        } catch (IOException ignored) {}
    }

    private static void init() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        boss = new int[N];
        originPraiseScore = new int[N];
        finalPraiseScore = new int[N];
    }

    private static void makeRelationship() throws IOException {
        String[] input = br.readLine().split(" ");
        boss[0] = -1;
        finalPraiseScore[0] = -1;
        for (int i = 1; i < N; i++) {
            finalPraiseScore[i] = -1;
            boss[i] = Integer.parseInt(input[i]) - 1;
        }
    }

    private static void praise() throws IOException {
        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            int employee = Integer.parseInt(input[0]) - 1;
            int praise = Integer.parseInt(input[1]);
            originPraiseScore[employee] += praise;
        }
    }

    private static void printResult() {
        try {
            for (int i = 0; i < N; i++) {
                int finalPraise = dfs(i);
                bw.write(finalPraise + " ");
            }
            bw.flush();
        } catch (IOException ignored) {}
    }

    private static int dfs(int employee) {
        if (employee == -1) return 0;

        if (finalPraiseScore[employee] > -1)
            return finalPraiseScore[employee];

        finalPraiseScore[employee] = originPraiseScore[employee];
        return finalPraiseScore[employee] += dfs(boss[employee]);
    }
}
