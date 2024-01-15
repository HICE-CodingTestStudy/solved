import java.io.*;
import java.util.*;

public class Leader {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int INF = 10000;

    private static List<Integer>[] friends;
    private static int[][] dist;
    private static int N, scoreOfLeader;

    public static void main(String[] args) {
        parseInput();
        floyd();
        Queue<Integer> candidates = findCandidates();
        printAnswer(candidates);
    }

    private static void parseInput() {
        try {
            N = Integer.parseInt(br.readLine());
            friends = new List[N + 1];
            for (int i = 1; i <= N; i++)
                friends[i] = new ArrayList<>();

            while (true) {
                String[] input = br.readLine().split(" ");
                int u = Integer.parseInt(input[0]);
                int v = Integer.parseInt(input[1]);
                if (u == -1) return;

                friends[u].add(v);
                friends[v].add(u);
            }
        } catch (IOException ignored) {}
    }

    private static void floyd() {
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
            for (int f : friends[i]) dist[i][f] = 1;
        }

        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }

    private static Queue<Integer> findCandidates() {
        Queue<Integer> candidates = new PriorityQueue<>();
        scoreOfLeader = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int score = getScore(i);
            if (score > scoreOfLeader) continue;
            if (score < scoreOfLeader) {
                candidates.clear();
                scoreOfLeader = score;
            }
            candidates.add(i);
        }
        return candidates;
    }

    private static int getScore(int start) {
        int score = 0;

        for (int i = 1; i <= N; i++)
            score = Math.max(score, dist[start][i]);

        return score;
    }

    private static void printAnswer(Queue<Integer> candidates) {
        try {
            bw.write(scoreOfLeader + " " + candidates.size() + "\n");
            while (!candidates.isEmpty())
                bw.write(candidates.poll() + " ");
            bw.flush();
        } catch (IOException ignored) {}
    }
}
