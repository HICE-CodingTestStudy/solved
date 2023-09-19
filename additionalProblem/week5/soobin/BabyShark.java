package week5.soobin;

import java.io.*;
import java.util.*;

public class BabyShark {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int[][] moves = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private static int currentSize = 2;
    private static int currentEaten = 0;
    private static boolean isStuck = false;
    private static int[] currentLocation;
    private static int[][] map;
    private static Map<Integer, Integer> fishes;

    private static boolean isMovable(int r, int c, int N) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static boolean canEatFish() {
        for (int i = 1; i < currentSize; i++)
            if (fishes.containsKey(i) && fishes.get(i) > 0) return true;
        return false;
    }

    private static int eatClosestFish(int N) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o1[2] - o2[2];
        });
        int[][] distance = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(currentLocation);
        visited[currentLocation[0]][currentLocation[1]] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int[] move : moves) {
                int nRow = node[0] + move[0];
                int nCol = node[1] + move[1];

                if (!isMovable(nRow, nCol, N)
                        || map[nRow][nCol] > currentSize
                        || visited[nRow][nCol])
                    continue;

                distance[nRow][nCol] = distance[node[0]][node[1]] + 1;
                visited[nRow][nCol] = true;
                queue.add(new int[] {nRow, nCol});

                if (map[nRow][nCol] < currentSize && map[nRow][nCol] > 0) {
                    pq.add(new int[] {distance[nRow][nCol], nRow, nCol});
                }
            }
        }

        if (pq.isEmpty()) {
            isStuck = true;
            return 0;
        } else {
            int[] fish = pq.poll();
            int size = map[fish[1]][fish[2]];
            fishes.put(size, fishes.get(size) - 1);
            map[fish[1]][fish[2]] = 0;
            currentEaten++;

            map[currentLocation[0]][currentLocation[1]] = 0;
            map[fish[1]][fish[2]] = 9;
            currentLocation[0] = fish[1];
            currentLocation[1] = fish[2];

            return fish[0];
        }
    }

    private static int simulation(int N) throws IOException {
        fishes = new HashMap<>();
        currentLocation = new int[2];
        map = new int[N][N];

        // Initialize map
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cur = Integer.parseInt(st.nextToken());
                if (cur == 0) continue;

                map[i][j] = cur;
                if (cur == 9) {
                    currentLocation[0] = i;
                    currentLocation[1] = j;
                } else {
                    fishes.put(cur, fishes.getOrDefault(cur, 0) + 1);
                }
            }
        }

        // simulate baby shark launch time
        int totalTime = 0;
        while (!isStuck && canEatFish()) {
            totalTime += eatClosestFish(N);

            if (currentEaten == currentSize) {
                currentSize++;
                currentEaten = 0;
            }
        }

        return totalTime;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(simulation(N)));
        bw.flush();
    }
}
