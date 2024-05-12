import java.util.ArrayDeque;
import java.util.Queue;
class Solution {
    static int[] dx = {1, 0, 0, -1}, dy = {0, -1, 1, 0};
    static String[] d = {"d", "l", "r", "u"};

    static class Info {
        int i, j;
        String s;

        public Info(int i, int j, String s) {
            this.i = i;
            this.j = j;
            this.s = s;
        }
    }

public String solution(int n, int m, int x, int y, int r, int c, int k) {
        Queue<Info> queue = new ArrayDeque<>();
        queue.add(new Info(x - 1, y - 1, ""));
        String ans = "impossible";
        boolean[][][] visited = new boolean[n][m][k+2];
        visited[x-1][y-1][0] = true;
        while (!queue.isEmpty()) {
            Info now = queue.poll();
            if (now.s.length() > k) return ans;
            if (now.s.length() == k) {
                if (now.i == r - 1 && now.j == c - 1) {
                    return now.s;
                }
            }
            for (int i = 0; i < 4; i++) {
                int nextI = now.i + dx[i];
                int nextJ = now.j + dy[i];
                if (nextI < 0 || nextJ < 0 || nextI >= n || nextJ >= m) continue;
                    if(visited[nextI][nextJ][now.s.length()+1]) continue;
                    visited[nextI][nextJ][now.s.length()+1] = true;
                if (Math.abs(r - nextI - 1) + Math.abs(c - nextJ - 1) > k - now.s.length()) continue;
                queue.add(new Info(nextI, nextJ, now.s + d[i]));
            }
        }
        return ans;
    }
}