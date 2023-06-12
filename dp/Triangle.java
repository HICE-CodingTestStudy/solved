import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Triangle {
    private static int[][] memo = new int[501][501];
    private static int[][] triangle;

    private static int trace(int n, int m) {
        if (n == 0) return memo[0][0] = triangle[0][0];

        if (memo[n][m] > 0) return memo[n][m];

        if (m > 0 && m < n)
            return memo[n][m] = Math.max(trace(n - 1, m - 1), trace(n - 1, m)) + triangle[n][m];
        else if (m == 0) return memo[n][m] = trace(n-1, 0) + triangle[n][0];
        else return memo[n][n] = trace(n-1, n) + triangle[n][n];
    }

    public static int solution(int[][] triangle) {
        Triangle.triangle = triangle;
        List<Integer> traces = new ArrayList<>();
        for (int i = 0; i < triangle.length; i++)
            traces.add(trace(triangle.length - 1, i));
        return Collections.max(traces);
    }

    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution(triangle));
    }
}
