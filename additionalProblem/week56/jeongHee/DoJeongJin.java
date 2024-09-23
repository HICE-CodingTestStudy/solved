import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/31929
    //너 재능있어
    static int N, M, K;
    static int[] victory, defeat;
    static int[][] dp;

    static class Info implements Comparable<Info> {
        int i, j, point;

        public Info(int i, int j, int point) {
            this.i = i;
            this.j = j;
            this.point = point;
        }

        @Override
        public int compareTo(Info o) {
            return o.point - point;
        }
    }

    static int solution() {
        int ans = 0;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0, 0, 0));
        while (!pq.isEmpty()) {
            Info now = pq.poll();
            if (dp[now.i][now.j] > now.point) continue;
            if (now.i == N && now.j == M) {
                ans = now.point;
                continue;
            }
            if (now.j != M) {
                int nextI = now.i;
                int nextJ = now.j + 1;
                int r = now.point % K;
                int c = now.point / K;
                if (now.point < 0 && r != 0) {
                    c--;
                    r += K;
                }
                int nextP;
                if (r == 0) nextP = now.point - defeat[now.j];
                else nextP = Math.max(now.point - defeat[now.j], c * K);
                if (dp[nextI][nextJ] < nextP) {
                    dp[nextI][nextJ] = nextP;
                    pq.add(new Info(nextI, nextJ, nextP));
                }
            }
            if (now.i != N) {
                int nextI = now.i + 1;
                int nextJ = now.j;
                int nextP = now.point + victory[now.i];
                if (dp[nextI][nextJ] >= nextP) continue;
                dp[nextI][nextJ] = nextP;
                pq.add(new Info(nextI, nextJ, nextP));
            }
        }
        return ans;
    }

    static int calcDefeat(int i, int j) {
        int r = dp[i][j] % K;
        int c = dp[i][j] / K;
        if (dp[i][j] < 0 && r != 0) {
            c--;
            r += K;
        }
        int nextP;
        if (r == 0) nextP = dp[i][j] - defeat[j];
        else nextP = Math.max(dp[i][j] - defeat[j], c * K);
        return nextP;
    }

    static int solution2() {
        dp = new int[N + 1][M + 1];
//        for (int i = 1; i < N + 1; i++) {
//            dp[i][0] = dp[i - 1][0] + victory[i - 1];
//        }
//        for (int i = 1; i < M + 1; i++) {
//            dp[0][i] = calcDefeat(0, i - 1);
//        }
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M + 1; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) {
                    dp[0][j] = calcDefeat(0, j - 1);
                    continue;
                }
                if (j == 0) {
                    dp[i][0] = dp[i - 1][0] + victory[i - 1];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j] + victory[i - 1], calcDefeat(i, j - 1));
            }
        }
        return dp[N][M];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        victory = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            victory[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(bf.readLine());
        defeat = new int[M];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            defeat[i] = Integer.parseInt(st.nextToken());
        }
        K = Integer.parseInt(bf.readLine());
        System.out.println(solution2());
    }

}