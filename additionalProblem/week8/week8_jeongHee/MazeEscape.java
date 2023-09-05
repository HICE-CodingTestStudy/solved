package additional;

import java.util.LinkedList;
import java.util.Queue;

public class MazeEscape {
    //https://school.programmers.co.kr/learn/courses/30/lessons/159993
    //미로 탈출
    public int bfs(int startI, int startJ, char[][] map, char target) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startI, startJ});
        visited[startI][startJ] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] next = queue.poll();
                int i = next[0];
                int j = next[1];
                int[] dx = {-1, 1, 0, 0};
                int[] dy = {0, 0, -1, 1};
                if (map[i][j] == target) return count;
                for (int p = 0; p < 4; p++) {
                    int nextI = i + dx[p];
                    int nextJ = j + dy[p];
                    if (nextI < 0 || nextJ < 0 || nextI >= map.length || nextJ >= map[0].length) continue;
                    if (map[nextI][nextJ] == 'X') continue;
                    if (visited[nextI][nextJ]) continue;
                    visited[nextI][nextJ] = true;
                    queue.add(new int[]{nextI, nextJ});
                }
            }
            count++;
        }
        return -1;
    }

    public int solution(String[] maps) {
        char[][] map = new char[maps.length][maps[0].length()];
        int[] S = new int[2];
        int[] L = new int[2];
        int[] E = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = maps[i].charAt(j);
                if (map[i][j] == 'L') L = new int[]{i, j};
                if (map[i][j] == 'E') E = new int[]{i, j};
                if (map[i][j] == 'S') S = new int[]{i, j};

            }
        }
        int l = bfs(S[0], S[1], map, 'L');
        if (l == -1) return -1;
        int e = bfs(L[0], L[1], map, 'E');
        if (e == -1) return -1;
        return l + e;
    }

    public static void main(String[] args) {
        MazeEscape m = new MazeEscape();
        m.solution(new String[]{"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"});
    }

}
