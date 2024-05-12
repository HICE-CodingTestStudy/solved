package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class DeathNote {
    //https://www.acmicpc.net/problem/2281
    //데스노트
    static int n, m;
    static List<Integer> names = new ArrayList<>();
    static int[][] dp;

    static int solution(int writeAt, int index) {
        if (index == n)
            return 0;
        if (dp[writeAt][index] != Integer.MAX_VALUE) return dp[writeAt][index];
        int nextWriteAt = writeAt + 1 + names.get(index);
        if (nextWriteAt <= m) {
            return dp[writeAt][index] = Math.min(
                    solution(nextWriteAt, index + 1),
                    solution(names.get(index), index + 1) + (m - writeAt) * (m - writeAt)
            );
        }
        return dp[writeAt][index] = solution(names.get(index), index + 1) + (m - writeAt) * (m - writeAt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[m + 1][n];
        for (int i = 0; i < m + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            names.add(Integer.valueOf(bf.readLine()));
        }
        System.out.println(solution(names.get(0), 1));
    }
}
