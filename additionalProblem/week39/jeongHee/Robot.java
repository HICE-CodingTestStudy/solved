import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Robot {
    //https://www.acmicpc.net/problem/1726
    //로봇
    static int N, M;
    static int[][][] dp;
    static int[][] map;
    static int startI, startJ, startD, endI, endJ, endD;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0}; //동남서북

    static boolean isValid(int i, int j) {
        return !(i < 0 || j < 0 || i >= N || j >= M) && map[i][j] == 0;
    }

    static int getDirection(int d) {
        return d % 4;
    }

//    static int solution(int i, int j, int d) {
//        if (!isValid(i, j)) return 4000;
//        if (dp[i][j][d] != -1)
//            return dp[i][j][d];
//        if (map[i][j] == 1) return dp[i][j][d] = 4000;
//        int current = 4000;
//        if (isValid(i - dx[d], j - dy[d]) && map[i - dx[d]][j - dy[d]] == 0) {
//            if (isValid(i - 2 * dx[d], j - 2 * dy[d]) && map[i - 2 * dx[d]][j - 2 * dy[d]] == 0) {
//                if (isValid(i - 3 * dx[d], j - 3 * dy[d]) && map[i - 3 * dx[d]][j - 3 * dy[d]] == 0)
//                    current = Math.min(current, solution(i - 3 * dx[d], j - 3 * dy[d], d) + 1);
//                current = Math.min(current, solution(i - 2 * dx[d], j - 2 * dy[d], d) + 1);
//            }
//            current = Math.min(current, solution(i - dx[d], j - dy[d], d) + 1);
//        }
//        current = Math.min(current, solution(i, j, getDirection(d + 1)) + 1);
//        current = Math.min(current, solution(i, j, getDirection(d + 3)) + 1);
//        current = Math.min(current, solution(i, j, getDirection(d + 2)) + 2);
//        return dp[i][j][d] = current;
//    }

    static int solution() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[3]));
        pq.add(new int[]{startI, startJ, startD, 0});
        pq.add(new int[]{startI, startJ, getDirection(startD + 1), 1});
        pq.add(new int[]{startI, startJ, getDirection(startD + 2), 2});
        pq.add(new int[]{startI, startJ, getDirection(startD + 3), 1});
        int ans = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            if (pq.peek()[3] >= ans) return ans;
            int[] now = pq.poll();
            if (now[0] == endI && now[1] == endJ) {
                dp[endI][endJ][now[2]] = Math.min(dp[endI][endJ][now[2]], now[3]);
                if (Math.abs(now[2] - endD) % 2 == 1) {
                    dp[endI][endJ][endD] = Math.min(dp[endI][endJ][endD], dp[endI][endJ][now[2]] + 1);
                } else if (Math.abs(now[2] - endD) == 2) {
                    dp[endI][endJ][endD] = Math.min(dp[endI][endJ][endD], dp[endI][endJ][now[2]] + 2);
                }
                ans = Math.min(ans, dp[endI][endJ][endD]);
            }
            for (int i = 0; i < 4; i++) {
                int d = getDirection(now[2] + i);
                int plus = i;
                if (i == 3) plus = 1;
                for (int j = 1; j <= 3; j++) {
                    int nextI = now[0] + j * dx[d];
                    int nextJ = now[1] + j * dy[d];
                    if (!isValid(nextI, nextJ)) break;
                    if (dp[nextI][nextJ][d] <= now[3] + plus + 1) continue;
                    dp[nextI][nextJ][d] = now[3] + plus + 1;
                    pq.add(new int[]{nextI, nextJ, d, now[3] + plus + 1});
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][M][4];
        map = new int[N][M];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine());
        startI = Integer.parseInt(st.nextToken()) - 1;
        startJ = Integer.parseInt(st.nextToken()) - 1;
        startD = Integer.parseInt(st.nextToken()) - 1;
        startD = startD == 1 || startD == 2 ? 3 - startD : startD;
        st = new StringTokenizer(bf.readLine());
        endI = Integer.parseInt(st.nextToken()) - 1;
        endJ = Integer.parseInt(st.nextToken()) - 1;
        endD = Integer.parseInt(st.nextToken()) - 1;
        endD = endD == 1 || endD == 2 ? 3 - endD : endD;
        System.out.println(solution());
    }
}
