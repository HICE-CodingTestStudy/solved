import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/20926
    //얼음 미로
    static int W, H;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static char[][] map;
    static int[] start;

    static class Info implements Comparable<Info> {
        int i, j, d;
        int cost;

        public Info(int i, int j, int d, int cost) {
            this.i = i;
            this.j = j;
            this.d = d;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return cost - o.cost;
        }
    }

    static boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < H && j < W;
    }

    static int solution() {
        int[][][] dp = new int[H][W][4];
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < 4; i++) {
            dp[start[0]][start[1]][i] = 0;
            pq.add(new Info(start[0], start[1], i, 0));
        }
        while (!pq.isEmpty()) {
            Info now = pq.poll();
            if (now.d != -1 && dp[now.i][now.j][now.d] < now.cost)continue;
            char nowC = map[now.i][now.j];
            if (nowC == 'E') return now.cost;
            if (nowC == 'H' || nowC == 'R') continue;
            if (now.d != -1) {
                int nextI = now.i + dx[now.d];
                int nextJ = now.j + dy[now.d];
                int nextC = now.cost + map[now.i][now.j] - '0';
                if (!isValid(nextI, nextJ)) continue;
                if (dp[nextI][nextJ][now.d] <= nextC) continue;
                dp[nextI][nextJ][now.d] = nextC;
                if (map[nextI][nextJ] == 'R')
                    pq.add(new Info(now.i, now.j, -1, now.cost));
                else pq.add(new Info(nextI, nextJ, now.d, nextC));
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextI = now.i + dx[i];
                int nextJ = now.j + dy[i];
                int nextC = now.cost + map[now.i][now.j] - '0';
                if (!isValid(nextI, nextJ)) continue;
                if (dp[nextI][nextJ][i] <= nextC) continue;
                dp[nextI][nextJ][i] = nextC;
                pq.add(new Info(nextI, nextJ, i, nextC));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        for (int i = 0; i < H; i++) {
            String s = bf.readLine();
            for (int j = 0; j < W; j++) {
                char c = s.charAt(j);
                map[i][j] = c;
                if (c == 'T') {
                    start = new int[]{i, j};
                    map[i][j] = '0';
                }
            }
        }
        System.out.println(solution());
    }
}