import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/20058
    //마법사 상어와 파이어스톰
    static int N, M;
    static int[][] map, tmpMap;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static void magic(int l) {
        tmpMap = new int[N][N];
        int level = 1 << l;
        for (int i = 0; i < N; i += level) {
            for (int j = 0; j < N; j += level) {
                turn(i, j, level);
            }
        }
        map = tmpMap;
    }

    static void turn(int r, int c, int level) {
        for (int i = r, rCount = 0; i < r + level; i++, rCount++) {
            for (int j = c, cCount = 0; j < c + level; j++, cCount++) {
                tmpMap[i][j] = map[r + level - cCount - 1][rCount + c];
            }
        }
    }

    static void melt() {
        tmpMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = 0;
                if (map[i][j] == 0) {
                    tmpMap[i][j] = 0;
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int nextI = i + dx[k];
                    int nextJ = j + dy[k];
                    if (nextI < 0 || nextJ < 0 || nextJ >= N || nextI >= N) continue;
                    if (map[nextI][nextJ] == 0) continue;
                    count++;
                }
                tmpMap[i][j] = count >= 3 ? map[i][j] : map[i][j] - 1;
            }
        }
        map = tmpMap;
    }

    static int[] calcAns() {
        int sum = 0;
        int maxIce = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] <= 0) continue;
                Queue<int[]> queue = new ArrayDeque<>();
                sum += map[i][j];
                map[i][j] = -1;
                int count = 1;
                queue.offer(new int[]{i, j});
                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        int nextI = now[0] + dx[k];
                        int nextJ = now[1] + dy[k];
                        if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= N) continue;
                        if (map[nextI][nextJ] <= 0) continue;
                        sum += map[nextI][nextJ];
                        map[nextI][nextJ] = -1;
                        queue.add(new int[]{nextI, nextJ});
                        count++;
                    }
                }
                maxIce = Math.max(maxIce, count);
            }
        }
        return new int[]{sum, maxIce};
    }

    static void solution(int l) {
        magic(l);
        melt();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = 1 << Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            int l = Integer.parseInt(st.nextToken());
            solution(l);
        }
        int[] ans = calcAns();
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }
}