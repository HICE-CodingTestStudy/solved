import java.io.*;
import java.util.*;

public class Main {
    static int N, ans;
    static int[][] info;
    static int[][] minCost;

    static void solution(int count, int visited, int owner, int cost) {
        ans = Math.max(ans, count);
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != 0) continue;
            if (cost > info[owner][i]) continue;
            int next = visited | (1 << i);
            if (minCost[i][next] <= info[owner][i]) continue;
            minCost[i][next] = info[owner][i];
            solution(count + 1, next, i, info[owner][i]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        info = new int[N][N];
        minCost = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            Arrays.fill(minCost[i], 10);
            for (int j = 0; j < N; j++) {
                info[i][j] = s.charAt(j) - '0';
            }
        }
        minCost[0][0] = 0;
        solution(1, 1, 0, 0);
        System.out.println(ans);
    }
}

