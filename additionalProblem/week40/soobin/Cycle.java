import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cycle {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int INF = 10000000;

    private static List<int[]>[] roads;
    private static int V;

    public static void main(String[] args) {
        parseInput();
        int[][] costs = floyd();
        int answer = findMinCycle(costs);
        printAnswer(answer);
    }

    private static void parseInput() {
        try {
            String[] input = br.readLine().split(" ");
            V = Integer.parseInt(input[0]);
            int E = Integer.parseInt(input[1]);

            roads = new List[V];
            for (int i = 0; i < V; i++)
                roads[i] = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                input = br.readLine().split(" ");
                int from = Integer.parseInt(input[0]) - 1;
                int to = Integer.parseInt(input[1]) - 1;
                int weight = Integer.parseInt(input[2]);
                roads[from].add(new int[] {to, weight});
            }
        } catch (IOException ignored) {}
    }

    private static int[][] floyd() {
        int[][] costs = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(costs[i], INF);
            for (int[] road : roads[i])
                costs[i][road[0]] = road[1];
        }

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);

        return costs;
    }

    private static int findMinCycle(int[][] costs) {
        int cycleCost = INF;

        for (int i = 0; i < V; i++)
            cycleCost = Math.min(cycleCost, costs[i][i]);

        return cycleCost == INF ? -1 : cycleCost;
    }

    private static void printAnswer(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
