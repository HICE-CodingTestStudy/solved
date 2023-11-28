package week28.soobin;

import java.io.*;
import java.util.*;

public class LightingUp {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static Queue<int[]> queue;
    private static List<int[]>[][] switches;
    private static boolean[][] isLightOn, visited;
    private static int answer = 1;

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < switches.length && c >= 0 && c < switches.length;
    }

    private static void addSwitch(int x, int y, int a, int b) {
        switches[x][y].add(new int[] {a, b});
    }

    private static void parseInput() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            switches = new List[N][N];
            isLightOn = new boolean[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    switches[i][j] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                addSwitch(x, y, a, b);
            }
        } catch (IOException e) {}
    }

    private static void printOutput() {
        try {
            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {}
    }

    private static void lightUpRoom(int[] room) {
        for (int[] next : switches[room[0]][room[1]]) {
            int nx = next[0], ny = next[1];

            if (!isLightOn[nx][ny]) {
                isLightOn[nx][ny] = true;
                answer++;
            }

            // 이전에 방문했던 방에서 새로 불 켜진 방으로 갈 수 있는지 확인
            if (visited[nx][ny]) continue;
            for (int[] move : moves) {
                int nnx = nx + move[0], nny = ny + move[1];
                if (!isValid(nnx, nny) || !visited[nnx][nny]) continue;
                queue.add(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    private static void moveToNextRoom(int[] room) {
        for (int[] move : moves) {
            int nx = room[0] + move[0], ny = room[1] + move[1];
            if (!isValid(nx, ny) || visited[nx][ny] || !isLightOn[nx][ny]) continue;

            queue.add(new int[] {nx, ny});
            visited[nx][ny] = true;
        }
    }

    private static void solution() {
        queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        visited[0][0] = isLightOn[0][0] = true;

        while (!queue.isEmpty()) {
            int[] room = queue.poll();
            lightUpRoom(room);
            moveToNextRoom(room);
        }
    }

    public static void main(String[] args) {
        parseInput();
        solution();
        printOutput();
    }
}
