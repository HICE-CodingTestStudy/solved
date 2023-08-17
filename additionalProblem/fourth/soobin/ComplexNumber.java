package fourth.soobin;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ComplexNumber {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static char[][] map;
    private static boolean[][] visited;
    private static Queue<int[]> houses;
    private static PriorityQueue<Integer> numOfHouses;

    private static boolean isMovable(int r, int c, int N) {
        return r < 0 || r >= N
                || c < 0 || c >= N
                || map[r][c] == '0'
                || visited[r][c];
    }

    private static void countHouse(int[] start, int N) {
        if (visited[start[0]][start[1]]) return;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int[] move : moves) {
                int r = node[0] + move[0];
                int c = node[1] + move[1];
                if (isMovable(r, c, N)) continue;

                queue.add(new int[] {r, c});
                visited[r][c] = true;
                count++;
            }
        }

        numOfHouses.add(count);
    }

    private static int countComplex(int N) {
        while (!houses.isEmpty()) {
            int[] house = houses.poll();
            countHouse(house, N);
        }

        return numOfHouses.size();
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        houses = new LinkedList<>();
        numOfHouses = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == '1') houses.add(new int[] {i, j});
            }
        }

        bw.write(String.format("%d\n", countComplex(N)));
        while (!numOfHouses.isEmpty())
            bw.write(String.format("%d\n", numOfHouses.poll()));
        bw.flush();
    }
}
