package week25.soobin;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Painting {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static char[][] painting;
    private static boolean[][] visited;
    private static int count = 0, area = 0;

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            painting = new char[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++)
                    painting[i][j] = st.nextToken().charAt(0);
            }
        } catch (IOException e) {}
    }

    private static void printAnswer() {
        try {
            bw.write(String.format("%d\n%d", count, area));
            bw.flush();
        } catch (IOException e) {}
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < painting.length && c >= 0 && c < painting[0].length;
    }

    private static int bfs(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sr, sc});
        visited[sr][sc] = true;

        int temp = 1;
        while (!queue.isEmpty()) {
            int[] n = queue.poll();

            for (int[] move : moves) {
                int nr = n[0] + move[0];
                int nc = n[1] + move[1];

                if (!isValid(nr, nc) || visited[nr][nc] || painting[nr][nc] == '0') continue;

                temp++;
                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }

        return temp;
    }

    private static void solution() {
        for (int i = 0; i < painting.length; i++) {
            for (int j = 0; j < painting[0].length; j++) {
                if (visited[i][j] || painting[i][j] == '0') continue;

                count++;
                area = Math.max(area, bfs(i, j));
            }
        }
    }

    public static void main(String[] args) {
        parseInput();
        solution();
        printAnswer();
    }
}
