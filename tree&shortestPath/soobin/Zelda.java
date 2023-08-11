package soobin;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Zelda {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] coordinator = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int[][] cave;
    private static int[][] dist;
    private static int N;

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    private static void updateShorterPath(int row, int col, int newDistance, PriorityQueue<int[]> pq) {
        if (dist[row][col] <= newDistance) return;

        dist[row][col] = newDistance;
        pq.add(new int[] {row, col, newDistance});
    }

    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o1 -> o1[2]));
        dist[0][0] = cave[0][0];
        pq.add(new int[] {0, 0, dist[0][0]});

        while (!pq.isEmpty()) {
            int[] u = pq.poll();
            int uRow = u[0];
            int uCol = u[1];

            for (int[] coor : coordinator) {
                int row = uRow + coor[0], col = uCol + coor[1];

                if (isValid(row, col)) updateShorterPath(row, col, dist[uRow][uCol] + cave[row][col], pq);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            cave = new int[N][N];
            dist = new int[N][N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra();

            bw.write(String.format("Problem %d: %d", testCase++, dist[N-1][N-1]));
            bw.newLine();
        }

        bw.flush();
    }
}
