package week22.soobin;

import java.io.*;
import java.util.*;

public class SnakeLadder {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] board;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            board = new int[101];
            Arrays.fill(board, -1);

            for (int i = 0; i < N + M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                board[from] = to;
            }
        } catch (IOException e) {}
    }

    private static void printOutput(int answer) {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static int rollDice() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        int answer = Integer.MAX_VALUE;

        queue.add(new int[] {1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.peek()[0];
            int numRolled = queue.peek()[1];
            queue.poll();

            if (current == 100) answer = Math.min(answer, numRolled);

            for (int i = 1; i <= 6; i++) {
                int next = current + i;
                if (next > 100 || visited[next]) continue;

                visited[next] = true;
                if (board[next] != -1) next = board[next];
                visited[next] = true;
                queue.add(new int[] {next, numRolled + 1});
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        parseInput();
        int answer = rollDice();
        printOutput(answer);
    }
}
