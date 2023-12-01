package additional2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Princess {
    //https://www.acmicpc.net/problem/1941
    //소문난 칠공주
    static char[][] map = new char[5][5];
    static boolean[][] visited = new boolean[5][5];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0;

    static boolean isValid(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        boolean[][] checked = new boolean[5][5];
        checked[i][j] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nextI = now[0] + dx[k];
                int nextJ = now[1] + dy[k];
                if (nextI < 0 || nextJ < 0 || nextJ >= 5 || nextI >= 5) continue;
                if (checked[nextI][nextJ]) continue;
                if (visited[nextI][nextJ]) {
                    queue.add(new int[]{nextI, nextJ});
                    count++;
                    checked[nextI][nextJ] = true;
                }
            }
        }
        return count == 7;
    }

    static void solution(int startI, int startJ, List<Character> students) {
        if (students.size() == 7) {
            if (!isValid(startI, startJ)) return;
            int s = 0;
            for (Character c : students)
                if (c == 'S') s++;
            if (s >= 4)
                ans++;
            return;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) continue;
                if (i * 5 + j < startI * 5 + startJ) continue;
                visited[i][j] = true;
                students.add(map[i][j]);
                solution(i, j, students);
                students.remove(students.size() - 1);
                visited[i][j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String s = bf.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        solution(0, 0, new ArrayList<>());
        System.out.println(ans);
    }
}
