import java.io.*;
import java.util.*;

public class FoodWaste {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] MOVES = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    private static boolean[][] isFoodWaste, visited;
    private static int N, M, answer;

    public static void main(String[] args) throws Exception {
        parseInput();
        solution();
        System.out.println(answer);
    }

    private static void parseInput() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        isFoodWaste = new boolean[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            isFoodWaste[r][c] = true;
        }
    }

    private static void solution() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!isFoodWaste[r][c] || visited[r][c]) continue;
                answer = Math.max(answer, bfs(r, c));
            }
        }
    }

    private static int bfs(int sr, int sc) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {sr, sc});
        visited[sr][sc] = true;
        int area = 0;

        while (!queue.isEmpty()) {
            int r = queue.peek()[0], c = queue.peek()[1];
            queue.poll();
            area++;

            for (int[] move : MOVES) {
                int nr = r + move[0], nc = c + move[1];
                if (isInvalid(nr, nc) || visited[nr][nc]) continue;
                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }

        return area;
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || !isFoodWaste[r][c];
    }
}
