package eighth.soobin;

import java.util.LinkedList;
import java.util.Queue;

public class EscapeMaze {
    private final int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private char[][] maze;
    private int N, M;

    private int bfs(int[] start, int[] dest, int initD) {
        int[][] dist = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(start);
        visited[start[0]][start[1]] = true;
        dist[start[0]][start[1]] = initD;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == dest[0] && cur[1] == dest[1]) break;

            for (int[] move : moves) {
                int nr = cur[0] + move[0];
                int nc = cur[1] + move[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || maze[nr][nc] == 'X') continue;

                queue.add(new int[] {nr, nc});
                visited[nr][nc] = true;
                dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
            }
        }

        return dist[dest[0]][dest[1]];
    }

    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        int[][] location = new int[3][2];
        maze = new char[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maze[i][j] = maps[i].charAt(j);
                if (maze[i][j] == 'S') location[0] = new int[]{i, j};
                if (maze[i][j] == 'L') location[1] = new int[]{i, j};
                if (maze[i][j] == 'E') location[2] = new int[]{i, j};
            }
        }

        int answer = bfs(location[0], location[1], 0);
        if (answer == 0) return -1;

        answer = bfs(location[1], location[2], answer);
        return answer != 0 ? answer : -1;
    }

    public static void main(String[] args) {
        EscapeMaze e = new EscapeMaze();

        System.out.println(e.solution(new String[] {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"}));
        System.out.println(e.solution(new String[] {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"}));
    }
}
