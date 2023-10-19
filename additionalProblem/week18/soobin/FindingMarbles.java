package week18.soobin;

import java.io.*;
import java.util.*;

public class FindingMarbles {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer>[] graph;
    private static int[][] compare; // 0: 무거운 구슬 수, 1: 가벼운 구슬 수
    private static int N;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            graph = new List[N + 1];
            for (int i = 1; i <= N; i++)
                graph[i] = new ArrayList<>();

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int heavier = Integer.parseInt(st.nextToken());
                int lighter = Integer.parseInt(st.nextToken());
                graph[lighter].add(heavier);
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int findHeavier(boolean[]visited, int n) {
        if (graph[n].isEmpty())
            return 0;

        int count = 0;
        for (int adj : graph[n]) {
            if (visited[adj]) continue;

            compare[adj][1]++;
            visited[adj] = true;
            count += findHeavier(visited, adj) + 1;
        }
        return count;
    }

    private static boolean isImpossible(int n) {
        return compare[n][0] > (N - 1) / 2 || compare[n][1] > (N - 1) / 2;
    }

    private static int countImpossibleMarbles() {
        compare = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;
            compare[i][0] = findHeavier(visited, i);
        }

        int answer = 0;
        for (int i = 1; i <= N; i++)
            if (isImpossible(i)) answer++;
        return answer;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = countImpossibleMarbles();
        printOutput(answer);
    }
}
